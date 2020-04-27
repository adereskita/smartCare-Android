package d3ifcool.org;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import d3ifcool.org.Models.Users;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    private EditText edt_nama, edt_age, edt_height, edt_weight;
    private RadioGroup rg_gender;
    private RadioButton rb_pria, rb_wanita;
    private Button btn_edit;
    private String UserId,userEmail;

    //db
    private DatabaseReference dbUser;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //auth user
        dbUser = FirebaseDatabase.getInstance().getReference("user");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        edt_nama = findViewById(R.id.edt_nama);
        edt_age = findViewById(R.id.edt_age);
        edt_height = findViewById(R.id.edt_height);
        edt_weight = findViewById(R.id.edt_weight);
        rg_gender = findViewById(R.id.rg_gender);
        rb_pria = findViewById(R.id.rdo_pria);
        rb_wanita = findViewById(R.id.rdo_wanita);
        btn_edit = findViewById(R.id.btn_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (mFirebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
            finish();
        } else {
            UserId = user.getUid();
            userEmail = user.getEmail();

            setData();
        }

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser();
                Intent i = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
    }


    private void setData() {
        DatabaseReference users = mDbRef.child("user").child(UserId);
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    Users mData = new Users();
                    mData.setNama(datasnapshot.getValue(Users.class).getNama());
                    mData.setBerat(datasnapshot.getValue(Users.class).getBerat());
                    mData.setGender(datasnapshot.getValue(Users.class).getGender());
                    mData.setTinggi(datasnapshot.getValue(Users.class).getTinggi());
                    mData.setUmur(datasnapshot.getValue(Users.class).getUmur());

                    edt_nama.setText(mData.getNama());
                    edt_nama.setAllCaps(true);

                    if (mData.getUmur() == null) {
                        edt_age.setText(null);
                    } else {
                        edt_age.setText(mData.getUmur());
                    }
                    if (mData.getTinggi() == null) {
                        edt_height.setText(null);
                    } else {
                        edt_height.setText(mData.getTinggi());
                    }
                    if (mData.getBerat() == null) {
                        edt_weight.setText(null);
                    } else {
                        edt_weight.setText(mData.getBerat());
                    }
                    if (mData.getGender() == null) {
                        rb_wanita.setSelected(false);
                        rb_pria.setSelected(false);
                    } else if (mData.getGender().equals("Pria")){
                        rb_pria.setSelected(true);
                    } else if (mData.getGender().equals("Wanita")) {
                        rb_wanita.setSelected(true);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void editUser(){

        final int selectedGender = rg_gender.getCheckedRadioButtonId();

        String gender = null;

        if (selectedGender == rb_pria.getId()) {
            gender = rb_pria.getText().toString().trim();

        } else if (selectedGender == rb_wanita.getId()) {
            gender = rb_wanita.getText().toString().trim();
        }

        final String name = edt_nama.getText().toString().trim();
        final String age = edt_age.getText().toString().trim();
        final String height = edt_height.getText().toString().trim();
        final String weight = edt_weight.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(height) && !TextUtils.isEmpty(weight)){


            FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

            //create user tabel Real-Time

            // before id = dbUser.push().getKey();

            Users user = new Users(UserId, name, userEmail, gender, weight, age, height);

            dbUser.child(UserId).setValue(user);

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }else {
            Toast.makeText(this, "Please, input your data first.",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
