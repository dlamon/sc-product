package cn.net.liaowei.sc.product.service;

import cn.net.liaowei.sc.product.domain.ProductCategory;

import java.util.List;

/**
 * @author liaowei
 */
public interface CategoryService {
    /**
     * 通过类别类型列表查找类别信息列表
     * @param categoryTypeList 类别类型列表
     * @return 类别信息列表
     */
    public List<ProductCategory> listCategoryIn(List<Integer> categoryTypeList);
}
