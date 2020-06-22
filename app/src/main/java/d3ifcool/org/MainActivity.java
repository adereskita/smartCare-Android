package d3ifcool.org;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import d3ifcool.org.Models.Patients;
import d3ifcool.org.Models.Users;
import de.hdodenhof.circleimageview.CircleImageView;

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

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DISEASE = "extra_disease";
    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_ID_CHECK = "extra_id_check";
    public static final String EXTRA_DOKTER = "extra_dokter";
    public static final String EXTRA_DESKRIPSI = "extra_deskripsi";

    private ArrayList<Patients> mListPatients;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    private CircleImageView circleImageView;
    private TextView tvNama, tvSistol, tvDiastol, tvTinggi, tvBerat, tvUmur, tvNull;
    private Button btn_see_more;
    private String UserId,userEmail, id_nik;
    private String gender = null;

    private ConstraintLayout const_profile;

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

        tvNama = findViewById(R.id.tv_nama);
        tvSistol = findViewById(R.id.tv_sistol);
        tvDiastol = findViewById(R.id.tv_diastol);
        tvTinggi = findViewById(R.id.tv_tinggi);
        tvBerat = findViewById(R.id.tv_berat);
        tvUmur = findViewById(R.id.tv_umur);
        btn_see_more = findViewById(R.id.btn_see_more);
        const_profile = findViewById(R.id.const_profile);
        circleImageView = findViewById(R.id.iv_picture);
        tvNull = findViewById(R.id.tv_no_history);

        if (mFirebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
            finish();
        } else {
            UserId = user.getUid();
            userEmail = user.getEmail();

            setData();
        }

        btn_see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HistoryListActivity.class);
                startActivity(i);
            }
        });

        const_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
        if (gender == null){
            circleImageView.setImageResource(R.drawable.avatar);
        }
    }

    private void setData() {
        // to show basic data user

        DatabaseReference users = mDbRef.child("user").child(UserId);
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    Users mData = new Users();

                    mData.setId_nik(datasnapshot.getValue(Users.class).getId_nik());
                    mData.setNama(datasnapshot.getValue(Users.class).getNama());
                    mData.setGender(datasnapshot.getValue(Users.class).getGender());
                    mData.setUmur(datasnapshot.getValue(Users.class).getUmur());
                    mData.setTinggi(datasnapshot.getValue(Users.class).getTinggi());
                    mData.setBerat(datasnapshot.getValue(Users.class).getBerat());
//                    mData.setDiastol(datasnapshot.getValue(Users.class).getDiastol());
//                    mData.setSistol(datasnapshot.getValue(Users.class).getSistol());

                    gender = mData.getGender();
                    id_nik = mData.getId_nik().trim().toString();


                    if (mData.getUmur() != null) {
                        tvUmur.setText(mData.getUmur());
                    }
                    if (mData.getBerat() != null) {
                        tvBerat.setText(mData.getBerat());
                    }
                    if (mData.getTinggi() != null) {
                        tvTinggi.setText(mData.getTinggi());
                    }

                    tvNama.setText(mData.getNama());
                    tvNama.setAllCaps(true);

                    if (gender != null) {
                        if (gender.equals("Pria")){
                            circleImageView.setImageResource(R.drawable.man_avatar);
                        } else if (gender.equals("Wanita")){
                            circleImageView.setImageResource(R.drawable.woman_avatar);
                        }
                    } else {
                        circleImageView.setImageResource(R.drawable.avatar);
                    }

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

                                    //to show sistol and diastol data
                                    if (mModel.getSistol() != null) {
                                        tvSistol.setText(mModel.getSistol());
                                    }
                                    if (mModel.getDiastol() != null) {
                                        tvDiastol.setText(mModel.getDiastol());
                                    }

                                    //to show list data
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


        recyclerView = findViewById(R.id.rv_history);

//        System.out.println("NIK: "+ id_nik);


//        DatabaseReference symtomps = mDbRef.child("Pasien");
//        Query mSearchQuery = symtomps.orderByChild("id_nik").equalTo(id_nik);
//
//        mSearchQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
//                mListPatients = new ArrayList<>();
//                if (datasnapshot.exists()) {
//
//                    mListPatients.clear();
//                    for (DataSnapshot ds : datasnapshot.getChildren()) {
//                        Patients mModel = ds.getValue(Patients.class);
//
//                        //to show sistol and diastol data
//                        if (mModel.getSistol() != null) {
//                            tvSistol.setText(mModel.getSistol());
//                        }
//                        if (mModel.getDiastol() != null) {
//                            tvDiastol.setText(mModel.getDiastol());
//                        }
//
//                        //to show list data
//                        if (mModel.getEmail() != null) {
//                            mListPatients.add(mModel);
//                        } else {
//                            mListPatients.remove(mModel);
//                        }
//                    }
//                    RecyclerViewInit();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

    private void RecyclerViewInit() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(this, mListPatients);
        recyclerView.setAdapter(adapter);

        if (mListPatients != null) {
            tvNull.setVisibility(View.GONE);
        }

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ListClickListener() {
            @Override
            public void onItemClick(int position, View v, String deskripsi, String disease, String id,
                                    String id_check, String dokter) {
                Intent i = new Intent(MainActivity.this, HistoryActivity.class);
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
