package cn.net.liaowei.sc.product.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiaoWei
 */
@Data
@Entity
@ApiModel(description = "产品详细信息")
public class ProductInfo {
    @Id
    @ApiModelProperty("产品编号")
    private Integer productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品类别")
    private Short categoryType;

    @ApiModelProperty("利率")
    private Float rate;

    @ApiModelProperty("开始时间")
    private Date sellBeginTime;

    @ApiModelProperty("结束时间")
    private Date sellEndTime;

    @ApiModelProperty("最小购买金额")
    private BigDecimal minPurchaseAmt;

    @ApiModelProperty("最大购买金额")
    private BigDecimal maxPurchaseAmt;

    @ApiModelProperty("产品总限额")
    private BigDecimal totalLimitAmt;

    @ApiModelProperty("产品描述")
    private String description;

    @ApiModelProperty("产品状态  0在售 1停售")
    private Short status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
