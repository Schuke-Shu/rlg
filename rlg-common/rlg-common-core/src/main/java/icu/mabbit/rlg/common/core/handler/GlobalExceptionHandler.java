package icu.mabbit.rlg.common.core.handler;

import icu.mabbit.rlg.common.core.exception.ServiceException;
import icu.mabbit.rlg.common.core.restful.R;
import icu.mabbit.rlg.common.core.exception.ProjectException;
import icu.mabbit.rlg.common.core.restful.FailedResult;
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
@SuppressWarnings("CallToPrintStackTrace")
public class GlobalExceptionHandler
{
    private static final ServiceException COMMON = new ServiceException();

    @ExceptionHandler()
    public FailedResult handleProjectException(ProjectException e)
    {
        if (log.isDebugEnabled())
            e.printStackTrace();
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
        if (log.isDebugEnabled())
            e.printStackTrace();
        return R.fail(e);
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
        if (log.isDebugEnabled())
            e.printStackTrace();
        log.error("-- Unhandled error: {}, msg: {}", e.getClass(), e.getMessage());
        return R.fail(COMMON);
    }
}