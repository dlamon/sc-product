package cn.net.liaowei.sc.product.client;

import cn.net.liaowei.sc.product.common.DecreaseQuotaDTO;
import cn.net.liaowei.sc.product.common.ProductInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author liaowei
 */
@FeignClient(name="product" , fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {
    /**
     * 通过产品编号列表获取产品信息列表
     * @param productIdList 产品编号列表
     * @return 产品信息列表
     */
    @GetMapping("/product/list/ids")
    List<ProductInfoDTO> listByProductId(@RequestParam("id")  List<Integer> productIdList);

    /**
     * 扣减可用额度
     * @param decreaseQuotaInputList 需要扣减额度的列表
     */
    @PostMapping("/product/decrease/quota")
    void decreaseQuota(@RequestBody List<DecreaseQuotaDTO> decreaseQuotaInputList) throws InterruptedException;


    // 屏蔽降级处理逻辑， 降级处理应该在需要的时候才进行配置
    @Component
    static class ProductClientFallback implements ProductClient {

        @Override
        public List<ProductInfoDTO> listByProductId(List<Integer> productIdList) {
            System.out.println("=============fallback listByProductId=============");
            return null;
        }

        @Override
        public void decreaseQuota(List<DecreaseQuotaDTO> decreaseQuotaInputList) {
            System.out.println("=============fallback decreaseQuota=============");
        }
    }
}