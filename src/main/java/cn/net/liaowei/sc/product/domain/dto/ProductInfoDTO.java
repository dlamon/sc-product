package cn.net.liaowei.sc.product.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiaoWei
 */
@Data
public class ProductInfoDTO {
    @ApiModelProperty("产品编号")
    private Integer productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品类别")
    private Short categoryType;

    @ApiModelProperty("利率")
    private Float rate;

    @ApiModelProperty("开始时间")
    private Date saleBeginTime;

    @ApiModelProperty("结束时间")
    private Date saleEndTime;

    @ApiModelProperty("最小购买金额")
    private BigDecimal minBuyAmt;

    @ApiModelProperty("最大购买金额")
    private BigDecimal maxBuyAmt;

    @ApiModelProperty("剩余额度")
    private BigDecimal remainQuota;

    @ApiModelProperty("产品描述")
    private String description;

    @ApiModelProperty("产品状态  0在售 1停售")
    private Short status;
}
