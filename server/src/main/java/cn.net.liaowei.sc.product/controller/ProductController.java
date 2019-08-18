package cn.net.liaowei.sc.product.controller;

import cn.net.liaowei.sc.product.client.ProductClient;
import cn.net.liaowei.sc.product.common.DecreaseQuotaDTO;
import cn.net.liaowei.sc.product.common.ProductInfoDTO;
import cn.net.liaowei.sc.product.domain.dos.ProductCategoryDO;
import cn.net.liaowei.sc.product.domain.dos.ProductInfoDO;
import cn.net.liaowei.sc.product.service.CategoryService;
import cn.net.liaowei.sc.product.service.ProductService;
import cn.net.liaowei.sc.product.util.ResultUtil;
import cn.net.liaowei.sc.product.domain.vo.ProductInfoVO;
import cn.net.liaowei.sc.product.domain.vo.ProductVO;
import cn.net.liaowei.sc.product.domain.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiaoWei
 */
@RestController
@Slf4j
@Api(tags = "/product", description = "产品服务")
@RequestMapping("/product")
public class ProductController implements ProductClient {
    private ProductService productService;
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    @ApiOperation("获取在售产品列表")
    public ResultVO<List<ProductVO>> list() {
        // 获取所有在售的产品列表
        Page<ProductInfoDO> productInfoDOPage = productService.listAllOnSaleProduct(null);

        // 获取产品对应的类别列表
        List<Short> categoryTypeList = productInfoDOPage.stream().map(ProductInfoDO::getCategoryType).collect(Collectors.toList());

        // 获取产品对应的类别信息
        List<ProductCategoryDO> productCategoryDOList = categoryService.listCategoryIn(categoryTypeList);

        // 构造返回数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategoryDO productCategoryDO : productCategoryDOList) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(productCategoryDO, productVO);
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfoDO productInfoDO : productInfoDOPage) {
                if ( productInfoDO.getCategoryType().equals(productCategoryDO.getCategoryType()) ) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfoDO, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        // 返回结果
        return ResultUtil.success(productVOList);
    }

    @ApiOperation("通过产品Id查询产品列表(仅供内部调用)")
    @GetMapping("/list/ids")
    @Override
    public List<ProductInfoDTO> listByProductId(@RequestParam("id") List<Integer> productIdList) {
        long before = System.currentTimeMillis();
        // 测试hystrix配置超时时间
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // 测试hystrix配置熔断
        if (productIdList.size() == 3) {
            throw new RuntimeException();
        }
        Page<ProductInfoDO> productInfoDOPage = productService.listProductIn(productIdList, null);
        long end = System.currentTimeMillis();
        long times = end - before;
        log.info("Span times: {}", times);

        return productInfoDOPage.stream().map(e -> {
            ProductInfoDTO output = new ProductInfoDTO();
            BeanUtils.copyProperties(e, output);
            return output;
        }).collect(Collectors.toList());
    }

    @ApiOperation("扣减产品可用额度(仅供内部调用)")
    @PostMapping("/decrease/quota")
    @Override
    public void decreaseQuota(@RequestBody List<DecreaseQuotaDTO> decreaseQuotaInputList) {
        productService.decreaseQuota(decreaseQuotaInputList);
    }
}
