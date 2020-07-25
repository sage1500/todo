package com.example.security.domain.service.account;

import com.example.security.domain.model.Account;
import com.example.security.domain.repository.account.AccountRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountSharedServiceImpl implements AccountSharedService {
    private final AccountRepository accountRepository;

    @Transactional(readOnly=true)
    @Override
    public Account findOne(String username) {
        Account account = accountRepository.findById(username).orElse(null);
        if (account == null) {
            throw new ResourceNotFoundException("The given account is not found! username="
                    + username);
        }
        return account;
    }

}