package com.weber.cs3230.adminapp;

import javax.swing.*;

public class LockoutCheck {
    public static volatile long lastButClick = System.currentTimeMillis();

    public void startLockoutThread() {
//        System.out.println("Thread started");
        new Thread(() -> {
            try {
                checkForLockout();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    private void checkForLockout() throws InterruptedException {
//        System.out.println("Thread checked");
        while (true) {
            Thread.sleep(30000);
//            System.out.println("Slept for 30 seconds");
            long diff = (System.currentTimeMillis() / 1000) - (LockoutCheck.lastButClick / 1000);
            // 600 = 10 mins
            if (diff > 600) {
//                System.out.println("Begin lockout");
                JOptionPane warning = new JOptionPane();
                JOptionPane.showMessageDialog(warning,"Login again due to timeout.");
                SwingUtilities.invokeLater(() -> new AlexaMainFrame());
//                System.out.println("sucessfully breaked");
                break;
            }
        }
    }

}
