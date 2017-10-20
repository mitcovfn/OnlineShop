package com.mfn.onlineshop.dao;

import java.util.List;

import com.mfn.onlineshop.entity.Category;

public interface CategoryDAO {
	
	List<Category> list();
	Category get(int id);

}
