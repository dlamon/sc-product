package cn.net.liaowei.sc.product.repository;

import cn.net.liaowei.sc.product.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByCategoryIdIn() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findByCategoryIdIn(Arrays.asList(1, 3));
        Assert.assertEquals(2, productCategoryList.size());
    }
}