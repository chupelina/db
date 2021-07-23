package com.softuni.springintroex.services;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Category;
import com.softuni.springintroex.repozitory.CategoryRepo;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final FileUtil fileUtil;
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(FileUtil fileUtil, CategoryRepo categoryRepo) {
        this.fileUtil = fileUtil;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepo.count() == 0) {
            String[] lines = fileUtil.readFileContent
                    (GlobalConstants.CATEGORIES_FILE_PATH);
            for (String line : lines) {
                Category category = new Category(line);
                categoryRepo.saveAndFlush(category);
            }
        }
    }
}
