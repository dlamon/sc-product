package cn.net.liaowei.sc.product.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author LiaoWei
 */
@Data
public class DecreaseQuotaDTO {
    @ApiModelProperty("产品编号")
    private Integer productId;

    @ApiModelProperty("购买金额")
    private BigDecimal amount;
}
