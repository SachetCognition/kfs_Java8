package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.PdpConstants;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kim.api.services.IdentityManagementService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class PdpAuthorizationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private IdentityManagementService identityManagementService;

    @InjectMocks
    private PdpAuthorizationServiceImpl pdpAuthorizationService;

    @Test
    void hasCancelPaymentPermission_authorized_returnsTrue() {
        when(identityManagementService.isAuthorized(eq("user1"),
                eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.CANCEL_PAYMENT),
                (Map<String, String>) eq(null))).thenReturn(true);

        assertThat(pdpAuthorizationService.hasCancelPaymentPermission("user1")).isTrue();
    }

    @Test
    void hasCancelPaymentPermission_notAuthorized_returnsFalse() {
        when(identityManagementService.isAuthorized(eq("user2"),
                eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.CANCEL_PAYMENT),
                (Map<String, String>) eq(null))).thenReturn(false);

        assertThat(pdpAuthorizationService.hasCancelPaymentPermission("user2")).isFalse();
    }

    @Test
    void hasFormatPermission_authorized_returnsTrue() {
        when(identityManagementService.isAuthorized(eq("user1"),
                eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.FORMAT),
                (Map<String, String>) eq(null))).thenReturn(true);

        assertThat(pdpAuthorizationService.hasFormatPermission("user1")).isTrue();
    }

    @Test
    void hasHoldPaymentPermission_authorized_returnsTrue() {
        when(identityManagementService.isAuthorized(eq("user1"),
                eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.HOLD_PAYMENT_REMOVE_NON_TAX_PAYMENT_HOLD),
                (Map<String, String>) eq(null))).thenReturn(true);

        assertThat(pdpAuthorizationService.hasHoldPaymentPermission("user1")).isTrue();
    }

    @Test
    void hasRemoveFormatLockPermission_authorized_returnsTrue() {
        when(identityManagementService.isAuthorized(eq("user1"),
                eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.REMOVE_FORMAT_LOCK),
                (Map<String, String>) eq(null))).thenReturn(true);

        assertThat(pdpAuthorizationService.hasRemoveFormatLockPermission("user1")).isTrue();
    }

    @Test
    void hasSetAsImmediatePayPermission_authorized_returnsTrue() {
        when(identityManagementService.isAuthorized(eq("user1"),
                eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.SET_AS_IMMEDIATE_PAY),
                (Map<String, String>) eq(null))).thenReturn(true);

        assertThat(pdpAuthorizationService.hasSetAsImmediatePayPermission("user1")).isTrue();
    }
}
