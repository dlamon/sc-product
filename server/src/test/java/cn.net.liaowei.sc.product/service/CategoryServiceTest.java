package cn.net.liaowei.sc.product.service;

import cn.net.liaowei.sc.product.domain.dos.ProductCategoryDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    @Test
    public void listCategoryIn() {
        List<ProductCategoryDO> productCategoryDOList = categoryService.listCategoryIn(Arrays.asList(new Short("1"), new Short("2")));
        Assert.assertEquals(2, productCategoryDOList.size());
    }
}