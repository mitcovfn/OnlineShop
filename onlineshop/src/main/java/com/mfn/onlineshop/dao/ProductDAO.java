package com.mfn.onlineshop.dao;

import java.util.List;

import com.mfn.onlineshop.entity.Product;

public interface ProductDAO {

	Product get(int id);
	List<Product> list();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);

	// business methods
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
}
