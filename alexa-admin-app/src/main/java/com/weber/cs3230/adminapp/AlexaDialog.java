package com.weber.cs3230.adminapp;

import javax.swing.*;
import java.awt.*;

public class AlexaDialog extends JDialog {
    public AlexaDialog() {
        someDialog();
    }

    private void someDialog() {
        setPreferredSize(new Dimension(300, 200));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        add(dialogCredentialPanel());
        pack();
        setVisible(true);
    }

    private JPanel dialogCredentialPanel()    {
        JPanel dialogPanel = new JPanel(new GridLayout(3, 2));
        JTextField loginString = new JTextField();
        JPasswordField passString = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton closeButton = new JButton("Exit");

        dialogPanel.add(new JLabel("User"));
        dialogPanel.add(loginString);
        dialogPanel.add(new JLabel("Password"));
        dialogPanel.add(passString);
        dialogPanel.add(loginButton);
        loginButton.addActionListener(e -> {
            if (loginString.getText().equals("butter") && new String(passString.getPassword()).equals("pass")) {
                LockoutCheck lockoutCheck = new LockoutCheck();
//                Thread thread = new Thread();
                lockoutCheck.startLockoutThread();
                // make lockoutCheck thread sleep for in 30 second intervals. make it check every 30 seconds.
                setVisible(false);
                dispose();
                //Thread thread = new Thread() -> sout("test");
                //Thread.sleep(1000)
//                LockoutCheckout.methodthatwatchsforlockout
                //
                // create that where we have our add/edit/del buttons
            }
            else {
                JOptionPane warning = new JOptionPane();
                JOptionPane.showMessageDialog(warning, "Incorrect Credentials");
            }
        });
        dialogPanel.add(closeButton);
        closeButton.addActionListener(e -> {
            System.exit(0);
        });
        return dialogPanel;
    }
}
