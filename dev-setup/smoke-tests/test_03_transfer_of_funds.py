"""Transfer of Funds: full initiate/fill/route happy path plus the
object-code business-rule validation (negative path)."""

import kfs_client


def test_transfer_of_funds_routes_to_enroute(routed_tf_doc):
    doc_number, page = routed_tf_doc
    assert "successfully submitted" in page, \
        "TF doc %s was not submitted: %s" % (
            doc_number, kfs_client.get_error_messages(page))
    assert "ENROUTE" in page, "TF doc %s is not ENROUTE" % doc_number


def test_transfer_of_funds_rejects_non_transfer_object_code(http, base_url):
    page = kfs_client.initiate_document(
        http, base_url, "financialTransferOfFunds.do", "TF"
    )
    form_key = kfs_client.get_hidden_value(page, "formKey")
    assert form_key

    page = kfs_client.post_document_action(
        http, base_url, "financialTransferOfFunds.do", form_key,
        "methodToCall.insertSourceLine.anchoraccountingSourceAnchor",
        {
            "document.documentHeader.documentDescription":
                "Smoke test negative validation",
            "newSourceLine.chartOfAccountsCode": "BL",
            "newSourceLine.accountNumber": "1031400",
            "newSourceLine.financialObjectCode": "1800",
            "newSourceLine.amount": "50",
        },
    )
    errors = " ".join(kfs_client.get_error_messages(page))
    assert '"Mandatory Transfer" or "Non-Mandatory Transfer"' in errors, \
        "expected transfer object-sub-type rule error, got: %s" % errors
