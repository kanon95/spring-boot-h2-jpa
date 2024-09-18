package com.practice.springh2jpa.component.member.store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.practice.springh2jpa.component.member.store.jpo.MemberJpo;


@Transactional(readOnly = true)
public interface MemberRepository extends CrudRepository<MemberJpo, Long>  {


}
