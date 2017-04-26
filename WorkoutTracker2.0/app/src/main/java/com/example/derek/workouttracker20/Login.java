package com.example.derek.workouttracker20;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Login extends Activity implements View.OnClickListener
{
    private EditText user, pass;
    public Button regB;
    public Button loginB;
    private ProgressDialog pDialog;

    private ArrayList userList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText)findViewById(R.id.userText);
        pass = (EditText)findViewById(R.id.passwordText);

        loginB = (Button)findViewById(R.id.login);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startHome = new Intent(Login.this, Homescreen.class);
                startActivity(startHome);
            }
        });

        regB = (Button)findViewById(R.id.register);
        regB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startReg = new Intent(Login.this, SignupActivity.class);
                startActivity(startReg);
            }
        });

        //Requesting Permissions
        ActivityCompat.requestPermissions(Login.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        ActivityCompat.requestPermissions(Login.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                2);
    }

    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.login:
                new AttemptLogin().execute();
            case R.id.register:
                //new AttemptReg().execute();
            default:
                break;
        }
    }

    class AttemptLogin extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Attempting Login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected void onPostExecute(String message)
        {
            pDialog.dismiss();
        }

        @Override
        protected String doInBackground(String... args)
        {
            ArrayList<User> savedUsers = new ArrayList<User>();
            //Check for success tag.
            boolean success = false;
            String username = user.getText().toString();
            String password = pass.getText().toString();
            User newUser = new User(username, password);
            userList.add(newUser);

            //Cant get it to save as file on phone. I really don't like Android.
            //saveData(userList);

            if(userList.contains(newUser))
            {
                success = true;
            }
            if(success == true)
            {
                Toast.makeText(Login.this, "Successful Registration!",
                        Toast.LENGTH_LONG).show();
                finish();
            }
            else
            {
                Toast.makeText(Login.this, "REGISTRATION FAILURE",
                        Toast.LENGTH_LONG).show();
            }
            //Send user to Homescreen page.
            Intent sendUser = new Intent(Login.this, Homescreen.class);
            sendUser.putExtra("curUser", newUser);

            //Send user to Settings page
            Intent sendUserToSettings = new Intent(Login.this, SettingsActivity.class);
            sendUserToSettings.putExtra("curUser", newUser);

            return "Done!";
        }
    }

    //Requesting Permission Method
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults)
    {
        switch (requestCode)
        {
            case 1:
            {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {                }
                else
                {                }
                return;
            }
            case 2:
            {
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {                }
                else
                {                }
                return;
            }
        }
    }
}