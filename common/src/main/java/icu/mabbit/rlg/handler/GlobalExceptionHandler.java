package icu.mabbit.rlg.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <h2>全局异常处理器</h2>
 *
 * @author 一只枫兔
 * @Date 2023/8/29 15:55
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler
    public void handleThrowable(Throwable e)
    {
        log.warn("-- unhandled error: {}, msg: {}; Please check the file of error-log", e.getClass().getName(), e.getMessage());
    }
}