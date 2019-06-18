package cn.net.liaowei.sc.product.exception;

import cn.net.liaowei.sc.product.enums.ErrorEnum;
import lombok.Getter;

/**
 * @author LiaoWei
 */
@Getter
public class SCException extends RuntimeException{
    private String code;

    private static String getMessage(ErrorEnum errorEnum, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(errorEnum.getMessage());
        if(message != null && !"".equals(message)) {
            sb.append("[").append(message).append("]");
        }
        return sb.toString();
    }

    public SCException(ErrorEnum errorEnum, String message) {
        super(SCException.getMessage(errorEnum, message));
        this.code = errorEnum.getCode();
    }

    public SCException(ErrorEnum errorEnum) {
        super(SCException.getMessage(errorEnum, null));
        this.code = errorEnum.getCode();
    }

    public SCException(ErrorEnum errorEnum, Throwable cause) {
        super(SCException.getMessage(errorEnum, null), cause);
        this.code = errorEnum.getCode();
    }

    public SCException(ErrorEnum errorEnum, String message, Throwable cause) {
        super(SCException.getMessage(errorEnum, message), cause);
        this.code = errorEnum.getCode();
    }
}
