package com.example.demobackend.jwt.userdetails;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
public class AuthUserDetails implements UserDetails {

    private static final long serialVersionUID = 8753012046635361642L;

    private Collection<? extends GrantedAuthority> authorities;

    private String password;

    private String username;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    private String uuid;

    public AuthUserDetails(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            String uuid
    ) {
        this(
                username,
                password,
                true,
                true,
                true,
                true,
                authorities,
                uuid
        );
    }

    public AuthUserDetails(
            String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities,
            String uuid
    ) {
        Assert.isTrue(
                username != null && !"".equals(username) && password != null,
                "Cannot pass null or empty values to constructor"
        );

        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        this.uuid = uuid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getUuid() {
        return this.uuid;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(
            Collection<? extends GrantedAuthority> authorities
    ) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet(
                new AuthUserDetails.AuthorityComparator()
        );
        Iterator var2 = authorities.iterator();

        while (var2.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority)var2.next();
            Assert.notNull(
                    grantedAuthority,
                    "GrantedAuthority list cannot contain any null elements"
            );
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        private static final long serialVersionUID = -1590831070697519922L;

        private AuthorityComparator() {}

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            } else {
                return g1.getAuthority() == null ?
                        1 : g1.getAuthority().compareTo(g2.getAuthority());
            }
        }
    }
}
