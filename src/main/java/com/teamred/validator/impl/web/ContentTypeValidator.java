package com.teamred.validator.impl.web;

import java.util.List;

import com.teamred.validator.ValidationResult;
import com.teamred.validator.WebValidator;
import com.teamred.web.mapping.RequestMapping;
import static com.teamred.web.util.WebUtils.*;

public class ContentTypeValidator implements WebValidator<String>{
    
    private String validationTarget;

    private List<RequestMapping> requestMapping = null;

    private ValidationResult validationResult;

    public ContentTypeValidator() {
        this.validationTarget = "web.error.contentType";
    }

    public ContentTypeValidator(String validationTarget, List<RequestMapping> requestMapping) {
        this.validationTarget = validationTarget;
        this.requestMapping = requestMapping;
    }    

    public ValidationResult validate(String contentType) {
        validationResult = ValidationResult.invalid("Wrong Content Type :: " + contentType);

        if ((requestMapping != null) && (contentType != null) &&
            (requestPartExists(requestMapping, 
            contentType, 
            (mapping)->contentType.equals(mapping.getContentType()))))
            validationResult = ValidationResult.valid();            

        return validationResult;
    }

    public String getValidationTarget() {
        return validationTarget;
    }

    @Override
    public void setRequestMapping(List<RequestMapping> requestMapping) {
        this.requestMapping = requestMapping;
    }
}