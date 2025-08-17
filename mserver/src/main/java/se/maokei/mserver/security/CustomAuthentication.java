package se.maokei.mserver.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthentication implements Authentication {
    private final String userId;
    private final String username;
    private final String credentials;
    private final Collection<? extends GrantedAuthority> authorities;
    private boolean isAuthenticated;


    public CustomAuthentication(String userId, String username, String credentials, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.credentials = credentials;
        this.authorities = authorities;
        this.setAuthenticated(true);
    }

    @Override
    public String getName() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getDetails() {
        return this.getUserId();
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }


    /*
    When authenticating a user, create an instance of CustomAuthentication and set it as the authentication object:


CopyInsert
SecurityContextHolder.getContext().setAuthentication(
    new CustomAuthentication(userId, username)
);

    Then, in your controller or service, you can access the user ID from the Principal object:



    @GetMapping("/profile")
public Mono<String> profile(@AuthenticationPrincipal CustomAuthentication authentication) {
    String userId = authentication.getUserId();
    // use the user ID
}
     */
}