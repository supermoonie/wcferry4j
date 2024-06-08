package com.github.supermoonie.wcferry4j.exception;

import com.github.supermoonie.wcferry4j.core.AppContext;
import com.github.supermoonie.wcferry4j.ui.MainFrame;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author super_wang
 * @since 2024/6/8
 */
@Component
@Slf4j
public class DefaultExceptionHandler {

    @Resource
    private AppContext appContext;

    public void handle(Throwable t) {
        log.error(t.getMessage(), t);
        MainFrame mainFrame = appContext.getMainFrame();
        if (t instanceof IOException) {
            JOptionPane.showMessageDialog(mainFrame, "网络异常，请检查网络是否通畅!", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (t instanceof WarnException) {
            JOptionPane.showMessageDialog(mainFrame, t.getMessage(), "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (t instanceof ExecutionException) {
            JOptionPane.showMessageDialog(mainFrame, t.getCause().getMessage(), "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (t instanceof InterruptedException) {
            // ignore
            return;
        }
        JOptionPane.showMessageDialog(mainFrame, "软件发生错误，请联系售后!", "提示", JOptionPane.ERROR_MESSAGE);
    }
}
