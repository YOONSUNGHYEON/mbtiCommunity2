package com.yoon.mbtiCommunity2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoon.mbtiCommunity2.Entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

	Member findById(String id);

	Member findByIdAndPassword(String id, String password);

}
