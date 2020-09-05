package com.gads2020.leaderboardapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

import static com.gads2020.leaderboardapp.Constants.EMAIL_PARAM;
import static com.gads2020.leaderboardapp.Constants.FIRST_NAME_PARAM;
import static com.gads2020.leaderboardapp.Constants.GITHUB_LINK_PARAM;
import static com.gads2020.leaderboardapp.Constants.LAST_NAME_PARAM;

public class SubmitProjectActivity extends AppCompatActivity implements View.OnClickListener,SubmitCallback {

    Button button;
    EditText firstNameEditText, lastNameEditText, emailEditText, githubLinkEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        button = findViewById(R.id.submit_button_large);
        button.setOnClickListener(this);

        firstNameEditText = findViewById(R.id.first_name_edit_text);
        lastNameEditText = findViewById(R.id.last_name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        githubLinkEditText = findViewById(R.id.github_link_edit_text);

    }

    @Override
    public void onClick(View view) {

        Dialog confirmDialog = new Dialog(this);
        View dialogView = getLayoutInflater().inflate(R.layout.confirm_submit_layout,null);
        ImageView cancelIcon = dialogView.findViewById(R.id.cancel_icon);

        cancelIcon.setOnClickListener(icon -> {
            confirmDialog.dismiss();
        });

        Button yesButton = dialogView.findViewById(R.id.yes_button);
        yesButton.setOnClickListener(yesBtnView -> {
           confirmDialog.dismiss();

           if(!isAnyFieldEmpty()) {
               // Submit the project data
               HashMap<String, String> fields = new HashMap<>();
               fields.put(FIRST_NAME_PARAM,firstNameEditText.getText().toString().trim());
               fields.put(LAST_NAME_PARAM,lastNameEditText.getText().toString().trim());
               fields.put(EMAIL_PARAM,emailEditText.getText().toString().trim());
               fields.put(GITHUB_LINK_PARAM,githubLinkEditText.getText().toString().trim());

               APIUtils.submitData(fields,this);
           }else{
               showFailureDialog();
               Toast.makeText(this,"All fields must be filled",Toast.LENGTH_SHORT).show();
           }

        });

        confirmDialog.setContentView(dialogView);

        confirmDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        confirmDialog.show();

        confirmDialog.getWindow().setLayout(getDialogWidth(),500);
    }

    private int getDialogWidth() {
        int deviceWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int dialogWidth = deviceWidth - (int)(deviceWidth*0.2);
        return dialogWidth;
    }

    private boolean isAnyFieldEmpty() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String githubLink = githubLinkEditText.getText().toString().trim();

        return firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || githubLink.isEmpty();
    }

    private void showSuccessDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.submit_success_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        dialog.getWindow().setLayout(getDialogWidth(),500);

        dismissDialogAfterDelay(dialog);

        firstNameEditText.setText("");
        lastNameEditText.setText("");
        emailEditText.setText("");
        githubLinkEditText.setText("");

    }

    private void showFailureDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.submit_failed_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        dialog.getWindow().setLayout(getDialogWidth(),500);

        dismissDialogAfterDelay(dialog);
    }

    private void dismissDialogAfterDelay(Dialog dialog) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },3000);
    }

    @Override
    public void onSubmitSuccessful() {
        showSuccessDialog();
    }

    @Override
    public void onSubmitFailed(Throwable t) {
        showFailureDialog();
        Toast.makeText(this,t.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}