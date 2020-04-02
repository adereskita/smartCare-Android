package d3ifcool.org;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import d3ifcool.org.Models.Patients;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Patients> mListPatients;
    private  RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    //db
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();

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

        recyclerView = findViewById(R.id.rv_history);

        mListPatients = new ArrayList<>();
        mListPatients.add(new Patients("Ade Reskita", "Flu Alergi",
                "123SDWe", "Hindari Debu", "31 Maret 2020"));
        mListPatients.add(new Patients("Reski", "Demam Ber Darah",
                "123SDWee", "Rawat Inap", "2 April 2020"));

        RecyclerViewInit();
    }

    void RecyclerViewInit() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(this, mListPatients);
        recyclerView.setAdapter(adapter);
    }
}
