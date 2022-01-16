package com.github.veronicazzzz.storageserverclient.Enum;

public enum Routes {
    FILES_INFO ("/api/file-info"),
    FILE       ("/api/file");

    private String route;

    Routes(String route){
        this.route = route;
    }
    public String getRoute() {
        return route;
    }
}
