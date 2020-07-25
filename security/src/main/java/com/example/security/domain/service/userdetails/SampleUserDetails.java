package com.example.security.domain.service.userdetails;

import com.example.security.domain.model.Account;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=true)
public class SampleUserDetails extends User {
    private static final long serialVersionUID = 1L;

    private final Account account;

    public SampleUserDetails(final Account account) {
        super(account.getUsername(), account.getPassword(), AuthorityUtils
                .createAuthorityList("ROLE_USER"));
        this.account = account;
    }
}