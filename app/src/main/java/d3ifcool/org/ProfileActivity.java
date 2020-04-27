package d3ifcool.org;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import d3ifcool.org.Models.Users;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvNama, tvGender, tvAge, tvHeight, tvWeight;
    private CircleImageView circleImageView;
    private Button btn_editProfile;
    private ImageButton btn_back;
    private String UserId,userEmail;

    //db
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //auth user
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        tvNama = findViewById(R.id.tv_nama_profile);
        tvGender = findViewById(R.id.tv_Gender);
        tvAge = findViewById(R.id.tv_age);
        tvHeight = findViewById(R.id.tv_height);
        tvWeight = findViewById(R.id.tv_weight);
        circleImageView = findViewById(R.id.iv_profile);
        btn_editProfile = findViewById(R.id.btn_editProfile);
        btn_back = findViewById(R.id.ib_back);

        if (mFirebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
            finish();
        } else {
            UserId = user.getUid();
            userEmail = user.getEmail();

            setData();
        }

        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(i);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

                    tvNama.setText(mData.getNama());
                    tvNama.setAllCaps(true);

                    if (mData.getGender() == null) {
                        tvGender.setText("-");
                    } else {
                        tvGender.setText(mData.getGender());
                    }
                    if (mData.getBerat() == null) {
                        tvWeight.setText("-");
                    } else {
                        tvWeight.setText(mData.getBerat());
                    }
                    if (mData.getTinggi() == null) {
                        tvHeight.setText("-");
                    } else {
                        tvHeight.setText(mData.getTinggi());
                    }
                    if (mData.getUmur() == null) {
                        tvAge.setText("-");
                    } else {
                        tvAge.setText(mData.getUmur());
                    }

                    if (tvGender.getText().equals("Pria")){
                        circleImageView.setImageResource(R.drawable.man_avatar);
                    } else if (tvGender.getText().equals("Wanita")){
                        circleImageView.setImageResource(R.drawable.woman_avatar);
                    } else {
                        circleImageView.setImageResource(R.drawable.avatar);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
