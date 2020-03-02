package com.alshevskaya.cleaningcompany.command;

public class Router {
    private String pagePath;
    private RouteType type;

    public Router() {
    }

    public Router(String pagePath, RouteType type) {
        this.pagePath = pagePath;
        this.type = type;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }


    public void setType(RouteType type) {
        this.type = type;
    }


    public String getPagePath() {
        return pagePath;
    }


    public RouteType getType() {
        return type;
    }
}
