package com.teamred.util;

public class PathParser {

    public static String[] getPathParameters(String path) {
        return parsePath(path);
    }

    public static String getPathEntryPoint(String path) {
        try {
            String[] pathTokens = parsePath(path);
            return pathTokens[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPathParameter(String path) {
        try {
            String[] pathTokens = parsePath(path);
            return pathTokens[1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    

    private static String[] parsePath(String path) {

        //remove leading and trailing spaces (there should be none)
        path = path.trim();
        //remove leading "/"
        if (path.startsWith("/")) path = path.replaceAll("^/+", "");
        //remove trailing "/"
        if (path.endsWith("/")) path = path.replaceAll("/+$", "");

        //Split path into tokens
        String[] pathTokens = path.split("/");

        return pathTokens;
    }
}