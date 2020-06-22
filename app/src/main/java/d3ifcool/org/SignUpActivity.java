package d3ifcool.org;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import d3ifcool.org.Models.Users;

public class SignUpActivity extends AppCompatActivity {

    private Button btn_signUp;
    private EditText mNama,mEmail, mNik, mPassword;
    private ProgressBar mProgressBar;

    private DatabaseReference dbUser;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbUser = FirebaseDatabase.getInstance().getReference("user");
        mFirebaseAuth = FirebaseAuth.getInstance();

        mProgressBar = findViewById(R.id.progress_bar);
        mNik = findViewById(R.id.edt_nik);
        mNama = findViewById(R.id.edt_nama);
        mEmail = findViewById(R.id.edt_email);
        mPassword = findViewById(R.id.edt_password);

        btn_signUp = findViewById(R.id.btn_signUp);

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            addUser();
            }
        });
    }

    private void addUser(){
        final String name = mNama.getText().toString().trim();
        final String nik = mNik.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        final String passwd = mPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(nik) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(passwd)){

            if (passwd.length() < 6){
                Toast.makeText(getApplicationContext(),"Password is too short.", Toast.LENGTH_SHORT).show();
            }

            mProgressBar.setVisibility(View.VISIBLE);

            mFirebaseAuth.createUserWithEmailAndPassword(email,passwd)
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Authentication Failed." +task.getException(), Toast.LENGTH_SHORT).show();
                            }else {

                                FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

                                //create user tabel Real-Time

                                // before id = dbUser.push().getKey();

                                String id = mUser.getUid();

                                Users user = new Users(id, nik, name, email);

                                dbUser.child(id).setValue(user);

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        }
                    });

        }else {
            Toast.makeText(this, "Please, input your data first.",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
