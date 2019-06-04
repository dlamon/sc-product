package cn.net.liaowei.sc.product.service.impl;

import cn.net.liaowei.sc.product.common.DecreaseTotalLimitAmtInput;
import cn.net.liaowei.sc.product.domain.ProductInfo;
import cn.net.liaowei.sc.product.enums.ErrorEnum;
import cn.net.liaowei.sc.product.enums.ProductStatusEnum;
import cn.net.liaowei.sc.product.exception.SCException;
import cn.net.liaowei.sc.product.repository.ProductInfoRepository;
import cn.net.liaowei.sc.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author LiaoWei
 */
public class ProductServiceImpl implements ProductService {
    private ProductInfoRepository productInfoRepository;

    public ProductServiceImpl(ProductInfoRepository productInfoRepository) {
        this.productInfoRepository = productInfoRepository;
    }

    @Override
    public Page<ProductInfo> listAllOnSaleProduct(Pageable pageable) {
        return productInfoRepository.findByStatus(ProductStatusEnum.ONSALE.getCode(), pageable);
    }

    @Override
    public Page<ProductInfo> listProductIn(List<Integer> productIdList, Pageable pageable) {
        return productInfoRepository.findByProductIdIn(productIdList, pageable);
    }

    @Override
    public List<ProductInfo> decreaseTotalLimitAmt(List<DecreaseTotalLimitAmtInput> decreaseTotalLimitAmtInputList) {
        for (DecreaseTotalLimitAmtInput decreaseTotalLimitAmtInput : decreaseTotalLimitAmtInputList) {
            // 判断商品是否存在
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseTotalLimitAmtInput.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new SCException(ErrorEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();

            // 判断商品状态是否正常

            // 判断是否大于开始时间

            // 判断是否小于结束时间

            // 判断是否大于最小购买金额

            // 判断是否小于最大购买金额

            // 判断剩余额度是否充足

            // 扣除购买额度
        }


        return null;
    }
}
