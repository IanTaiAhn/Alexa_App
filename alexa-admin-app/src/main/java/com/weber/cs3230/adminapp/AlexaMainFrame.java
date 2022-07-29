package com.weber.cs3230.adminapp;

import javax.swing.*;
import java.awt.*;

public class AlexaMainFrame extends JFrame{
    public AlexaMainFrame() throws HeadlessException {
        showMainFrame();
    }
    private void showMainFrame() {
        AlexaLoginDialog dialog = new AlexaLoginDialog();
        // load in all of the data i want.
        setPreferredSize(new Dimension(480, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new AlexaMainPanel());
        pack();
        setVisible(true);
    }
}
