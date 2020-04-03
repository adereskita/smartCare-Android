package d3ifcool.org;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import d3ifcool.org.Models.Patients;
import d3ifcool.org.Models.Users;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DISEASE = "extra_disease";
    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_DESKRIPSI = "extra_deskripsi";

    private ArrayList<Patients> mListPatients;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    private TextView tvNama, tvDarah, tvTinggi, tvBerat, tvUmur;
    private String UserId,userEmail;

    //db
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //auth user
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        UserId = user.getUid();
        userEmail = user.getEmail();

        if (mFirebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
            finish();
        }

        //get current user
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser cUser = firebaseAuth.getCurrentUser();

                if (cUser == null) {
                    startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                    finish();
                }
            }
        };

        tvNama = findViewById(R.id.tv_nama);
        tvDarah = findViewById(R.id.tv_tekanan_darah);
        tvTinggi = findViewById(R.id.tv_tinggi);
        tvBerat = findViewById(R.id.tv_berat);
        tvUmur = findViewById(R.id.tv_umur);


        DatabaseReference users = mDbRef.child("user").child(UserId);
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    Users mData = new Users();
                    mData.setNama(datasnapshot.getValue(Users.class).getNama());

                    tvNama.setText(mData.getNama());
                    tvNama.setAllCaps(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        recyclerView = findViewById(R.id.rv_history);

        DatabaseReference symtomps = mDbRef.child("Pasien");
        Query mSearchQuery = symtomps.orderByChild("email").equalTo(userEmail);

        mSearchQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                mListPatients = new ArrayList<>();
                if (datasnapshot.exists()) {

                    mListPatients.clear();
                    for (DataSnapshot ds : datasnapshot.getChildren()) {
                        Patients mModel = ds.getValue(Patients.class);


                        if (mModel.getEmail() != null) {
                            mListPatients.add(mModel);
                        } else {
                            mListPatients.remove(mModel);
                        }
                    }
                    RecyclerViewInit();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void RecyclerViewInit() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(this, mListPatients);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ListClickListener() {
            @Override
            public void onItemClick(int position, View v, String deskripsi, String disease, String id) {
                Intent i = new Intent(MainActivity.this, HistoryActivity.class);
                i.putExtra(EXTRA_ID, id);
                i.putExtra(EXTRA_DISEASE, disease);
                i.putExtra(EXTRA_DESKRIPSI, deskripsi);
                startActivity(i);
            }
        });
    }


}
