package com.mindtree.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.springsecurity.model.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    
	@Query(value = "select * from NOTICE_DETAILS",nativeQuery = true)
	List<Notice> findAllActiveNotices();
	

}
