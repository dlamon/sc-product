package cn.net.liaowei.sc.product.vo;

import lombok.Data;

/**
 * @author LiaoWei
 */
@Data
public class ResultVO<T> {
    private String code;
    private String message;
    private T data;
}
