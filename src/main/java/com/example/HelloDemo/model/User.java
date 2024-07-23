package com.example.HelloDemo.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "^[A-Z][a-zA-Z]*( [A-Z][a-zA-Z]*)*$", message = "Full name must start with an uppercase letter and contain spaces between names if necessary")
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Pattern(regexp = "^[A-Z][a-zA-Z0-9]*$", message = "Username must start with an uppercase letter")
    @NotBlank(message = "Username is required")
    private String username;

   //  Simplified regex pattern for password validation
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9]*$", message = "Password must start with an uppercase letter and contain at least one digit")
    @NotBlank(message = "Password is required")
    private String password;

    @Pattern(regexp = "^[A-Z]+$", message = "Roles must be in uppercase letters")
    @NotBlank(message = "Roles are required")
    private String roles;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return java.util.Collections.emptyList(); // Modify according to your roles implementation
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
