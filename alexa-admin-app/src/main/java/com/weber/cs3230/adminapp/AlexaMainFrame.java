package com.weber.cs3230.adminapp;

import javax.swing.*;
import java.awt.*;

public class AlexaMainFrame extends JFrame{

    public AlexaMainFrame() throws HeadlessException {
        showMainFrame();
    }
    private void showMainFrame() {
        AlexaDialog dialog = new AlexaDialog();
        setPreferredSize(new Dimension(480, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new AlexaMainPanel());
        pack();
        setVisible(true);
    }
}
