package com.example.HappyNewHere.repository;

import com.example.HappyNewHere.domain.Account;
import com.example.HappyNewHere.domain.Calender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalenderRepository extends JpaRepository<Calender, Account> {
}
