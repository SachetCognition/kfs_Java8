#!/bin/bash
# Extends KFS demo fiscal-year reference data forward from the last seeded year (2017)
# so the application works with the current system date.
# Usage: ./extend-fiscal-years.sh [db] [last_year]
set -e
DB="${1:-kuldev}"
SRC_YEAR=2017
END_YEAR="${2:-2028}"
MYSQL="mysql -ukuldev -pkuldev -h127.0.0.1 -N $DB"

TABLES="ar_org_acctgdflt_t ar_sys_info_t ca_icr_auto_entr_t ca_icr_rate_t ca_object_code_t ca_org_reversion_t ca_org_rvrsn_dtl_t ca_sub_object_cd_t cm_cptlast_obj_t fp_dv_diem_t fp_fscl_yr_ctrl_t fs_option_t fs_wire_chrg_t gl_offset_defn_t ld_benefits_calc_t ld_labor_obj_t ld_lbr_obj_bene_t"

for T in $TABLES; do
  COLS=$($MYSQL -e "select group_concat(concat('\`',column_name,'\`')) from information_schema.columns where table_schema='$DB' and table_name='$T' order by ordinal_position")
  SRC=$($MYSQL -e "select max(UNIV_FISCAL_YR) from $T")
  if [ "$SRC" = "NULL" ] || [ -z "$SRC" ]; then echo "skipped $T (empty)"; continue; fi
  for Y in $(seq $((SRC+1)) $END_YEAR); do
    SEL=$(echo "$COLS" | sed -e "s/\`UNIV_FISCAL_YR\`/$Y as UNIV_FISCAL_YR/" -e "s/\`OBJ_ID\`/UUID() as OBJ_ID/")
    $MYSQL -e "insert ignore into $T ($COLS) select $SEL from $T where UNIV_FISCAL_YR=$SRC"
  done
  echo "extended $T from $SRC"
done

# sh_acct_period_t: shift period begin/end dates forward by whole years
COLS=$($MYSQL -e "select group_concat(concat('\`',column_name,'\`')) from information_schema.columns where table_schema='$DB' and table_name='sh_acct_period_t' order by ordinal_position")
SRC=$($MYSQL -e "select max(UNIV_FISCAL_YR) from sh_acct_period_t")
for Y in $(seq $((SRC+1)) $END_YEAR); do
  OFF=$((Y-SRC))
  SEL=$(echo "$COLS" | sed -e "s/\`UNIV_FISCAL_YR\`/$Y as UNIV_FISCAL_YR/" -e "s/\`OBJ_ID\`/UUID() as OBJ_ID/" -e "s/\`UNIV_FSCPD_END_DT\`/DATE_ADD(UNIV_FSCPD_END_DT, INTERVAL $OFF YEAR) as UNIV_FSCPD_END_DT/")
  $MYSQL -e "insert ignore into sh_acct_period_t ($COLS) select $SEL from sh_acct_period_t where UNIV_FISCAL_YR=$SRC"
done
echo "extended sh_acct_period_t"

# sh_univ_date_t: one row per calendar day; fiscal year runs Jul 1 - Jun 30, period = fiscal month
$MYSQL <<EOF
DROP TEMPORARY TABLE IF EXISTS seq_days;
CREATE TEMPORARY TABLE seq_days (n INT PRIMARY KEY);
INSERT INTO seq_days
SELECT a.n + b.n*10 + c.n*100 + d.n*1000
FROM (SELECT 0 n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a,
     (SELECT 0 n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b,
     (SELECT 0 n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) c,
     (SELECT 0 n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) d;
INSERT IGNORE INTO sh_univ_date_t (UNIV_DT, OBJ_ID, VER_NBR, UNIV_FISCAL_YR, UNIV_FISCAL_PRD_CD)
SELECT dt, UUID(), 1,
       IF(MONTH(dt) >= 7, YEAR(dt)+1, YEAR(dt)),
       LPAD(IF(MONTH(dt) >= 7, MONTH(dt)-6, MONTH(dt)+6), 2, '0')
FROM (SELECT DATE_ADD('$SRC_YEAR-07-01', INTERVAL n DAY) dt FROM seq_days) days
WHERE dt <= '$END_YEAR-06-30';
EOF
echo "extended sh_univ_date_t"
