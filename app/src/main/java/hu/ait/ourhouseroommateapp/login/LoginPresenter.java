package hu.ait.ourhouseroommateapp.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import hu.ait.ourhouseroommateapp.ui.Presenter;

/**
 * Created by nasheyarahman on 5/11/17.
 */

public class LoginPresenter extends Presenter<LoginScreen> {

    private FirebaseAuth firebaseAuth;

    public LoginPresenter() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void loginWithCredentials(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            screen.postLoginSuccess();
        } else {
            screen.postLoginFailure();
        }
    }


    public void loginToFirebase(String username, String password){
        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    screen.postLoginSuccess();
                } else {
                    screen.postLoginFailure();
                }
            }
        });
    }


    public void registerInFirebase(String email, String password, final String firstName, final String lastName){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            firebaseUser.updateProfile(new UserProfileChangeRequest.Builder().
                                    setDisplayName(firstName+" "+lastName).
                                    build());

                            screen.postLoginSuccess();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        screen.postLoginFailure();
                    }
                });
    }

    public void register(String email, String password, String first, String last){
        registerInFirebase(email, password, first, last);
    }
}
