package cn.net.liaowei.sc.product.service.impl;

import cn.net.liaowei.sc.product.domain.dos.ProductCategoryDO;
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
    public List<ProductCategoryDO> listCategoryIn(List<Short> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
