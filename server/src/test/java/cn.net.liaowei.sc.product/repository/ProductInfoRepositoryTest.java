package cn.net.liaowei.sc.product.repository;

import cn.net.liaowei.sc.product.domain.dos.ProductInfoDO;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByStatus() {
        List<ProductInfoDO> productInfoDOList = productInfoRepository.findByStatus(Short.valueOf("0"));
        Assert.assertNotNull(productInfoDOList);

        Page<ProductInfoDO> productInfoDOPage = productInfoRepository.findByStatus(Short.valueOf("0"), PageRequest.of(0, 3, Sort.Direction.DESC, "productId"));
        Assert.assertNotNull(productInfoDOPage);
    }

    @Test
    public void findByStatusQuery() {
        Page<String> resultList = productInfoRepository.findByStatusQuery(Short.valueOf("0"), PageRequest.of(0, 3, Sort.Direction.DESC, "productId"));
        Assert.assertNotNull(resultList);
    }

    @Test
    public void findAllByExample() {
        ProductInfoDO productInfoDO = new ProductInfoDO();
        productInfoDO.setStatus(Short.valueOf("0"));
        productInfoDO.setCategoryType(Short.valueOf("1"));
        List<ProductInfoDO> productInfoDOList = productInfoRepository.findAll(Example.of(productInfoDO));
        Assert.assertNotNull(productInfoDOList);
    }

    @Test
    public void findByProductIdIn() {
        Page<ProductInfoDO> productInfoDOPage = productInfoRepository.findByProductIdIn(Arrays.asList(1, 3, 5, 7), null);
        Assert.assertEquals(4, productInfoDOPage.getContent().size());
    }
}