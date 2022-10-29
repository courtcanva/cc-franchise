package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.OrderStatus;

public class OrderUtil {

    private OrderUtil() {
    }

    public static boolean statusValid(String test) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.value.equals(test)) {
                return true;
            }
        }
        return false;
    }
}
