package com.bearclawvisions.enums;

public enum ApplicationRole {
    NONE,
    ADMIN,
    MODERATOR,
    SUPERVISOR,
    EMPLOYEE,
    CUSTOMER;

    // Companion compile-time string constants (must be manually kept in sync)
    public static final class Constants {
        private Constants() {}

        public static final String NONE = "NONE";
        public static final String ADMIN = "ADMIN";
        public static final String MODERATOR = "MODERATOR";
        public static final String SUPERVISOR = "SUPERVISOR";
        public static final String EMPLOYEE = "EMPLOYEE";
        public static final String CUSTOMER = "CUSTOMER";
    }
}
