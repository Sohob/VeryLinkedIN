package com.Account.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    public Account findByUsername(String username);
   // public void deleteByUsername(String username);
}


