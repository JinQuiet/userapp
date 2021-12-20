package com.teamred.validator.impl.web;

import java.util.List;

import com.teamred.validator.ValidationResult;
import com.teamred.validator.WebValidator;
import com.teamred.web.mapping.PathParamToRegexPool;
import com.teamred.web.mapping.RequestMapping;
import static com.teamred.web.util.WebUtils.*;

public class PathValidator implements WebValidator<String>{
    
    private String validationTarget;

    private List<RequestMapping> requestMapping = null;

    private ValidationResult validationResult;

    public PathValidator() {
        this.validationTarget = "web.error.path";
    }

    public PathValidator(String validationTarget, List<RequestMapping> requestMapping) {
        this.validationTarget = validationTarget;
        this.requestMapping = requestMapping;
    }    

    public ValidationResult validate(String path) {
        validationResult = ValidationResult.invalid("Wrong Entry Point Data :: " + path);

        if ((requestMapping!=null) && (path != null) &&
            (requestPartExists(requestMapping, 
            path, 
            (mapping)->path.matches(pathParamToRegex(PathParamToRegexPool.getPathParamToRegexMapping(), mapping.getPath()))))) 
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
