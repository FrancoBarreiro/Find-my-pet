package com.findmypet.utils;

public enum NotificationType {

    LIKE,
    COMMENT;

    public static boolean isValidType(String value) {
        for (NotificationType type : NotificationType.values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
