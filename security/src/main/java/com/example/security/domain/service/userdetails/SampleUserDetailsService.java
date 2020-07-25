package com.example.security.domain.service.userdetails;

import com.example.security.domain.model.Account;
import com.example.security.domain.service.account.AccountSharedService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleUserDetailsService implements UserDetailsService {
    private final AccountSharedService accountSharedService;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = accountSharedService.findOne(username);
            return new SampleUserDetails(account);
        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException("user not found", e);
        }
    }
    
}