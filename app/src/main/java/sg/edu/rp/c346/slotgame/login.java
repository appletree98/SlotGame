package sg.edu.rp.c346.slotgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.w3c.dom.Text;

public class login extends AppCompatActivity {

    LoginButton btnLogin;
    TextView textview;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (LoginButton)findViewById(R.id.btnLogin);
        textview = (TextView)findViewById(R.id.textview);

        callbackManager = CallbackManager.Factory.create();
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                textview.setText("Login Success \n"+
                loginResult.getAccessToken().getUserId()+
                "\n"+loginResult.getAccessToken().getToken());

                Intent i = new Intent(login.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {
                textview.setText("Login has been cancelled");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

}
