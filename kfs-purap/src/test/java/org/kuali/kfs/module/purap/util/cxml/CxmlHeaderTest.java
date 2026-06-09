package org.kuali.kfs.module.purap.util.cxml;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class CxmlHeaderTest extends KfsUnitTestBase {

    @Test
    void defaultConstructorFieldsNull() {
        CxmlHeader header = new CxmlHeader();
        assertThat(header.getFromDomain()).isNull();
        assertThat(header.getFromIdentity()).isNull();
        assertThat(header.getFromType()).isNull();
        assertThat(header.getToDomain()).isNull();
        assertThat(header.getToIdentity()).isNull();
        assertThat(header.getToType()).isNull();
        assertThat(header.getSenderDomain()).isNull();
        assertThat(header.getSenderIdentity()).isNull();
        assertThat(header.getSenderType()).isNull();
        assertThat(header.getSenderUserAgent()).isNull();
    }

    @Test
    void setFromTwoArgs() {
        CxmlHeader header = new CxmlHeader();
        header.setFrom("DUNS", "12345");
        assertThat(header.getFromDomain()).isEqualTo("DUNS");
        assertThat(header.getFromIdentity()).isEqualTo("12345");
        assertThat(header.getFromType()).isNull();
    }

    @Test
    void setFromThreeArgs() {
        CxmlHeader header = new CxmlHeader();
        header.setFrom("DUNS", "12345", "Supplier");
        assertThat(header.getFromDomain()).isEqualTo("DUNS");
        assertThat(header.getFromIdentity()).isEqualTo("12345");
        assertThat(header.getFromType()).isEqualTo("Supplier");
    }

    @Test
    void setToTwoArgs() {
        CxmlHeader header = new CxmlHeader();
        header.setTo("NetworkId", "buyer1");
        assertThat(header.getToDomain()).isEqualTo("NetworkId");
        assertThat(header.getToIdentity()).isEqualTo("buyer1");
        assertThat(header.getToType()).isNull();
    }

    @Test
    void setToThreeArgs() {
        CxmlHeader header = new CxmlHeader();
        header.setTo("NetworkId", "buyer1", "Buyer");
        assertThat(header.getToDomain()).isEqualTo("NetworkId");
        assertThat(header.getToIdentity()).isEqualTo("buyer1");
        assertThat(header.getToType()).isEqualTo("Buyer");
    }

    @Test
    void setSenderTwoArgs() {
        CxmlHeader header = new CxmlHeader();
        header.setSender("DUNS", "S1");
        assertThat(header.getSenderDomain()).isEqualTo("DUNS");
        assertThat(header.getSenderIdentity()).isEqualTo("S1");
        assertThat(header.getSenderType()).isNull();
    }

    @Test
    void setSenderThreeArgs() {
        CxmlHeader header = new CxmlHeader();
        header.setSender("DUNS", "S1", "Marketplace");
        assertThat(header.getSenderDomain()).isEqualTo("DUNS");
        assertThat(header.getSenderIdentity()).isEqualTo("S1");
        assertThat(header.getSenderType()).isEqualTo("Marketplace");
    }

    @Test
    void sharedSecretSetters() {
        CxmlHeader header = new CxmlHeader();
        header.setFromSharedSecret("fromSecret");
        header.setToSharedSecret("toSecret");
        header.setSenderSharedSecret("senderSecret");
        assertThat(header.getFromSharedSecret()).isEqualTo("fromSecret");
        assertThat(header.getToSharedSecret()).isEqualTo("toSecret");
        assertThat(header.getSenderSharedSecret()).isEqualTo("senderSecret");
    }

    @Test
    void senderUserAgent() {
        CxmlHeader header = new CxmlHeader();
        header.setSenderUserAgent("KFS/6.0");
        assertThat(header.getSenderUserAgent()).isEqualTo("KFS/6.0");
    }

    @Test
    void toStringContainsFields() {
        CxmlHeader header = new CxmlHeader();
        header.setFrom("DUNS", "12345");
        String result = header.toString();
        assertThat(result).contains("DUNS").contains("12345");
    }
}
