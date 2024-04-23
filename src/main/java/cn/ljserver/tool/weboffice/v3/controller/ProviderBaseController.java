package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.exception.ProviderException;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ProviderBaseController
 * @Description: 基础控制器，异常处理
 */
@Controller
public class ProviderBaseController {
    @ExceptionHandler({ProviderException.class})
    public @ResponseBody ProviderResponseEntity<?> handleProviderExceptions(ProviderException exception,
                                                                            HttpServletResponse response) {
        // let it broken
        ResponseStatus status = AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class);
        response.setStatus(status.value().value());
        return ProviderResponseEntity.builder().code(exception.getCode()).message(exception.getMessage()).build();
    }

    @ExceptionHandler({MissingServletRequestParameterException.class,
            HttpMediaTypeNotSupportedException.class,
            ServletRequestBindingException.class})
    public @ResponseBody ProviderResponseEntity<?> handleInvalidRequestExceptions(Exception ex,
                                                                                  HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return ProviderResponseEntity.builder().code(40005).message(ex.getMessage()).build();
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody ProviderResponseEntity<?> handleOtherExceptions(Exception ex, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ProviderResponseEntity.builder().code(50001).message(ex.getMessage()).build();
    }

    public String getRequestPath() {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
    }
}
