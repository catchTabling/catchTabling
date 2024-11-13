package com.example.catchTabling.Repository;

import com.example.catchTabling.Model.Entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {

}
