import os

import pytest
import requests

import kfs_client


def pytest_addoption(parser):
    parser.addoption(
        "--base-url",
        default=os.environ.get("KFS_BASE_URL", kfs_client.DEFAULT_BASE_URL),
        help="Base URL of the running KFS webapp",
    )


@pytest.fixture(scope="session")
def base_url(request):
    return request.config.getoption("--base-url").rstrip("/")


@pytest.fixture(scope="session")
def http(base_url):
    """One authenticated HTTP session for the whole run.

    The dev build auto-logs-in as khuntley (DummyLoginFilter), so hitting the
    portal once establishes the session cookie.
    """
    session = requests.Session()
    resp = session.get(kfs_client.portal_url(base_url))
    resp.raise_for_status()
    return session


@pytest.fixture(scope="session")
def routed_tf_doc(http, base_url):
    """Initiate, fill, and route a Transfer of Funds document.

    Shared by the Transfer of Funds test (asserts routing succeeded) and the
    Document Search test (finds the same document by id).
    Returns (doc_number, route_result_html).
    """
    page = kfs_client.initiate_document(
        http, base_url, "financialTransferOfFunds.do", "TF"
    )
    form_key = kfs_client.get_hidden_value(page, "formKey")
    doc_number = kfs_client.get_hidden_value(
        page, "document.documentHeader.documentNumber"
    )
    assert form_key and doc_number, "TF initiation screen missing form state"

    page = kfs_client.post_document_action(
        http, base_url, "financialTransferOfFunds.do", form_key,
        "methodToCall.insertSourceLine.anchoraccountingSourceAnchor",
        {
            "document.documentHeader.documentDescription":
                "Smoke test transfer of funds",
            "newSourceLine.chartOfAccountsCode": "BL",
            "newSourceLine.accountNumber": "1031400",
            "newSourceLine.financialObjectCode": "1663",
            "newSourceLine.amount": "50",
        },
    )
    assert kfs_client.get_error_messages(page) == [], \
        "adding From line failed: %s" % kfs_client.get_error_messages(page)

    page = kfs_client.post_document_action(
        http, base_url, "financialTransferOfFunds.do", form_key,
        "methodToCall.insertTargetLine.anchoraccountingTargetAnchor",
        {
            "newTargetLine.chartOfAccountsCode": "BL",
            "newTargetLine.accountNumber": "1031420",
            "newTargetLine.financialObjectCode": "1663",
            "newTargetLine.amount": "50",
        },
    )
    assert kfs_client.get_error_messages(page) == [], \
        "adding To line failed: %s" % kfs_client.get_error_messages(page)

    page = kfs_client.post_document_action(
        http, base_url, "financialTransferOfFunds.do", form_key,
        "methodToCall.route",
    )
    return doc_number, page
