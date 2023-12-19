package com.example.HappyNewHere.repository;

import com.example.HappyNewHere.domain.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Long> {
    List<Messages> findByReceiver(String receiver);
}
