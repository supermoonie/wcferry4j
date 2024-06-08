package com.github.supermoonie.wcferry4j;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.extras.FlatUIDefaultsInspector;
import com.formdev.flatlaf.util.SystemInfo;
import com.github.supermoonie.wcferry4j.core.AppConfig;
import com.github.supermoonie.wcferry4j.exception.DefaultUncaughtExceptionHandler;
import com.github.supermoonie.wcferry4j.ui.MainFrame;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Slf4j
@SpringBootApplication(exclude = {
        JacksonAutoConfiguration.class,
        SqlInitializationAutoConfiguration.class,
        TaskExecutionAutoConfiguration.class,
        JmxAutoConfiguration.class,
        SpringApplicationAdminJmxAutoConfiguration.class
})
@Component
public class Wcferry4jApplication implements CommandLineRunner {

    @Resource
    private MainFrame mainFrame;
    @Resource
    private DefaultUncaughtExceptionHandler defaultUncaughtExceptionHandler;
    @Resource
    private AppConfig appConfig;

    private static void lookAndFeel() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (Boolean.parseBoolean(System.getProperty("flatlaf.demo.screenshotsMode")) &&
                !SystemInfo.isJava_9_orLater && System.getProperty("flatlaf.uiScale") == null) {
            System.setProperty("flatlaf.uiScale", "2x");
        }
        System.setProperty("java.net.useSystemProxies", "false");
//        FlatLightLaf.setup();
        FlatDarkLaf.setup();
        UIManager.setLookAndFeel(FlatDarkLaf.class.getName());
    }


    public static void main(String[] args) {
        try {
            // 初始化外观
            lookAndFeel();
            new SpringApplicationBuilder(Wcferry4jApplication.class)
                    .web(WebApplicationType.NONE)
                    .headless(false)
                    .bannerMode(Banner.Mode.OFF)
                    .run(args);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            JOptionPane.showMessageDialog(null, "启动失败!", "提示", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    @Override
    public void run(String... args) {
        Thread.setDefaultUncaughtExceptionHandler(defaultUncaughtExceptionHandler);
        try {
            if (!appConfig.getRelease()) {
                FlatInspector.install("ctrl shift alt X");
                FlatUIDefaultsInspector.install("ctrl shift alt Y");
            }
            SwingUtilities.invokeLater(() -> mainFrame.setVisible(true));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            JOptionPane.showMessageDialog(null, "启动失败!", "提示", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
