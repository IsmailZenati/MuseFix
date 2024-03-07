package services;

import java.security.Principal;

public class RolePrincipal implements Principal {
    private final String roleName;

    public RolePrincipal(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getName() {
        return roleName;
    }
}
