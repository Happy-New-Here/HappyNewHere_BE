package com.example.HappyNewHere.repository;
import com.example.HappyNewHere.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
    //userId 또는 닉네임과 일치하는 유저 모두 불러오기
    // 일부분만 일치해도 불러오기
    Page<Account> findByUserIdContainingOrNicknameContaining(String userId, String nickname, Pageable pageable);
    Optional<Account> findByUserId(String userId);
}
