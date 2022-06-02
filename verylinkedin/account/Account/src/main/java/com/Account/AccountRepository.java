package com.Account;

import com.Account.classes.Account;
import com.Account.classes.FieldOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    public Account findByUsername(String username);

    List<Account> findByFieldOfInterestAndIsCompany(FieldOfInterest fieldOfInterest,boolean isCompany);


}


