package com.plivo.assignment.repository;

import com.plivo.assignment.entity.*;

import org.springframework.data.jpa.repository.*;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

    @Query("SELECT number from PhoneNumber phoneNumber where phoneNumber.account = ?1 and phoneNumber.number=?2")
    String findNumberByLoggedInUser(Account account,String number);
}
