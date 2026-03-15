package com.bearclawvisions.enums;

import org.junit.jupiter.api.Test;

public class ApplicationRoleTests {

    @Test
    void roleConstantsMatchEnumValues() {
        assert ApplicationRole.Constants.NONE.equals(ApplicationRole.NONE.toString());
        assert ApplicationRole.Constants.ADMIN.equals(ApplicationRole.ADMIN.toString());
        assert ApplicationRole.Constants.MODERATOR.equals(ApplicationRole.MODERATOR.toString());
        assert ApplicationRole.Constants.SUPERVISOR.equals(ApplicationRole.SUPERVISOR.toString());
        assert ApplicationRole.Constants.EMPLOYEE.equals(ApplicationRole.EMPLOYEE.toString());
        assert ApplicationRole.Constants.CUSTOMER.equals(ApplicationRole.CUSTOMER.toString());
    }
}
