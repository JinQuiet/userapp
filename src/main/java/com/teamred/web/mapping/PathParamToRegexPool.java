package com.teamred.web.mapping;

import java.util.HashMap;
import java.util.Map;

public class PathParamToRegexPool {

    private static Map<String, String> pathParameterToRegexMapping = new HashMap<>();

    static {
        //here, "key" represents the pattern to replace and "value" is the pattern itself
        pathParameterToRegexMapping.put("%integer%", "[0-9]*");
        pathParameterToRegexMapping.put("%string%", "[a-z]*");
    }

    public static Map<String, String> getPathParamToRegexMapping() {
        return pathParameterToRegexMapping;
    }
}