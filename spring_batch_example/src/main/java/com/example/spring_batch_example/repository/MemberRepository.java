package com.example.spring_batch_example.repository;

import com.example.spring_batch_example.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    List<MemberEntity> findByStatusFalse();
    List<MemberEntity> findByStatusTrue();

}
