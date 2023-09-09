package icu.mabbit.rlg.common.handler;

import icu.mabbit.rlg.common.enums.ServiceCode;
import icu.mabbit.rlg.common.exception.ProjectException;
import icu.mabbit.rlg.common.exception.ServiceException;
import icu.mabbit.rlg.common.restful.FailedResult;
import icu.mabbit.rlg.common.restful.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
public class GlobalExceptionHandler
{
    private static final ServiceException COMMON = new ServiceException(ServiceCode.ERR_UNKNOWN.code(), "服务器忙，请重试");

    @ExceptionHandler(ProjectException.class)
    public FailedResult handleProjectException()
    {
        // TODO 项目异常处理
        return R.fail(COMMON);
    }

    /**
     * 处理业务异常
     *
     * @param e 业务异常
     * @return {@link FailedResult}
     */
    @ExceptionHandler
    public FailedResult handleServiceException(ServiceException e)
    {
        return R.fail(e);
    }

    static
    {
        System.err.println("-- GlobalExceptionHandler handleUnknownError()，打印异常堆栈信息语句未关闭"); // 作提醒用
    }

    /**
     * 处理所有未处理异常
     *
     * @param e 所有未处理异常
     * @return {@link FailedResult}
     */
    @ExceptionHandler
    public FailedResult handleUnknownError(Throwable e)
    {
        e.printStackTrace(); // 生产环境下关闭，必须与上方静态块中语句同开同关
        log.error("-- Unhandled error: {}, msg: {}", e.getClass(), e.getMessage());
        return R.fail(COMMON);
    }
}