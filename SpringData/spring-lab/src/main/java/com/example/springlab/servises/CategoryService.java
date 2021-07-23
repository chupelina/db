package com.example.springlab.servises;

import com.example.springlab.entities.Category;

import java.io.IOException;

public interface CategoryService  {
    void seedCategories() throws IOException;
    Category getCategoryById(long id);
    int countCategories();

}
