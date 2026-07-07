"""Document Search (KEW) finds the routed Transfer of Funds document."""


def test_document_search_finds_routed_doc(http, base_url, routed_tf_doc):
    doc_number, _ = routed_tf_doc
    resp = http.get(
        base_url + "/kew/DocumentSearch.do",
        params={"methodToCall": "search", "documentId": doc_number},
    )
    assert resp.status_code == 200
    assert "item retrieved" in resp.text or "items retrieved" in resp.text
    assert doc_number in resp.text
    assert "ENROUTE" in resp.text
