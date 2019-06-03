package cn.net.liaowei.sc.product.controller;

import cn.net.liaowei.sc.product.domain.ProductInfo;
import cn.net.liaowei.sc.product.repository.ProductInfoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiaoWei
 */
@RestController
@Slf4j
@Api(tags = "/", description = "欢迎信息")
public class HelloController {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @GetMapping("/")
    @ApiOperation("欢迎信息")
    public ProductInfo hello() {
        List<ProductInfo> productInfoList = productInfoRepository.findByStatus(Short.valueOf("0"));
        log.info(productInfoList.toString());
        ProductInfo productInfo = new ProductInfo();
        return productInfo;
    }
}