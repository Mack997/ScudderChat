package com.example.mayankagarwal.thechatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {

    private EditText currentStatus;
    private ImageView update_btn;

    private Toolbar mToolbar;
    private ProgressDialog mProgress;

    //firebase
    private DatabaseReference mStatusDatabse;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        currentStatus = (EditText)findViewById(R.id.status_edittext);
        update_btn = (ImageView) findViewById(R.id.save_status);


        String status_value = getIntent().getStringExtra("status_value");
        currentStatus.setText(status_value);
        currentStatus.setSelection(status_value.length());

        //toolbar
        mToolbar = (Toolbar)findViewById(R.id.status_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //firebase
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();
        mStatusDatabse = FirebaseDatabase.getInstance().getReference().child("users").child(current_uid);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mProgress = new ProgressDialog(StatusActivity.this);
                mProgress.setTitle("Saving Changes");
                mProgress.setMessage("Please, Wait While the Status is being updated!!");
                mProgress.show();

                String status = currentStatus.getText().toString();

                mStatusDatabse.child("status").setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            mProgress.dismiss();
                            Intent back = new Intent(StatusActivity.this,SettingsActivity.class);
                            startActivity(back);
                            finish();
                        }else {
                            mProgress.hide();
                            Toast.makeText(StatusActivity.this, "Chnages Are Not Saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home){
            Intent back = new Intent(StatusActivity.this,SettingsActivity.class);
            startActivity(back);
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(StatusActivity.this,SettingsActivity.class);
        startActivity(back);
        finish();
    }
}
