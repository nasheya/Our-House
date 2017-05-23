package hu.ait.ourhouseroommateapp.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import hu.ait.ourhouseroommateapp.CurrentGroup;
import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.login.LoginPresenter;
import hu.ait.ourhouseroommateapp.login.LoginScreen;
import hu.ait.ourhouseroommateapp.login.LoginOrRegisterActivity;

public class SplashScreen extends AppCompatActivity implements LoginScreen{

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Animation animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.play_anim);

        name = (TextView) findViewById(R.id.titleSplash);

        Typeface lemonMilk = Typeface.createFromAsset(getAssets(),  "fonts/LemonMilk.otf");
        name.setTypeface(lemonMilk);

        LinearLayout layout = (LinearLayout) findViewById(R.id.splashScreen);
        layout.startAnimation(animation);
        final LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.attachScreen(SplashScreen.this);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                SystemClock.sleep(1000);
                loginPresenter.loginWithCredentials();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    @Override
    public void postLoginSuccess(){
        Intent main = new Intent(this, MainActivity.class);

        if(CurrentGroup.getCurrentGroup() == null){
            CurrentGroup.retrieveGroupId(main);
        } else {
            startActivity(main);
            finish();
        }
//        startActivity(main);
//        finish();
    }

    @Override
    public void postLoginFailure(){
        Intent loginRegister = new Intent(this, LoginOrRegisterActivity.class);
        startActivity(loginRegister);
        finish();
    }

    @Override
    public void postNetworkFailure() {}
}
