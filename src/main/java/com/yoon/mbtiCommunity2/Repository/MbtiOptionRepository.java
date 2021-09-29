package com.yoon.mbtiCommunity2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoon.mbtiCommunity2.Entity.MbtiOption;

@Repository
public interface MbtiOptionRepository extends JpaRepository<MbtiOption,Integer>{

}
