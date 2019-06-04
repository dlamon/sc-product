package cn.net.liaowei.sc.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    /**
     * 错误码枚举
     */
    PRODUCT_NOT_EXIST("PRD0001", "商品不存在");

    private String code;
    private String message;
}
