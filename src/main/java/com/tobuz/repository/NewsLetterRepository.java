package com.tobuz.repository;

import com.tobuz.model.NewsLetterSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsLetterRepository extends JpaRepository<NewsLetterSubscription, Long> {

}
