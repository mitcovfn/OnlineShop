package com.mfn.onlineshop.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mfn.onlineshop.dao.CategoryDAO;
import com.mfn.onlineshop.entity.Category;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.mfn.onlineshop");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");

	}

	@Test
	public void testAddCategory() {
		category = new Category();

		category.setName("Mobile");
		category.setDescription("Mobile description");
		category.setImageURL("mobile.png");

		assertEquals("Successfully added a category inside the table!", true, categoryDAO.add(category));

		category = new Category();

		category.setName("Laptop");
		category.setDescription("Laptop description");
		category.setImageURL("laptop.png");

		assertEquals("Successfully added a category inside the table!", true, categoryDAO.add(category));
	}

	@Test
	public void testGetCategory() {
		category = categoryDAO.get(1);

		category.setName("Mobile");

		assertEquals("Successfully fetched a single category from the table!", "Mobile", category.getName());
	}

	@Test
	public void testUpdateCategory() {
		category = categoryDAO.get(2);
		category.setName("TV");
		category.setDescription("TV description");
		category.setImageURL("tv.png");
		category.setActive(true);

		assertEquals("Successfully updated category from the table!", true, categoryDAO.update(category));
	}

	@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(2);

		assertEquals("Successfully deleted category from the table!", true, categoryDAO.delete(category));
	}

	@Test
	public void testListCategory() {

		assertEquals("Successfully listed category from the table!", 1, categoryDAO.list().size());
	}
}
