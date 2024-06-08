package com.github.supermoonie.wcferry4j.ui;

import com.formdev.flatlaf.extras.FlatSVGUtils;
import com.github.supermoonie.wcferry4j.core.AppConfig;
import com.github.supermoonie.wcferry4j.ui.menu.CustomMenuBar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author super_wang
 * @since 2024/6/8
 */
@Slf4j
@Component
public class MainFrame extends JFrame {

    private final AppConfig appConfig;

    @Autowired
    public MainFrame(AppConfig appConfig,
                     CustomMenuBar customMenuBar) throws HeadlessException {
        super();
        this.appConfig = appConfig;
        this.setIconImages(FlatSVGUtils.createWindowIconImages("/icons/app.svg"));
        this.setTitle("wcferry4j");
        setJMenuBar(customMenuBar);
        setLayout(new BorderLayout());

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int minWith = 1024, minHeight = 768;
        this.setLocation(screenSize.width / 2 - minWith / 2, screenSize.height / 2 - minHeight / 2);
        this.setMinimumSize(new Dimension(minWith, minHeight));
        this.setPreferredSize(new Dimension(minWith, minHeight));
        this.pack();
        this.setResizable(true);
        this.setFocusable(true);
        this.setAutoRequestFocus(true);
        this.setVisible(true);
    }

    public void close() {
        if (appConfig.getRelease()) {
            int result = JOptionPane.showConfirmDialog(MainFrame.this, "确认退出?", "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
}
