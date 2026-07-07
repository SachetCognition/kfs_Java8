"""Account Lookup returns the expected demo-data result sets."""

import kfs_client

ACCOUNT_BO = "org.kuali.kfs.coa.businessobject.Account"


def test_account_lookup_bl_psych_returns_11_rows(http, base_url):
    page = kfs_client.lookup(
        http, base_url, ACCOUNT_BO,
        {"chartOfAccountsCode": "BL", "accountName": "PSYCH*"},
    )
    assert kfs_client.lookup_result_count(page) == 11
    assert page.count("PSYCH") >= 11


def test_account_lookup_no_match_returns_0_rows(http, base_url):
    page = kfs_client.lookup(
        http, base_url, ACCOUNT_BO,
        {"chartOfAccountsCode": "BL",
         "accountName": "NO-SUCH-ACCOUNT-NAME-XYZZY*"},
    )
    assert kfs_client.lookup_result_count(page) == 0
