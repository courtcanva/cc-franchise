package com.courtcanva.ccfranchise.constants;

public enum OrderStatus {
    UNASSIGNED("unassigned"),
    ASSIGNED_PENDING("open"),
    ACCEPTED("accepted"),
    COMPLETED("complete"),
    CANCELED("canceled");

    public final String value;

    OrderStatus(final String value) {
        this.value = value;
    }

}
