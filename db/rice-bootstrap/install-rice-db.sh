#!/usr/bin/env bash
#
# Installs the Kuali Rice 2.1.10 bootstrap database schema + seed data into a
# local MySQL database for KFS runtime deployment.
#
# The schema/data come from org.kuali.rice:rice-impex-server-bootstrap:2.1.10
# (still published on Maven Central), so no defunct Kuali Nexus access is
# required. Three MySQL-8 compatibility adjustments are applied on the fly:
#   1. utf8/utf8_bin table charset -> latin1/latin1_bin (composite indexes on
#      VARCHAR(256)+VARCHAR(2000) columns exceed MySQL 8's 3072-byte key limit
#      under utf8mb3/utf8mb4)
#   2. lowercase table references in views -> uppercase (Linux MySQL is
#      case-sensitive; the impex DDL mixes case)
#   3. sequences are emulated as single-column AUTO_INCREMENT tables (the DDL
#      already contains them; nothing extra needed)
#
# Usage: ./install-rice-db.sh [db_name] [db_user] [db_pass]
set -euo pipefail

DB_NAME="${1:-kfs}"
DB_USER="${2:-kfs}"
DB_PASS="${3:-kfs}"
WORK_DIR="$(mktemp -d)"
trap 'rm -rf "$WORK_DIR"' EXIT

JAR_URL="https://repo1.maven.org/maven2/org/kuali/rice/rice-impex-server-bootstrap/2.1.10/rice-impex-server-bootstrap-2.1.10-sql.jar"

echo "Downloading Rice 2.1.10 bootstrap SQL artifact..."
curl -sfLo "$WORK_DIR/impex-sql.jar" "$JAR_URL"
unzip -q "$WORK_DIR/impex-sql.jar" -d "$WORK_DIR/impex"

echo "Preparing DDL (MySQL 8 compatibility fixes)..."
python3 - "$WORK_DIR" <<'EOF'
import glob, os, re, sys

work = sys.argv[1]
src = os.path.join(work, 'impex', 'sql', 'mysql')

# --- DDL: split on "/" delimiter, strip comments, fix charset + case ---
ddl = open(os.path.join(src, 'rice-impex-server-bootstrap.sql'), encoding='utf-8').read()
out = ["SET FOREIGN_KEY_CHECKS=0;"]
for stmt in ddl.split('\n/\n'):
    lines = [l for l in stmt.splitlines() if not l.strip().startswith('#')]
    stmt = '\n'.join(lines).strip().rstrip('/').strip()
    if stmt:
        out.append(stmt + ';')
out.append("SET FOREIGN_KEY_CHECKS=1;")
s = '\n'.join(out)
s = s.replace('CHARACTER SET utf8 COLLATE utf8_bin', 'CHARACTER SET latin1 COLLATE latin1_bin')
s = re.sub(r'\b([Kk][Rr][A-Za-z]{1,2}_[A-Za-z0-9_]+_[TSVtsv])\b', lambda m: m.group(1).upper(), s)
open(os.path.join(work, 'rice_ddl.sql'), 'w', encoding='utf-8').write(s)

# --- Data: everything except the DDL file ---
out = ["SET FOREIGN_KEY_CHECKS=0;"]
for f in sorted(glob.glob(os.path.join(src, '*.sql'))):
    if os.path.basename(f) == 'rice-impex-server-bootstrap.sql':
        continue
    for stmt in open(f, encoding='utf-8').read().split('\n/\n'):
        stmt = stmt.strip().rstrip('/').strip()
        if stmt:
            out.append(stmt + ';')
out.append("SET FOREIGN_KEY_CHECKS=1;")
open(os.path.join(work, 'rice_data.sql'), 'w', encoding='utf-8').write('\n'.join(out))
EOF

echo "Applying DDL to database '$DB_NAME'..."
mysql -u "$DB_USER" -p"$DB_PASS" "$DB_NAME" < "$WORK_DIR/rice_ddl.sql"

echo "Loading bootstrap seed data..."
mysql -u "$DB_USER" -p"$DB_PASS" "$DB_NAME" < "$WORK_DIR/rice_data.sql"

TABLES=$(mysql -u "$DB_USER" -p"$DB_PASS" -N -e "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema='$DB_NAME'" 2>/dev/null)
echo "Done. $TABLES tables/views installed in '$DB_NAME'."
