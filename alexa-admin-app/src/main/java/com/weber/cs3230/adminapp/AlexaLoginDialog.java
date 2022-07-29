package com.weber.cs3230.adminapp;

import com.weber.cs3230.adminapp.api.ApiClient;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;

public class AlexaLoginDialog extends JDialog {
    public AlexaLoginDialog() {
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
            // Do swing worker in here

            ApiClient apiClient = new ApiClient();
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            SwingWorker<Object, Object> swingWorker = new SwingWorker<>() {
                @Override
                protected String doInBackground() throws Exception {
                    Thread.sleep(1000); // Sleeps thread for 1 secs after pressing login button. To see the cirlce spin
                    String user =  loginString.getText();
                    String pass =  new String(passString.getPassword());
                    if (apiClient.validateCreds(user, pass)) {
                        LockoutCheck lockoutCheck = new LockoutCheck();
                        lockoutCheck.startLockoutThread();
                        setVisible(false);
                        dispose();
                    }
                    else {
                        JOptionPane warning = new JOptionPane();
                        JOptionPane.showMessageDialog(warning, "Incorrect Credentials");
                    }
                    return null;
                }

                @Override
                protected void done() {
                    setCursor(Cursor.getDefaultCursor());
                    super.done();
                }
            };
            swingWorker.execute();

            /*
            if (loginString.getText().equals("user") && new String(passString.getPassword()).equals("pass")) {
                LockoutCheck lockoutCheck = new LockoutCheck();
                lockoutCheck.startLockoutThread();
                setVisible(false);
                dispose();
            }
            else {
                JOptionPane warning = new JOptionPane();
                JOptionPane.showMessageDialog(warning, "Incorrect Credentials");
            }
            */
        });
        dialogPanel.add(closeButton);
        closeButton.addActionListener(e -> {
            System.exit(0);
        });
        return dialogPanel;
    }
}
