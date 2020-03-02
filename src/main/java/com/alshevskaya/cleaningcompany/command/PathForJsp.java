package com.alshevskaya.cleaningcompany.command;

public enum PathForJsp {
    INDEX("/index.jsp"),
    GUEST("/jsp/guest.jsp"),
    LOGIN("/jsp/login.jsp"),
    CLIENT_CABINET("/jsp/client/main.jsp"),
    CLEANER_CABINET("/jsp/cleaner/cleanerCabinet.jsp"),
    ADMIN_CABINET("/jsp/admin/admin.jsp"),
    ERROR("/jsp/error/error.jsp"),
    SIGNUP("/jsp/signup.jsp"),
    SIGNUP_STAFF("/jsp/admin/signupstaff.jsp"),
    CHANGE_PASSWORD("/jsp/changepassword.jsp"),
    ADD_SERVICE("/jsp/admin/addService.jsp"),
    ADD_ORDER("/jsp/admin/addOrder.jsp"),
    UPDATE_ORDER("/jsp/admin/updateOrder.jsp"),
    DELETE_ORDER("/jsp/admin/deleteOrder.jsp"),
    DELETE_SERVICE("/jsp/admin/deleteService.jsp"),
    SHOW_SERVICES("/jsp/client/showServices.jsp"),
    SHOW_ORDERS("/jsp/admin/showOrders.jsp"),
    UPDATE_PERSONAL_DATA("/jsp/client/updatePersonalData.jsp"),
    UPDATE_STAFF_PERSONAL_DATA("/jsp/cleaner/updateStaffPersonalData.jsp"),
    SHOW_ORDERS_BY_CLIENT_ID("/jsp/client/showOrdersByClientId.jsp"),
    SHOW_ORDERS_BY_CLEANER_ID("/jsp/cleaner/showOrdersByCleanerId.jsp"),
    UPDATE_SERVICE("/jsp/admin/updateService.jsp");

    private String path;

    PathForJsp(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
