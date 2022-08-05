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
            ApiClient apiClient = new ApiClient();
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            SwingWorker<Boolean, Object> swingWorker = new SwingWorker<>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    Thread.sleep(500); // Sleeps thread for 1 secs after pressing login button. To see the cirlce spin
                    String user =  loginString.getText();
                    String pass =  new String(passString.getPassword());
                    return apiClient.validateCreds(user, pass);
                }
                @Override
                protected void done() {
                    try {
                        if (get() == true) {
                            LockoutCheck lockoutCheck = new LockoutCheck();
                            lockoutCheck.startLockoutThread();
                            setVisible(false);
                            dispose();
                        }
                        else {
                            JOptionPane warning = new JOptionPane();
                            JOptionPane.showMessageDialog(warning, "Incorrect Credentials");
                        }
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    } catch (ExecutionException ex) {
                        throw new RuntimeException(ex);
                    }
                    setCursor(Cursor.getDefaultCursor());
                    LockoutCheck.lastButClick = System.currentTimeMillis();
                    super.done();
                }
            };
            swingWorker.execute();
        });
        dialogPanel.add(closeButton);
        closeButton.addActionListener(e -> {
            System.exit(0);
        });
        return dialogPanel;
    }
}
