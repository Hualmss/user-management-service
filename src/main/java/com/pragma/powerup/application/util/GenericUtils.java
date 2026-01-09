package com.pragma.powerup.application.util;

import com.pragma.powerup.domain.util.constans.Constants;
import org.springframework.stereotype.Component;

@Component
public class GenericUtils {

    public static String getRoleName(long id){
        return Constants.MAP_ROLES.get(id);
    }


}
