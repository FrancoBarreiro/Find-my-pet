package com.findmypet.utils;

public enum PostType {
    LOST,
    FOUND,
    FOR_ADOPTION;

    public static boolean isValidType(String value) {
        for (PostType type : PostType.values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
