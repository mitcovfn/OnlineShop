package com.mfn.onlineshop.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mfn.onlineshop.dao.ProductDAO;
import com.mfn.onlineshop.entity.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product get(int id) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM product", Product.class).list();
	}

	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
		product.setActive(false);

		try {
			return this.update(product);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> listActiveProducts() {
		String query = "FROM product WHERE active = :active";
		return sessionFactory.getCurrentSession()
				.createQuery(query, Product.class).setParameter("active", true).list();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String query = "FROM product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory.getCurrentSession()
				.createQuery(query, Product.class)
				.setParameter("active", true)
				.setParameter("categoryId", categoryId).list();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String query = "FROM product WHERE active = :active ORDER BY id";
		return sessionFactory.getCurrentSession()
				.createQuery(query, Product.class)
				.setParameter("active", true)
				.setFirstResult(0).setMaxResults(count).list();
	}

}
