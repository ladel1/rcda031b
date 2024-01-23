package com.eni.jeuxvideo.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eni.jeuxvideo.bo.Category;


public interface CategoryService {
	List<Category> getAllCategories();
}
