package com.software.dpweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.software.dpweb.entity.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long>{

	@Query("select i from Instructor i left join i.courses where i.id =:insId  ")
	Instructor getInstructorDataById(@Param("insId") Long insId);
	
}
