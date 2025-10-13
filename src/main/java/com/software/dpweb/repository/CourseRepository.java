package com.software.dpweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.software.dpweb.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
