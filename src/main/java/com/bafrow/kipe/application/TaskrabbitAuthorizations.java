package com.bafrow.kipe.application;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import com.bafrow.authentication.application.AuthenticatedUser;
import com.bafrow.authentication.domain.Role;
import com.bafrow.authentication.domain.Roles;
import com.bafrow.authentication.domain.Username;
import com.bafrow.kipe.domain.Action;
import com.bafrow.kipe.domain.Resource;
import com.bafrow.kipe.domain.RolesAccesses;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class TaskrabbitAuthorizations {

  private final RolesAccesses accesses;

  public TaskrabbitAuthorizations(Collection<RolesAccesses> accesses) {
    this.accesses = accesses.stream().reduce(RolesAccesses.EMPTY, RolesAccesses::merge);
  }

  public boolean allAuthorized(Authentication authentication, String action, Resource resource) {
    if (missingAuthenticationInfo(authentication, action, resource)) {
      return false;
    }

    return accesses.allAuthorized(roles(authentication), action(action), resource);
  }

  public Username getUsername(Authentication authentication) {
    return new Username(AuthenticatedUser.readPrincipal(authentication));
  }

  public boolean specificAuthorized(Authentication authentication, String action, Resource resource) {
    if (missingAuthenticationInfo(authentication, action, resource)) {
      return false;
    }

    return accesses.specificAuthorized(roles(authentication), action(action), resource);
  }

  private Roles roles(Authentication authentication) {
    Set<Role> role = authentication
      .getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority)
      .map(Role::from)
      .collect(Collectors.toSet());

    return new Roles(role);
  }

  private Action action(String action) {
    return new Action(action);
  }

  private boolean missingAuthenticationInfo(Authentication authentication, String action, Resource resource) {
    return authentication == null || StringUtils.isBlank(action) || resource == null;
  }
}
