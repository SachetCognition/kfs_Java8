"""Portal loads, dev auto-login works, main navigation is present."""

import kfs_client


def test_portal_loads_and_khuntley_logged_in(http, base_url):
    resp = http.get(kfs_client.portal_url(base_url))
    assert resp.status_code == 200
    assert "khuntley" in resp.text, "expected auto-login as khuntley"


def test_main_menu_links_present(http, base_url):
    resp = http.get(kfs_client.portal_url(base_url))
    assert resp.status_code == 200
    for tab in ("Main Menu", "Maintenance", "Administration"):
        assert tab in resp.text, "missing portal navigation tab: %s" % tab
