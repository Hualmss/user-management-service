package com.pragma.powerup.domain.util.constans;

import java.util.Map;

public class Constants {
    public static final Long ROL_ADMIN_IDENTIFIER = 1L;
    public static final Long ROL_OWNER_IDENTIFIER = 2L;
    public static final Long ROL_EMPLOYEE_IDENTIFIER = 3L;
    public static final Long ROL_CLIENT_IDENTIFIER = 4L;

    public static final Map<Long, String> MAP_ROLES = Map.of(
            ROL_ADMIN_IDENTIFIER, "ADMIN",
            ROL_OWNER_IDENTIFIER, "OWNER",
            ROL_EMPLOYEE_IDENTIFIER, "EMPLOYEE",
            ROL_CLIENT_IDENTIFIER, "CLIENT");

}
