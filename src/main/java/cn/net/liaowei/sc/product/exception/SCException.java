package cn.net.liaowei.sc.product.exception;

import cn.net.liaowei.sc.product.enums.ErrorEnum;

/**
 * @author LiaoWei
 */
public class SCException extends RuntimeException{
    private String code;

    public SCException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SCException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
    }
}
