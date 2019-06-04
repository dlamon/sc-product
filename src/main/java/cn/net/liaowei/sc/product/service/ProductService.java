package cn.net.liaowei.sc.product.service;

import cn.net.liaowei.sc.product.common.DecreaseTotalLimitAmtInput;
import cn.net.liaowei.sc.product.domain.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author LiaoWei
 */
public interface ProductService {
    /**
     * 查询所有在售商品列表
     * @param pageable 分页参数
     * @return 在售商品列表
     */
    public Page<ProductInfo> listAllOnSaleProduct(Pageable pageable);

    /**
     * 查询特定商品ID的商品列表
     * @param productIdList 商品ID列表
     * @param pageable 分页参数
     * @return 商品列表
     */
    public Page<ProductInfo> listProductIn(List<Integer> productIdList, Pageable pageable);

    /**
     * 扣减商品额度
     * @param decreaseTotalLimitAmtInputList 购买商品列表
     * @return 剩余的商品额度
     */
    public List<ProductInfo> decreaseTotalLimitAmt(List<DecreaseTotalLimitAmtInput> decreaseTotalLimitAmtInputList);

}
