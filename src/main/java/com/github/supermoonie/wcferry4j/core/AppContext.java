package com.github.supermoonie.wcferry4j.core;

import com.github.supermoonie.wcferry4j.ui.MainFrame;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author super_wang
 * @since 2024/6/8
 */
@Component
public class AppContext implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static ApplicationContext getSpringContext() {
        return applicationContext;
    }

    public MainFrame getMainFrame() {
        return applicationContext.getBean(MainFrame.class);
    }
}
