package org.kuali.kfs.module.ar.batch.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomerLoadBatchErrorsTest extends KfsUnitTestBase {

    private CustomerLoadBatchErrors batchErrors;

    @BeforeEach
    void setUp() {
        batchErrors = new CustomerLoadBatchErrors();
    }

    @Test
    void testDefaultConstructorCreatesEmptyContainer() {
        assertThat(batchErrors.isEmpty()).isTrue();
        assertThat(batchErrors.getTotalErrors()).isZero();
        assertThat(batchErrors.getCompaniesWithErrors()).isZero();
    }

    @Test
    void testConstructorWithSingleError() {
        CustomerLoadBatchError error = new CustomerLoadBatchError(
                "Corp A", "name", String.class, "val", "desc");
        CustomerLoadBatchErrors errors = new CustomerLoadBatchErrors(error);

        assertThat(errors.isEmpty()).isFalse();
        assertThat(errors.getTotalErrors()).isEqualTo(1);
        assertThat(errors.getCompaniesWithErrors()).isEqualTo(1);
    }

    @Test
    void testConstructorWithNullError() {
        CustomerLoadBatchErrors errors = new CustomerLoadBatchErrors((CustomerLoadBatchError) null);
        assertThat(errors.isEmpty()).isTrue();
    }

    @Test
    void testConstructorWithErrorList() {
        List<CustomerLoadBatchError> errorList = new ArrayList<>();
        errorList.add(new CustomerLoadBatchError("Corp A", "name", String.class, "v1", "d1"));
        errorList.add(new CustomerLoadBatchError("Corp B", "email", String.class, "v2", "d2"));

        CustomerLoadBatchErrors errors = new CustomerLoadBatchErrors(errorList);
        assertThat(errors.getTotalErrors()).isEqualTo(2);
        assertThat(errors.getCompaniesWithErrors()).isEqualTo(2);
    }

    @Test
    void testConstructorWithNullList() {
        CustomerLoadBatchErrors errors = new CustomerLoadBatchErrors((List<CustomerLoadBatchError>) null);
        assertThat(errors.isEmpty()).isTrue();
    }

    @Test
    void testConstructorWithEmptyList() {
        CustomerLoadBatchErrors errors = new CustomerLoadBatchErrors(new ArrayList<>());
        assertThat(errors.isEmpty()).isTrue();
    }

    @Test
    void testAddError() {
        CustomerLoadBatchError error = new CustomerLoadBatchError(
                "Corp A", "name", String.class, "val", "desc");
        batchErrors.addError(error);

        assertThat(batchErrors.isEmpty()).isFalse();
        assertThat(batchErrors.getTotalErrors()).isEqualTo(1);
    }

    @Test
    void testAddErrorWithNullThrows() {
        assertThatThrownBy(() -> batchErrors.addError((CustomerLoadBatchError) null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testAddErrorByFields() {
        batchErrors.addError("Corp A", "customerName", String.class, "Bad", "Invalid name");
        assertThat(batchErrors.getTotalErrors()).isEqualTo(1);
        assertThat(batchErrors.getCompanyNames()).contains("Corp A");
    }

    @Test
    void testAddErrorByFieldsWithBlankCustomerName() {
        assertThatThrownBy(() -> batchErrors.addError("", "prop", String.class, "val", "desc"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testAddErrorByFieldsWithBlankPropertyName() {
        assertThatThrownBy(() -> batchErrors.addError("Corp", "", String.class, "val", "desc"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testAddErrorByFieldsWithBlankDescription() {
        assertThatThrownBy(() -> batchErrors.addError("Corp", "prop", String.class, "val", ""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testAddErrors() {
        List<CustomerLoadBatchError> errorList = new ArrayList<>();
        errorList.add(new CustomerLoadBatchError("Corp A", "f1", String.class, "v1", "d1"));
        errorList.add(new CustomerLoadBatchError("Corp A", "f2", String.class, "v2", "d2"));

        batchErrors.addErrors(errorList);
        assertThat(batchErrors.getTotalErrors()).isEqualTo(2);
        assertThat(batchErrors.getCompaniesWithErrors()).isEqualTo(1);
    }

    @Test
    void testAddErrorsWithNullThrows() {
        assertThatThrownBy(() -> batchErrors.addErrors(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testAddAll() {
        CustomerLoadBatchErrors other = new CustomerLoadBatchErrors();
        other.addError(new CustomerLoadBatchError("Corp B", "name", String.class, "v", "d"));

        batchErrors.addError(new CustomerLoadBatchError("Corp A", "name", String.class, "v", "d"));
        batchErrors.addAll(other);

        assertThat(batchErrors.getTotalErrors()).isEqualTo(2);
        assertThat(batchErrors.getCompaniesWithErrors()).isEqualTo(2);
    }

    @Test
    void testAddAllWithNullThrows() {
        assertThatThrownBy(() -> batchErrors.addAll(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testGetCompanyNames() {
        batchErrors.addError(new CustomerLoadBatchError("Corp B", "n", String.class, "v", "d"));
        batchErrors.addError(new CustomerLoadBatchError("Corp A", "n", String.class, "v", "d"));

        Set<String> names = batchErrors.getCompanyNames();
        assertThat(names).containsExactly("Corp A", "Corp B");
    }

    @Test
    void testGetErrorsByCompany() {
        batchErrors.addError(new CustomerLoadBatchError("Corp A", "f1", String.class, "v1", "d1"));
        batchErrors.addError(new CustomerLoadBatchError("Corp A", "f2", String.class, "v2", "d2"));

        List<CustomerLoadBatchError> errors = batchErrors.getErrorsByCompany("Corp A");
        assertThat(errors).hasSize(2);
    }

    @Test
    void testGetErrorsByCompanyNotFound() {
        List<CustomerLoadBatchError> errors = batchErrors.getErrorsByCompany("NonExistent");
        assertThat(errors).isNull();
    }

    @Test
    void testGetErrorStrings() {
        batchErrors.addError(new CustomerLoadBatchError("Corp A", "name", String.class, "v", "desc1"));
        batchErrors.addError(new CustomerLoadBatchError("Corp B", "email", String.class, "v", "desc2"));

        Set<String> errorStrings = batchErrors.getErrorStrings();
        assertThat(errorStrings).hasSize(2);
    }

    @Test
    void testMultipleErrorsSameCompany() {
        batchErrors.addError(new CustomerLoadBatchError("Corp A", "f1", String.class, "v1", "d1"));
        batchErrors.addError(new CustomerLoadBatchError("Corp A", "f2", String.class, "v2", "d2"));
        batchErrors.addError(new CustomerLoadBatchError("Corp A", "f3", String.class, "v3", "d3"));

        assertThat(batchErrors.getTotalErrors()).isEqualTo(3);
        assertThat(batchErrors.getCompaniesWithErrors()).isEqualTo(1);
    }
}
