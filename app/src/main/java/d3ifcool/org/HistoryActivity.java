package d3ifcool.org;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import d3ifcool.org.Models.Obat;
import d3ifcool.org.Models.Patients;
import d3ifcool.org.Models.Users;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<Obat> mListObat;
    private RecyclerViewHistory adapter;
    private RecyclerView recyclerView;

    private TextView tvNamaDokter, tvDeskripsi;
    private String UserId,userEmail, id_check, dokter;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //auth user
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        recyclerView = findViewById(R.id.rv_obat);

        Intent i = getIntent();
        String check_id = i.getStringExtra(MainActivity.EXTRA_ID);
        String penyakit = i.getStringExtra(MainActivity.EXTRA_DISEASE);
        String deskripsi = i.getStringExtra(MainActivity.EXTRA_DESKRIPSI);
        id_check = i.getStringExtra(MainActivity.EXTRA_ID_CHECK);
        dokter = i.getStringExtra(MainActivity.EXTRA_DOKTER);

        tvNamaDokter = findViewById(R.id.tv_dokter);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);

        tvDeskripsi.setText(deskripsi);

        if (mFirebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
            finish();
        } else {
            UserId = user.getUid();
            userEmail = user.getEmail();

            tvNamaDokter.setText("dr. "+dokter);
            setData();
        }
    }

    private void setData() {

        DatabaseReference obat = mDbRef.child("Obat");
        Query mSearchQuery = obat.orderByChild("id_check").equalTo(id_check);

        mSearchQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                mListObat = new ArrayList<>();
                if (datasnapshot.exists()) {

                    mListObat.clear();
                    for (DataSnapshot ds : datasnapshot.getChildren()) {
                        Obat mModel = ds.getValue(Obat.class);


                        if (mModel.getUser_email() != null) {
                            mListObat.add(mModel);
                        } else {
                            mListObat.remove(mModel);
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

    void RecyclerViewInit() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewHistory(this, mListObat);
        recyclerView.setAdapter(adapter);
    }
}
