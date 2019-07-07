package com.plivo.assignment.repository;

import com.plivo.assignment.entity.*;

import org.springframework.data.jpa.repository.*;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    public Account findByUserName(String userName);
}

