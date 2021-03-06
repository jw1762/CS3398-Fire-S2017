package com.example.derek.workouttracker20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class SettingsActivity extends AppCompatActivity {

    private EditText weight;
    private EditText feet;
    private EditText newPassword;
    private EditText inches;
    private TextView ShowUser;
    private TextView ShowPassword;

    public Button settingsButton;
    public void settingsActivity_launcher()
    {
        /*

        String weightStr = weight.getText().toString();
        String feetStr = feet.getText().toString();
        String inchesStr = inches.getText().toString();
        String newPasswordstr = newPassword.getText().toString();


        float feetValue = Float.parseFloat(feetStr);
        float inchesValue = Float.parseFloat(inchesStr);
        float weightValue = Float.parseFloat(weightStr);

        here I call the setters with these new floats
        */



        settingsButton = (Button)findViewById(R.id.SaveSettingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SettingsActivity.this, Homescreen.class);
                startActivity(myIntent);
            }
    });
}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settingsActivity_launcher();

        feet = (EditText) findViewById(R.id.Edit_Feet);
        inches = (EditText) findViewById(R.id.Edit_inches);
        weight = (EditText) findViewById(R.id.weight);
        ShowUser = (TextView) findViewById(R.id.Show_User);
        ShowPassword = (TextView) findViewById(R.id.Show_Password);

        Intent getUser = getIntent();
        User curUser = (User)getUser.getSerializableExtra("curUser");

        /*
        ShowUser.setText(curUser.getUsername());
        ShowPassword.setText(curUser.getPassword.());
        */
    }

}
