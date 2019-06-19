package cn.net.liaowei.sc.product.client;

import cn.net.liaowei.sc.product.common.DecreaseQuotaDTO;
import cn.net.liaowei.sc.product.common.ProductInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author liaowei
 */
@FeignClient("sc-product")
public interface ProductClient {
    /**
     * 通过产品编号列表获取产品信息列表
     * @param productIdList 产品编号列表
     * @return 产品信息列表
     */
    @GetMapping("/list/ids")
    List<ProductInfoDTO> listByProductId(@RequestParam("id") List<Integer> productIdList);

    /**
     * 扣减可用额度
     * @param decreaseQuotaInputList 需要扣减额度的列表
     */
    @PostMapping("/decrease/quota")
    void decreaseQuota(@RequestBody List<DecreaseQuotaDTO> decreaseQuotaInputList);
}
