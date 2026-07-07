"""Minimal HTTP client helpers for the KFS KNS (Struts 1) UI.

All KNS screens are classic Struts forms: state is kept in the HTTP session
(``documentWebScope=session``), every postback goes to the same ``.do`` action,
and the "button" that was clicked is identified by a ``methodToCall.*``
request-parameter name. Requests must carry the session cookie, which
``requests.Session`` handles for us.
"""

import html
import re

DEFAULT_BASE_URL = "http://localhost:8080/kfs-web-dev"


def portal_url(base_url):
    return base_url + "/portal.do"


def get_hidden_value(page_html, field_name):
    """Extract the value of a hidden input by name."""
    m = re.search(
        r'name="%s" value="([^"]*)"' % re.escape(field_name), page_html
    )
    return m.group(1) if m else None


def get_error_messages(page_html):
    """Collect KNS validation error messages rendered on the page."""
    msgs = re.findall(r"display:list-item[^>]*>([^<]+)<", page_html)
    return [html.unescape(m).strip() for m in msgs]


def lookup(session, base_url, business_object_class, criteria=None,
           lookupable=None):
    """Run a KNS lookup (kr/lookup.do) and return the result page HTML."""
    data = {
        "methodToCall": "search",
        "businessObjectClassName": business_object_class,
        "docFormKey": "88888888",
        "returnLocation": portal_url(base_url),
        "hideReturnLink": "true",
    }
    if lookupable:
        data["lookupableImplServiceName"] = lookupable
    if criteria:
        data.update(criteria)
    resp = session.post(base_url + "/kr/lookup.do", data=data)
    resp.raise_for_status()
    return resp.text


def lookup_result_count(page_html):
    """Number of rows a KNS lookup reports ("N items retrieved" banner)."""
    if "No values match this search" in page_html:
        return 0
    m = re.search(r"(\d+) items retrieved", page_html)
    if m:
        return int(m.group(1))
    if "one item retrieved" in page_html:
        return 1
    return 0


def initiate_document(session, base_url, action, doc_type):
    """Open a transactional document initiation screen and return its HTML."""
    resp = session.get(
        base_url + "/" + action,
        params={
            "methodToCall": "docHandler",
            "command": "initiate",
            "docTypeName": doc_type,
        },
    )
    resp.raise_for_status()
    return resp.text


def post_document_action(session, base_url, action, form_key, method_to_call,
                         fields=None):
    """Post back to a KNS document action held in the HTTP session."""
    data = {
        method_to_call: "submit",
        "formKey": form_key,
        "docFormKey": form_key,
        "documentWebScope": "session",
    }
    if fields:
        data.update(fields)
    resp = session.post(base_url + "/" + action, data=data)
    resp.raise_for_status()
    return resp.text
