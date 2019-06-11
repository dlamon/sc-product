package cn.net.liaowei.sc.product.service.impl;

import cn.net.liaowei.sc.product.domain.ProductCategory;
import cn.net.liaowei.sc.product.repository.ProductCategoryRepository;
import cn.net.liaowei.sc.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiaoWei
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private ProductCategoryRepository productCategoryRepository;

    public CategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<ProductCategory> listCategoryIn(List<Short> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
