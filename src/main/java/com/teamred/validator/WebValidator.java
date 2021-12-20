package com.teamred.validator;

import java.util.List;

import com.teamred.web.mapping.RequestMapping;

public interface WebValidator<T> extends Validator<T>{

    public void setRequestMapping(List<RequestMapping> req);

}