package edu.hit.LvWenXuan.dao;

import edu.hit.LvWenXuan.domain.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 查询所有
     * @return
     */
    public List<Category> findAll();
}
