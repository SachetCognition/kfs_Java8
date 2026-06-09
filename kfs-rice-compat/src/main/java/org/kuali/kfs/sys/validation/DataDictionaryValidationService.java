package org.kuali.kfs.sys.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataDictionaryValidationService {

    private final Validator validator;

    public DataDictionaryValidationService() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public Map<String, String> validate(Object businessObject) {
        Map<String, String> errors = new HashMap<String, String>();
        if (businessObject == null) {
            return errors;
        }
        Set<ConstraintViolation<Object>> violations = validator.validate(businessObject);
        for (ConstraintViolation<Object> violation : violations) {
            errors.put(
                violation.getPropertyPath().toString(),
                violation.getMessage()
            );
        }
        return errors;
    }

    public boolean isValid(Object businessObject) {
        if (businessObject == null) {
            return true;
        }
        return validator.validate(businessObject).isEmpty();
    }

    public Map<String, String> validateProperty(Object businessObject, String propertyName) {
        Map<String, String> errors = new HashMap<String, String>();
        if (businessObject == null) {
            return errors;
        }
        Set<ConstraintViolation<Object>> violations =
            validator.validateProperty(businessObject, propertyName);
        for (ConstraintViolation<Object> violation : violations) {
            errors.put(propertyName, violation.getMessage());
        }
        return errors;
    }
}
