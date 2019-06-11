package cn.net.liaowei.sc.product.controller;

import cn.net.liaowei.sc.product.common.DecreaseQuotaInput;
import cn.net.liaowei.sc.product.common.ProductInfoOutput;
import cn.net.liaowei.sc.product.domain.ProductCategory;
import cn.net.liaowei.sc.product.domain.ProductInfo;
import cn.net.liaowei.sc.product.service.CategoryService;
import cn.net.liaowei.sc.product.service.ProductService;
import cn.net.liaowei.sc.product.util.ResultUtil;
import cn.net.liaowei.sc.product.vo.ProductInfoVO;
import cn.net.liaowei.sc.product.vo.ProductVO;
import cn.net.liaowei.sc.product.vo.ResultVO;
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
public class ProductController {
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
        Page<ProductInfo> productInfoPage = productService.listAllOnSaleProduct(null);

        // 获取产品对应的类别列表
        List<Short> categoryTypeList = productInfoPage.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());

        // 获取产品对应的类别信息
        List<ProductCategory> productCategoryList = categoryService.listCategoryIn(categoryTypeList);

        // 构造返回数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(productCategory, productVO);
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoPage) {
                if ( productInfo.getCategoryType().equals(productCategory.getCategoryType()) ) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
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
    List<ProductInfoOutput> listByProductId(List<Integer> productIdList) {
        Page<ProductInfo> productInfoPage = productService.listProductIn(productIdList, null);
        return productInfoPage.stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e, output);
            return output;
        }).collect(Collectors.toList());
    }

    @ApiOperation("扣减产品可用额度(仅供内部调用)")
    @PostMapping("/decrease/quota")
    public void decreaseQuota(@RequestBody List<DecreaseQuotaInput> decreaseQuotaInputList) {
        productService.decreaseQuota(decreaseQuotaInputList);
    }
}
