package com.example.esthere.gett2.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.esthere.gett2.R;
import com.example.esthere.gett2.model.backend.IAction;
import com.example.esthere.gett2.model.datasource.Globals;
import com.example.esthere.gett2.model.entities.Driver;
import com.example.esthere.gett2.utils.Dialogs;
import com.example.esthere.gett2.utils.SharedPref;

public class LoginActivity extends Activity {

    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void buttonClicked(View view) {
        SharedPref.savePreference(LoginActivity.this,"email",email.getText().toString());
        final Driver driver = new Driver(email.getText().toString(),password.getText().toString());
        Globals.backend.Authenticate(driver, new IAction<Boolean>() {
            @Override
            public void onSuccess(Boolean obj) {
                if (obj == false) {
                    Dialogs.Dialog(LoginActivity.this,getString(R.string.FIREBASE),getString(R.string.WRONG_USER_PASSWORD),getString(R.string.BUTTON_CLOSE));
                    return;
                }
                else {
                    Globals.driver = new Driver();
                    Globals.driver.setEmail(email.getText().toString());
                    finish();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Exception ex) {
                Dialogs.Dialog(LoginActivity.this,getString(R.string.FIREBASE),ex.getMessage(),getString(R.string.BUTTON_CLOSE));
            }
        });
    }
    public void signupClicked(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
