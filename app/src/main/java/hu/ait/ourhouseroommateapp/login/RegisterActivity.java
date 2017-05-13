package hu.ait.ourhouseroommateapp.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.groups.LoginRegisterGroups;

public class RegisterActivity extends AppCompatActivity implements LoginScreen {

    private UserLoginTask mAuthTask = null;
    LoginPresenter loginPresenter;

    TextView name;

    // UI references.
    private EditText firstName;
    private EditText lastName;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Typeface lemonMilk = Typeface.createFromAsset(getAssets(),  "fonts/LemonMilk.otf");
        name = (TextView) findViewById(R.id.name_register);
        name.setTypeface(lemonMilk);

        firstName = (EditText) findViewById(R.id.register_name_first);
        lastName = (EditText) findViewById(R.id.register_name_last);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.register_email);
        mPasswordView = (EditText) findViewById(R.id.register_password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_register_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        mLoginFormView = findViewById(R.id.register_form);
        mProgressView = findViewById(R.id.register_progress);

        loginPresenter = new LoginPresenter();
        loginPresenter.attachScreen(this);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.detachScreen();
        super.onDestroy();
    }

    private void attemptRegister() {
        if (mAuthTask != null) {
            return;
        }

        firstName.setError(null);
        lastName.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);

        if(validForm()){
            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();
            String first = firstName.getText().toString();
            String last = lastName.getText().toString();

            showProgress(true);
            mAuthTask = new RegisterActivity.UserLoginTask(email, password, first, last);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean validForm() {
        String first = firstName.getText().toString();
        String last = lastName.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(first)) {
            firstName.setError(getString(R.string.error_field_required));
            focusView = firstName;
            cancel = true;
        }

        if (TextUtils.isEmpty(last)) {
            lastName.setError(getString(R.string.error_field_required));
            focusView = lastName;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        if(!isValidPassword(password)){
            mPasswordView.setError(getString(R.string.error_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidPassword(String password) {
        return password.length() >= LoginData.PASSWORD_LENGTH;
    }

    @Override
    public void postLoginSuccess(){
        Intent groups = new Intent(this, LoginRegisterGroups.class);
        groups.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(groups);
        finish();
    }

    @Override
    public void postLoginFailure() {
        mEmailView.setError(getString(R.string.error_exists));
        mEmailView.requestFocus();
    }

    @Override
    public void postNetworkFailure() {
        Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_LONG).show();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final String firstName;
        private final String lastName;

        UserLoginTask(String email, String password, String first, String last) {
            mEmail = email;
            mPassword = password;
            firstName = first;
            lastName = last;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            loginPresenter.register(mEmail, mPassword, firstName, lastName);
            return null;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
