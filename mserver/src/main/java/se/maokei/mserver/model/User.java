package se.maokei.mserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table("users")
public class User implements UserDetails, Persistable<UUID> {
    @Id
    private UUID id;
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @JsonIgnore
    private String password;
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @JsonIgnore
    private List<@NotBlank(message = "Roles should not be empty") Role> roles;
    @JsonIgnore
    private Boolean enabled;
    @Transient
    @JsonIgnore
    private boolean isNew;

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public User(String username, String password, String email, List<Role> roles, Boolean enabled, boolean isNew) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.enabled = enabled;
        this.isNew = isNew;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role r : this.roles) {
            authorities.add(new SimpleGrantedAuthority(r.toString()));
        }
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isNew() {
        boolean result = Objects.isNull(id);
        this.id = result ? UUID.randomUUID() : this.id;
        return result;
    }
}