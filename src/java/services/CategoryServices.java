/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoryDB;
import java.util.List;
import models.Category;

/**
 *
 * @author gurwi
 */
public class CategoryServices {

    public Category get(String categoryName) {
        CategoryDB categoriesdb = new CategoryDB();
        Category category = categoriesdb.get(categoryName);
        return category;
    }

    public List<Category> getAll() throws Exception {
        CategoryDB categoriesdb = new CategoryDB();
        List<Category> categories = categoriesdb.getAll();
        return categories;
    }

    public void insert(String categoryName) throws Exception {
        int categoryId = 0;
        Category category = new Category(categoryId, categoryName);
        CategoryDB categoriesdb = new CategoryDB();
        categoriesdb.insert(category);
    }

    public void update(int categoryId, String categoryName) throws Exception {
        CategoryDB categoriesdb = new CategoryDB();
        Category category = categoriesdb.get(categoryId);
        category.setCategoryName(categoryName);
        categoriesdb.update(category);
    }

}
