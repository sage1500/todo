package com.example.security.domain.repository.account;

import java.util.Optional;

import com.example.security.domain.model.Account;

public interface AccountRepository {
    Optional<Account> findById(String username);
}