package com.eni.jeuxvideo.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eni.jeuxvideo.bo.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
