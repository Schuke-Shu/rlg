package icu.mabbit.rlg.common.handler;

import icu.mabbit.rlg.common.enums.ServiceCode;
import icu.mabbit.rlg.common.exception.ServiceException;
import icu.mabbit.rlg.common.restful.FailedResult;
import icu.mabbit.rlg.common.restful.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <h2>全局异常处理器</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/3 23:44
 */
@Slf4j
@RestControllerAdvice
@Order
public class GlobalExceptionHandler
{
    /**
     * 处理业务异常
     *
     * @param e 业务异常
     * @return {@link FailedResult}
     */
    public FailedResult handleServiceException(ServiceException e)
    {
        return R.fail(e);
    }

    /**
     * 处理所有未处理异常
     *
     * @param e 所有未处理异常
     * @return {@link FailedResult}
     */
    public FailedResult handleUnknownError(Throwable e)
    {
//        System.err.println("-- GlobalExceptionHandler handleUnknownError，打印异常堆栈信息语句未关闭"); // 作提醒用
//        e.printStackTrace(); // 生产环境下关闭，必须与上方语句同开同关
        log.error("-- Unhandled error: {}, msg: {}", e.getClass(), e.getMessage());
        return R.fail(ServiceCode.ERR_UNKNOWN, "服务器忙，请重试");
    }
}