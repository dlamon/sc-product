package cn.net.liaowei.sc.product.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.Getter;

/**
 * @author LiaoWei
 */
@Getter
public class SCException extends HystrixBadRequestException {
    private String code;
    private String message;

    public SCException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
