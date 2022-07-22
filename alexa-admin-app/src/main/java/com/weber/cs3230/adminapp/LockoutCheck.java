package com.weber.cs3230.adminapp;

public class LockoutCheck {
    public static volatile long lastButClick = 0L;
    public void startLockoutThread()    {
        new Thread(() -> checkForLockout()).start();
    }

    private void checkForLockout() {
        //TODO Lock user out if inactive for more than 30 seconds.
        //whenver we click a button, do Lockoutchecker.sinceLastButtnlick = Sys.currenttimemillis();
        // do that on every button so that we know what it is.
        // while(true)  {
        // thread.sleep
        // get the now time using sys.currenttimemillis
        // calculate the now time between now time, and last button click time
        // if > 10 mins, break out of loop
        // Use SwingUtilites.invokeLater from main app
        // since Swing only has one thread at a time, then this should kill the existing one, and it restarts.
        //JOptionPane.ShowMessageDialog("warningmethod that thread is dying")
//            }
    }
}
