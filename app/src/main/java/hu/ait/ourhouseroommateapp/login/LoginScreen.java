package hu.ait.ourhouseroommateapp.login;

/**
 * Created by nasheyarahman on 5/11/17.
 */

public interface LoginScreen {
    public void postLoginSuccess();

    public void postLoginFailure();

    public void postNetworkFailure();
}
