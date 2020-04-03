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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<Obat> mListObat;
    private  RecyclerViewHistory adapter;
    private RecyclerView recyclerView;

    private TextView tvNamaDokter, tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.rv_obat);

        Intent i = getIntent();
        String check_id = i.getStringExtra(MainActivity.EXTRA_ID);
        String penyakit = i.getStringExtra(MainActivity.EXTRA_DISEASE);
        String deskripsi = i.getStringExtra(MainActivity.EXTRA_DESKRIPSI);

        tvNamaDokter = findViewById(R.id.tv_dokter);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);

        tvDeskripsi.setText(deskripsi);


        mListObat = new ArrayList<>();
        mListObat.add(new Obat("Rhinos", "Flu Alergi"));
        mListObat.add(new Obat("Panadol", "Demam dan flu"));

        RecyclerViewInit();
    }

    void RecyclerViewInit() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewHistory(this, mListObat);
        recyclerView.setAdapter(adapter);
    }
}
