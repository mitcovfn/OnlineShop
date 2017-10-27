package com.mfn.onlineshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mfn.onlineshop.dao.CategoryDAO;
import com.mfn.onlineshop.dao.ProductDAO;
import com.mfn.onlineshop.entity.Category;
import com.mfn.onlineshop.entity.Product;
import com.mfn.onlineshop.util.FileUploadUtility;
import com.mfn.onlineshop.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	ProductDAO productDAO;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");

		Product newProduct = new Product();
		newProduct.setSupplierId(1);
		newProduct.setActive(true);
		mv.addObject("product", newProduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product Saved Successfully!");
			}
			if (operation.equals("category")) {
				mv.addObject("message", "Category added Successfully!");
			}

		}

		return mv;
	}

	@RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable("id") int id) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");

		Product newProduct = productDAO.get(id);

		mv.addObject("product", newProduct);

		return mv;
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSave(@Valid @ModelAttribute("product") Product mProduct, BindingResult bindingResult,
			Model model, HttpServletRequest request) {

		if (mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, bindingResult);
		} else {
			if (!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, bindingResult);
			}
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");

			return "page";
		}

		if (mProduct.getId() == 0) {
			productDAO.add(mProduct);
		} else {
			productDAO.update(mProduct);
		}

		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		return "redirect:/manage/products?operation=product";
	}

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable("id") int id) {

		Product product = productDAO.get(id);

		boolean isActive = product.isActive();
		product.setActive(!product.isActive());
		productDAO.update(product);

		return (isActive) ? "You have successfully deactivated the product with ID " + product.getId()
				: "You have successfully activated the product with ID " + product.getId();
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String addNewCategory(@ModelAttribute Category category) {

		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
	}

	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}

	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}

}
