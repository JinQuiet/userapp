package com.teamred.web.mapping;

import java.util.Arrays;

public class RequestMapping {

/*
===========================эндпоинты===========================

    =======0001 - вернуть всех пользователей
    /users
    GET 

    =======0002  - создать нового пользователя
    /users
    POST

    =======0003 - открыть редактор существующего пользователя
    /users/%integer%
    GET 

    =======0004 - отредактировать пользователя
    /users/%integer%
    PUT 

    =======0005 - удалить пользователя
    /users/%integer%
    DELETE 

    =======0006 - открыть редактор создания нового пользователя
    /users/newuser
    GET

========эндпоинты=======
*/

    //name of the action bound to the mapping
    private String actionName;
    //path passed to the servlet. We have only 1 servlet. So it's just "path"
    private String path;
    //path passed to the servlet. We have only 1 servlet. So it's just "path"
    private String requestMethod;
    //application/json should work
    private String contentType;
    //pfth parameter like %integer% part in /users/%integer%
    private String[] pathParameters;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String[] getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(String[] pathParameters) {
        this.pathParameters = pathParameters;
    }

    @Override
    public String toString() {
        return "RequestMapping [actionName=" + actionName + ", contentType=" + contentType + ", path=" + path
                + ", pathParameters=" + Arrays.toString(pathParameters) + ", requestMethod=" + requestMethod + "]";
    }
   
}