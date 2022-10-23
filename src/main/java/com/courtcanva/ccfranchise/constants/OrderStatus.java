package com.courtcanva.ccfranchise.constants;

public enum OrderStatus {
    UNASSIGNED("unassigned"),
    ASSIGNED_PENDING("open"),
    ACCEPTED("accepted"),
    COMPLETED("complete"),
    CANCELED("canceled");

    final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
