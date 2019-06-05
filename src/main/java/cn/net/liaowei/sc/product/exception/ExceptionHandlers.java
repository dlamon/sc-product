package cn.net.liaowei.sc.product.exception;

import cn.net.liaowei.sc.product.enums.ErrorEnum;
import cn.net.liaowei.sc.product.util.ResultUtil;
import cn.net.liaowei.sc.product.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author LiaoWei
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {
    @ExceptionHandler(value = SCException.class)
    public <E> ResultVO<E> handleSCException(SCException e) {
        String code = e.getCode();
        String message = e.getMessage();

        log.error("{}:{}", code, message, e);
        return ResultUtil.error(code, message);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public <E> ResultVO<E> handleConstraintViolationException(ConstraintViolationException e) {
        String code = ErrorEnum.PARAM_CHECK_ERROR.getCode();
        String exceptionMessage = e.getMessage();
        int length = exceptionMessage.length();
        exceptionMessage = exceptionMessage.substring(exceptionMessage.lastIndexOf(":")+1, length).trim();
        String message = ErrorEnum.PARAM_CHECK_ERROR.getMessage() + "[" + exceptionMessage + "]";

        log.error("{}:{}", code, message, e);
        return ResultUtil.error(code, message);
    }

    @ExceptionHandler(value = BindException.class)
    public <E> ResultVO<E> handleBindException(BindException e) {
        return this.returnByBindingResult(e);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public <E> ResultVO<E> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return this.returnByBindingResult(e);
    }

    @ExceptionHandler(value = Exception.class)
    public <E> ResultVO<E> handleException(Exception e) {
        String code = ErrorEnum.SYSTEM_INTERNAL_ERROR.getCode();
        String message = ErrorEnum.SYSTEM_INTERNAL_ERROR.getMessage();

        log.error("{}:{}", code, message, e);
        return ResultUtil.error(code, message);
    }

    private <E> ResultVO<E> returnByBindingResult(Exception e) {
        String code = ErrorEnum.PARAM_CHECK_ERROR.getCode();
        String message = ErrorEnum.PARAM_CHECK_ERROR.getMessage();
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        sb.append("[");

        BindingResult bindingResult = null;
        if (e instanceof BindException) {
            bindingResult = ((BindException)e).getBindingResult();
        } else if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException)e).getBindingResult();
        }
        if (bindingResult == null) {
            log.error("{}:{}", code, message, e);
            return ResultUtil.error(code, message);
        } else {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                sb.append(fieldError.getDefaultMessage());
            }
            sb.append("]");
            message = sb.toString();

            log.error("{}:{}", code, message, e);
            return ResultUtil.error(code, message);
        }
    }
}
