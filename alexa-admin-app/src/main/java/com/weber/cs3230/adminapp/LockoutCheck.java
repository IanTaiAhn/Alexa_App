package com.weber.cs3230.adminapp;

import javax.swing.*;

public class LockoutCheck {
    public static volatile long lastButClick = 0;

    public void startLockoutThread() {
        new Thread(() -> {
            try {
                checkForLockout();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    private void checkForLockout() throws InterruptedException {
        while (true) {
            Thread.sleep(30000);
            if ((System.currentTimeMillis() - LockoutCheck.lastButClick) > 600_000) {
                JOptionPane warning = new JOptionPane();
                JOptionPane.showMessageDialog(warning,"Login again due to timeout.");
                SwingUtilities.invokeLater(() -> new AlexaLoginDialog());
                break;
            }
        }
    }

}
