package com.tobuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tobuz.model.Testimonial;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {

}
