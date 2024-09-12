package edu.hit.LvWenXuan.service.Impl;

import edu.hit.LvWenXuan.dao.CategoryDao;
import edu.hit.LvWenXuan.dao.Impl.CategoryDaoImpl;
import edu.hit.LvWenXuan.domain.Category;
import edu.hit.LvWenXuan.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
