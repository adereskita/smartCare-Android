package d3ifcool.org;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import d3ifcool.org.Models.Obat;
import d3ifcool.org.Models.Patients;

import android.os.Bundle;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<Obat> mListObat;
    private  RecyclerViewHistory adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.rv_obat);

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
