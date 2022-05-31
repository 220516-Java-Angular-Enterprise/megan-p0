package com.revature.project0.services;

import java.util.List;

import com.revature.project0.daos.CategoryDAO;
import com.revature.project0.models.Category;
import com.revature.project0.util.custom_exceptions.InvalidSQLException;
public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> getAllCat() {
        return categoryDAO.getAll();
    }

}
