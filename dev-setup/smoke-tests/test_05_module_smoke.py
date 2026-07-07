"""Broad read-only smoke coverage across kfs-vnd, kfs-coa, kfs-fp, kfs-purap:
lookups, an inquiry, and document initiation screens."""

import pytest

import kfs_client

LOOKUPS = [
    ("Vendor", "org.kuali.kfs.vnd.businessobject.VendorDetail"),
    ("Chart", "org.kuali.kfs.coa.businessobject.Chart"),
    ("Organization", "org.kuali.kfs.coa.businessobject.Organization"),
    ("Object Code", "org.kuali.kfs.coa.businessobject.ObjectCode"),
]


@pytest.mark.parametrize("label,bo_class", LOOKUPS, ids=[l for l, _ in LOOKUPS])
def test_lookup_returns_rows(http, base_url, label, bo_class):
    page = kfs_client.lookup(http, base_url, bo_class)
    assert kfs_client.lookup_result_count(page) > 0, \
        "%s lookup returned no rows" % label


def test_chart_inquiry(http, base_url):
    resp = http.get(
        base_url + "/kr/inquiry.do",
        params={
            "methodToCall": "start",
            "businessObjectClassName":
                "org.kuali.kfs.coa.businessobject.Chart",
            "chartOfAccountsCode": "BL",
        },
    )
    assert resp.status_code == 200
    assert "Chart Code" in resp.text
    assert "BL" in resp.text


INITIATION_SCREENS = [
    ("Disbursement Voucher", "financialDisbursementVoucher.do", "DV",
     "Disbursement Voucher"),
    ("Requisition", "purapRequisition.do", "REQS", "Requisition"),
]


@pytest.mark.parametrize(
    "label,action,doc_type,marker", INITIATION_SCREENS,
    ids=[s[0] for s in INITIATION_SCREENS])
def test_document_initiation_screen(http, base_url, label, action, doc_type,
                                    marker):
    page = kfs_client.initiate_document(http, base_url, action, doc_type)
    assert marker in page, "%s initiation screen missing marker" % label
    assert 'name="document.documentHeader.documentNumber"' in page or \
        "documentNumber" in page
    assert "Incident Report" not in page
