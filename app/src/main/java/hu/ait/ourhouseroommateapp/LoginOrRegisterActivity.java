package hu.ait.ourhouseroommateapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ait.ourhouseroommateapp.ui.LoginActivity;
import hu.ait.ourhouseroommateapp.ui.RegisterActivity;

public class LoginOrRegisterActivity extends AppCompatActivity {

    @BindView(R.id.nameLoginRegister)
    TextView name;

    @BindView(R.id.btnRegister)
    Button btnRegister;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
        ButterKnife.bind(this);

        Typeface lemonMilk = Typeface.createFromAsset(getAssets(),  "fonts/LemonMilk.otf");
        name.setTypeface(lemonMilk);
    }

    @OnClick(R.id.btnLogin)
    public void btnLoginClicked(Button btn){
        Intent loginActivity = new Intent(LoginOrRegisterActivity.this, LoginActivity.class);
        startActivity(loginActivity);
    }

    @OnClick(R.id.btnRegister)
    public void btnRegisterClicked(Button btn){
        Intent registerActivity = new Intent(LoginOrRegisterActivity.this, RegisterActivity.class);
        startActivity(registerActivity);
    }
}
