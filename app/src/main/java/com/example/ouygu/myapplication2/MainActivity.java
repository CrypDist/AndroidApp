package com.example.ouygu.myapplication2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Client.Property;
import Client.Util.Config;
import Client.Util.CrypDist;

public class MainActivity extends AppCompatActivity {

    // elements in content_main.xml
    EditText username_txt;
    EditText password_txt;
    Button signin_btn;

    // holding username and password information of user
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing elements in content_main.xml
        username_txt = (EditText) findViewById(R.id.username_txt);
        password_txt = (EditText) findViewById(R.id.password_txt);
        signin_btn = (Button) findViewById(R.id.signin_btn);

        // Alert Dialog for empty email address and password
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Email address and password fields cannot be empty");
        builder.setCancelable(true);

        // Setting the dialog button
        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // Create alert dialog
        AlertDialog alertAuthentication = builder.create();

        // Listener for signin_btn
        signin_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!username_txt.getText().toString().isEmpty() && !password_txt.getText().toString().isEmpty()) {
                    username = username_txt.getText().toString();
                    password = password_txt.getText().toString();

                    // log4j authentication
                    //Property p = new Property();

                    if(     username.equals("Client1") && password.equals("Pass1") ||
                            username.equals("Client2") && password.equals("Pass2") ||
                            username.equals("Client3") && password.equals("Pass3") ||
                            username.equals("Client4") && password.equals("Pass4") ||
                            username.equals("Client5") && password.equals("Pass5") ||
                            username.equals("Client6") && password.equals("Pass6") ||
                            username.equals("Client7") && password.equals("Pass7") ||
                            username.equals("Client8") && password.equals("Pass8") ||
                            username.equals("Client9") && password.equals("Pass9") ||
                            username.equals("Client10") && password.equals("Pass10") ||
                            username.equals("Client11") && password.equals("Pass11") ||
                            username.equals("Client12") && password.equals("Pass12") ||
                            username.equals("Client13") && password.equals("Pass13") )
                    {
                        Config.USER_NAME = username;
                        Config.USER_PASS = password;

                        Toast.makeText(MainActivity.this, "Log in successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, AkActivity.class);
                        startActivity(i);

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Log in failed", Toast.LENGTH_SHORT).show();
                        username_txt.setText("");
                        password_txt.setText("");
                    }

                } else {
                    alertAuthentication.show();
                }
            }
        });
    }



}
