package com.altale.service.CSException;
/**
 * 异常信息类
 */
@SuppressWarnings("serial")
public final class RequestException extends RuntimeException
{
    /**
     * 异常信息类构造函数
     * @param message 异常信息
     */
    public RequestException(String message)
    {
        super(message);
    }
}
