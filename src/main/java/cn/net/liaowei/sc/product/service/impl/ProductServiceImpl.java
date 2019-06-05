package cn.net.liaowei.sc.product.service.impl;

import cn.net.liaowei.sc.product.common.DecreaseQuotaInput;
import cn.net.liaowei.sc.product.domain.ProductInfo;
import cn.net.liaowei.sc.product.enums.ErrorEnum;
import cn.net.liaowei.sc.product.enums.ProductStatusEnum;
import cn.net.liaowei.sc.product.exception.SCException;
import cn.net.liaowei.sc.product.repository.ProductInfoRepository;
import cn.net.liaowei.sc.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Transactional
    public List<ProductInfo> decreaseTotalLimitAmt(List<DecreaseQuotaInput> decreaseQuotaInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseQuotaInput decreaseQuotaInput : decreaseQuotaInputList) {
            // 判断产品是否存在
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseQuotaInput.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new SCException(ErrorEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();

            // 判断产品是否在售
            if (productInfo.getStatus() != 0) {
                throw new SCException(ErrorEnum.PRODUCT_SALE_OFF);
            }

            // 判断是否大于开始时间
            if (System.currentTimeMillis() < productInfo.getSaleBeginTime().getTime()) {
                throw new SCException(ErrorEnum.PRODUCT_SALE_NOT_STARTED);
            }

            // 判断是否小于结束时间
            if (System.currentTimeMillis() > productInfo.getSaleEndTime().getTime()) {
                throw new SCException(ErrorEnum.PRODUCT_SALE_EXCEEDED);
            }
            // 判断是否大于最小购买金额
            if (decreaseQuotaInput.getAmount().compareTo(productInfo.getMinBuyAmt()) < 0) {
                throw new SCException(ErrorEnum.PRODUCT_LESS_MINI_AMT);
            }

            // 判断是否小于最大购买金额
            if (decreaseQuotaInput.getAmount().compareTo(productInfo.getMaxBuyAmt()) > 0) {
                throw new SCException(ErrorEnum.PRODUCT_GREATER_MAX_AMT);
            }

            // 判断剩余额度是否充足
            if (decreaseQuotaInput.getAmount().compareTo(productInfo.getRemainQuota()) > 0) {
                throw new SCException(ErrorEnum.PRODUCT_REMAIN_NOT_ENOUGH);
            }

            // 扣除购买额度
            BigDecimal remainQuota = productInfo.getRemainQuota().subtract(decreaseQuotaInput.getAmount());
            productInfo.setRemainQuota(remainQuota);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }

        return productInfoList;
    }
}
