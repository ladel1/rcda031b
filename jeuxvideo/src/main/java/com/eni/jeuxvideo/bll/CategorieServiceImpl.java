package com.eni.jeuxvideo.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eni.jeuxvideo.bo.Category;
import com.eni.jeuxvideo.dal.CategoryDao;
@Service
public class CategorieServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> getAllCategories() {		
		return categoryDao.findAll();
	}

	@Override
	public void ajouterCategory(Category category) {
		categoryDao.save(category);
	}
	

}
