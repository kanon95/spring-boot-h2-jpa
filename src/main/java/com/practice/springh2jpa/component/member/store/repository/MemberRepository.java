package com.practice.springh2jpa.component.member.store.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.practice.springh2jpa.component.member.store.jpo.MemberJpo;


@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<MemberJpo, Long>  {
  @Query(value = "SELECT * FROM t_member WHERE name = :name",
      countQuery = "SELECT * FROM t_member WHERE name = :name",
      nativeQuery = true)
  List<MemberJpo> findAllByName(String name, PageRequest pageRequest);

}

