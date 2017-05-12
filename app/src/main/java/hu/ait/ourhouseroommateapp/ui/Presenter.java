package hu.ait.ourhouseroommateapp.ui;

import android.app.Activity;

/**
 * Created by nasheyarahman on 5/11/17.
 */

public abstract class Presenter<S> {
    protected S screen;

    public void attachScreen(S screen) {
        this.screen = screen;
    }

    public void detachScreen() {
        this.screen = null;
    }
}