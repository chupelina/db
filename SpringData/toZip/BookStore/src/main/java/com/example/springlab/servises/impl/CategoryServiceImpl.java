package com.example.springlab.servises.impl;

import com.example.springlab.constants.GlobalConstants;
import com.example.springlab.entities.Category;
import com.example.springlab.repositories.CategoryRepo;
import com.example.springlab.servises.CategoryService;
import com.example.springlab.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, FileUtil fileUtil) {
        this.categoryRepo = categoryRepo;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if(this.categoryRepo.count()!=0){
            return;
        }
        String[] fileContent =
                this.fileUtil.readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);
        Arrays.stream(fileContent).forEach(r->{
            Category current = new Category(r);
            this.categoryRepo.saveAndFlush(current);
        });
    }
    @Override
    public Category getCategoryById(long id){
        return this.categoryRepo.getOne(id);
    }
    @Override
    public  int countCategories(){
        return (int)this.categoryRepo.count();
    }
}
