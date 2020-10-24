package ua.ozzy.apiback.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.ozzy.apiback.model.Admin;
import ua.ozzy.apiback.model.BotApiInfo;
import ua.ozzy.apiback.model.SystemUser;

public class AuthUtil {

    private AuthUtil() {}

    public static Admin getCurrentAdmin() {
        return getCurrentSystemUser(Admin.class);
    }

    public static BotApiInfo getCurrentBotApiInfo() {
        return getCurrentSystemUser(BotApiInfo.class);
    }

    private static <T extends SystemUser> T getCurrentSystemUser(Class<T> sysUserClass) {
        SecurityContext sc = SecurityContextHolder.getContext();
        Object principal = sc.getAuthentication().getPrincipal();
        return sysUserClass.cast(principal);
    }

}
