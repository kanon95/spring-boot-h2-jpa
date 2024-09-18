package com.practice.springh2jpa.component.member.store.repository;

import com.practice.springh2jpa.component.member.store.jpo.MemberJpo;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberJpo, Long>  {


}
