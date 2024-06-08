package com.github.supermoonie.wcferry4j.exception;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author super_wang
 * @since 2024/6/8
 */
@Component
@Slf4j
public class DefaultUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Resource
    private DefaultExceptionHandler defaultExceptionHandler;

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        defaultExceptionHandler.handle(e);
    }
}
