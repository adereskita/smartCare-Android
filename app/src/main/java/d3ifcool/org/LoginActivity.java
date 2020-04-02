package d3ifcool.org;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText mEmail,mPassword;
    private ProgressBar mProgressBar;

    //db
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.edt_email);
        mPassword = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);

        mProgressBar = findViewById(R.id.progress_bar);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUser();
            }
        });

    }

    private void getUser() {
        String email = mEmail.getText().toString().trim();
        final String passwd = mPassword.getText().toString().trim();


        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(passwd)) {

            mProgressBar.setVisibility(View.VISIBLE);

            //authenticate user
            mFirebaseAuth.signInWithEmailAndPassword(email, passwd)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                if (passwd.length() < 6) {
                                    mPassword.setError("Password is too Short, at least 8 character");
                                }
                                Toast.makeText(getApplicationContext(), "Authentication Failed." + task.getException(), Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        }
                    });
        }else {
            Toast.makeText(this, "Make Sure you fill the data.", Toast.LENGTH_SHORT).show();
        }
    }
}
