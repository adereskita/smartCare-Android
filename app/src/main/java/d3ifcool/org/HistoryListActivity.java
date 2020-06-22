package d3ifcool.org;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import d3ifcool.org.Models.Patients;
import d3ifcool.org.Models.Users;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class HistoryListActivity extends AppCompatActivity {

    public static final String EXTRA_DISEASE = "extra_disease";
    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_ID_CHECK = "extra_id_check";
    public static final String EXTRA_DESKRIPSI = "extra_deskripsi";
    public static final String EXTRA_DOKTER =  "extra_dokter";

    private ArrayList<Patients> mListPatients;
    private RecyclerHistoryAdapter adapter;
    private RecyclerView recyclerView;

    private TextView tvNama, tvDarah, tvTinggi, tvBerat, tvUmur, tvNull;
    private Button btn_see_more;
    private String UserId,userEmail, id_nik;

    //db
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        tvNull = findViewById(R.id.tv_no_history);

        //auth user
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView = findViewById(R.id.rv_history_list);

        if (mFirebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
            finish();
        } else {
            UserId = user.getUid();
            userEmail = user.getEmail();

            setData();
        }

    }

    private void setData() {

        DatabaseReference users = mDbRef.child("user").child(UserId);
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    Users mData = new Users();

                    mData.setId_nik(datasnapshot.getValue(Users.class).getId_nik());

                    id_nik = mData.getId_nik().trim().toString();

                    DatabaseReference symtomps = mDbRef.child("Pasien");
                    Query mSearchQuery = symtomps.orderByChild("id_nik").equalTo(id_nik);

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
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void RecyclerViewInit() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerHistoryAdapter(this, mListPatients);
        recyclerView.setAdapter(adapter);

        if (mListPatients != null) {
            tvNull.setVisibility(View.GONE);
        }

        adapter.setOnItemClickListener(new RecyclerHistoryAdapter.ListClickListener() {
            @Override
            public void onItemClick(int position, View v, String deskripsi, String dokter, String disease, String id, String id_check) {
                Intent i = new Intent(HistoryListActivity.this, HistoryActivity.class);
                i.putExtra(EXTRA_ID, id);
                i.putExtra(EXTRA_DISEASE, disease);
                i.putExtra(EXTRA_DESKRIPSI, deskripsi);
                i.putExtra(EXTRA_ID_CHECK, id_check);
                i.putExtra(EXTRA_DOKTER, dokter);
                startActivity(i);
            }
        });

    }
}
