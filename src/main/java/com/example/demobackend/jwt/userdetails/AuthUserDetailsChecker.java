package com.example.demobackend.jwt.userdetails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.util.Assert;

@Slf4j
public class AuthUserDetailsChecker implements UserDetailsChecker, MessageSourceAware {

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    @Override
    public void setMessageSource(MessageSource messageSource) {
        Assert.notNull(messageSource, "messageSource cannot be null");
        this.messages = new MessageSourceAccessor(messageSource);
    }

    @Override
    public void check(UserDetails userDetails) {
        if (!userDetails.isAccountNonLocked()) {
            log.debug("Failed to authenticate since user account is locked");
            throw new LockedException(
                    this.messages.getMessage(
                            "AccountStatusUserDetailsChecker.locked",
                            "User account is locked"
                    )
            );
        } else if (!userDetails.isEnabled()) {
            log.debug("Failed to authenticate since user account is disabled");
            throw new DisabledException(
                    this.messages.getMessage(
                            "AccountStatusUserDetailsChecker.disabled",
                            "User is disabled"
                    )
            );
        } else if (!userDetails.isAccountNonExpired()) {
            log.debug("Failed to authenticate since user account is expired");
            throw new AccountExpiredException(
                    this.messages.getMessage(
                            "AccountStatusUserDetailsChecker.expired",
                            "User account has expired"
                    )
            );
        } else if (!userDetails.isCredentialsNonExpired()) {
            log.debug("Failed to authenticate since user account credentials have expired");
            throw new CredentialsExpiredException(
                    this.messages.getMessage(
                            "AccountStatusUserDetailsChecker.credentialsExpired",
                            "User credentials have expired"
                    )
            );
        }
    }

}