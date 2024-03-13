package io.at.tinyurl.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.at.tinyurl.accountservice.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Account getAccountByUsernameAndIsDeleted(String username, boolean isDeleted);
    
}
