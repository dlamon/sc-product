package cn.net.liaowei.sc.product.common;

import lombok.Data;
import java.math.BigDecimal;

/**
 * @author LiaoWei
 */
@Data
public class DecreaseTotalLimitAmtInput {
    private Integer productId;
    private BigDecimal amount;
}
