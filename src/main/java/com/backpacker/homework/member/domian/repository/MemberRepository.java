package com.backpacker.homework.member.domian.repository;

import com.backpacker.homework.member.domian.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String email);
}
