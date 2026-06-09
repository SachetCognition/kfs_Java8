package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.PdpConstants;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kim.api.services.IdentityManagementService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PdpAuthorizationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private IdentityManagementService identityManagementService;

    private PdpAuthorizationServiceImpl pdpAuthorizationService;

    @BeforeEach
    void setUp() {
        pdpAuthorizationService = new PdpAuthorizationServiceImpl() {
            @Override
            public IdentityManagementService getIdentityManagementService() {
                return identityManagementService;
            }
        };
    }

    @Test
    void testHasCancelPaymentPermission_authorized() {
        when(identityManagementService.isAuthorized(eq("user1"), eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.CANCEL_PAYMENT), isNull())).thenReturn(true);

        assertThat(pdpAuthorizationService.hasCancelPaymentPermission("user1")).isTrue();
    }

    @Test
    void testHasCancelPaymentPermission_notAuthorized() {
        when(identityManagementService.isAuthorized(eq("user2"), eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.CANCEL_PAYMENT), isNull())).thenReturn(false);

        assertThat(pdpAuthorizationService.hasCancelPaymentPermission("user2")).isFalse();
    }

    @Test
    void testHasFormatPermission_authorized() {
        when(identityManagementService.isAuthorized(eq("user1"), eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.FORMAT), isNull())).thenReturn(true);

        assertThat(pdpAuthorizationService.hasFormatPermission("user1")).isTrue();
    }

    @Test
    void testHasFormatPermission_notAuthorized() {
        when(identityManagementService.isAuthorized(eq("user1"), eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.FORMAT), isNull())).thenReturn(false);

        assertThat(pdpAuthorizationService.hasFormatPermission("user1")).isFalse();
    }

    @Test
    void testHasHoldPaymentPermission_authorized() {
        when(identityManagementService.isAuthorized(eq("user1"), eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.HOLD_PAYMENT_REMOVE_NON_TAX_PAYMENT_HOLD), isNull())).thenReturn(true);

        assertThat(pdpAuthorizationService.hasHoldPaymentPermission("user1")).isTrue();
    }

    @Test
    void testHasRemoveFormatLockPermission_authorized() {
        when(identityManagementService.isAuthorized(eq("user1"), eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.REMOVE_FORMAT_LOCK), isNull())).thenReturn(true);

        assertThat(pdpAuthorizationService.hasRemoveFormatLockPermission("user1")).isTrue();
    }

    @Test
    void testHasRemovePaymentTaxHoldPermission_authorized() {
        when(identityManagementService.isAuthorized(eq("user1"), eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.REMOVE_PAYMENT_TAX_HOLD), isNull())).thenReturn(true);

        assertThat(pdpAuthorizationService.hasRemovePaymentTaxHoldPermission("user1")).isTrue();
    }

    @Test
    void testHasSetAsImmediatePayPermission_authorized() {
        when(identityManagementService.isAuthorized(eq("user1"), eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.SET_AS_IMMEDIATE_PAY), isNull())).thenReturn(true);

        assertThat(pdpAuthorizationService.hasSetAsImmediatePayPermission("user1")).isTrue();
    }

    @Test
    void testHasSetAsImmediatePayPermission_notAuthorized() {
        when(identityManagementService.isAuthorized(eq("user1"), eq(KFSConstants.CoreModuleNamespaces.PDP),
                eq(PdpConstants.PermissionNames.SET_AS_IMMEDIATE_PAY), isNull())).thenReturn(false);

        assertThat(pdpAuthorizationService.hasSetAsImmediatePayPermission("user1")).isFalse();
    }
}
