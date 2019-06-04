package cn.net.liaowei.sc.product.repository;

import cn.net.liaowei.sc.product.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByStatus() {
        List<ProductInfo> productInfoList = productInfoRepository.findByStatus(Short.valueOf("0"));
        Assert.assertNotNull(productInfoList);

        Page<ProductInfo> productInfoPage = productInfoRepository.findByStatus(Short.valueOf("0"), PageRequest.of(0, 3, Sort.Direction.DESC, "productId"));
        Assert.assertNotNull(productInfoPage);
    }

    @Test
    public void findByStatusQuery() {
        Page<String> resultList = productInfoRepository.findByStatusQuery(Short.valueOf("0"), PageRequest.of(0, 3, Sort.Direction.DESC, "productId"));
        Assert.assertNotNull(resultList);
    }

    @Test
    public void findAllByExample() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setStatus(Short.valueOf("0"));
        productInfo.setCategoryType(Short.valueOf("1"));
        List<ProductInfo> productInfoList = productInfoRepository.findAll(Example.of(productInfo));
        Assert.assertNotNull(productInfoList);
    }

    @Test
    public void findByProductIdIn() {
        Page<ProductInfo> productInfoList = productInfoRepository.findByProductIdIn(Arrays.asList(1, 3, 5, 7), null);
        Assert.assertEquals(4, productInfoList.getTotalPages());
    }
}