package com.example.pwpolicy.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
    private boolean isPasswordNeverExpire;
    private boolean enabled;
    private Timestamp lastPasswordChangeDTM;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "password_never_expire")
    public boolean isPasswordNeverExpire() {
        return isPasswordNeverExpire;
    }

    public void setPasswordNeverExpire(boolean passwordNeverExpire) {
        isPasswordNeverExpire = passwordNeverExpire;
    }

    @Basic
    @Column(name = "last_pw_change_dt")
    public Timestamp getLastPasswordChangeDTM() {
        return lastPasswordChangeDTM;
    }

    public void setLastPasswordChangeDTM(Timestamp lastPasswordChangeDTM) {
        this.lastPasswordChangeDTM = lastPasswordChangeDTM;
    }

    @Basic
    @Column(name = "active")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
