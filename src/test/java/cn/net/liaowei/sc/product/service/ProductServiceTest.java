package cn.net.liaowei.sc.product.service;

import cn.net.liaowei.sc.product.domain.dos.ProductInfoDO;
import cn.net.liaowei.sc.product.domain.dto.DecreaseQuotaDTO;
import cn.net.liaowei.sc.product.exception.SCException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private ProductService productService;

    @Test
    public void listAllOnSaleProduct() {
        Page<ProductInfoDO> productInfoDOPage = productService.listAllOnSaleProduct(null);
        Assert.assertNotNull(productInfoDOPage);
    }

    @Test
    public void listProductIn() {
        Page<ProductInfoDO> productInfoDOPage = productService.listProductIn(Arrays.asList(1, 3, 5, 7), null);
        Assert.assertNotNull(productInfoDOPage);
    }

    @Test
    public void decreaseQuota_01() {
        // 正常测试
        List<DecreaseQuotaDTO> decreaseQuotaInputList = new ArrayList<>();
        DecreaseQuotaDTO decreaseQuotaDTO = new DecreaseQuotaDTO(1, new BigDecimal("3000.00"));
        decreaseQuotaInputList.add(decreaseQuotaDTO);
        decreaseQuotaDTO = new DecreaseQuotaDTO(2, new BigDecimal("5000.00"));
        decreaseQuotaInputList.add(decreaseQuotaDTO);
        productService.decreaseQuota(decreaseQuotaInputList);
    }

    @Test
    public void decreaseQuota_02() {
        // 购买金额超过最大购买金额测试
        thrown.expect(SCException.class);
        thrown.expectMessage("购买金额大于产品最大购买金额");
        List<DecreaseQuotaDTO> decreaseQuotaInputList = new ArrayList<>();
        decreaseQuotaInputList = new ArrayList<>();
        DecreaseQuotaDTO decreaseQuotaDTO = new DecreaseQuotaDTO(3, new BigDecimal("1000001.00"));
        decreaseQuotaInputList.add(decreaseQuotaDTO);
        productService.decreaseQuota(decreaseQuotaInputList);

    }
}