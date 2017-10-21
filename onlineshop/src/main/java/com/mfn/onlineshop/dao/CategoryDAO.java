package com.mfn.onlineshop.dao;

import java.util.List;

import com.mfn.onlineshop.entity.Category;

public interface CategoryDAO {
	
	Category get(int id);
	List<Category> list();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);

}
