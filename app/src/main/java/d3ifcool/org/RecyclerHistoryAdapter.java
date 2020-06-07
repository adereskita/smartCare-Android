package d3ifcool.org;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import d3ifcool.org.Models.Patients;

public class RecyclerHistoryAdapter extends RecyclerView.Adapter<RecyclerHistoryAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Patients> mData;
    private static ListClickListener mListener;


    public RecyclerHistoryAdapter(Context mContext, ArrayList<Patients> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.history_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO : di isi dengan textview, glider, dsb..
        Patients mPatient = mData.get(position);
        holder.tvSymptomp.setText(mPatient.getDisease());
//        holder.imageView.setText(mPatient.getSymptomp());
        holder.tvKeterangan.setText(mPatient.getDeskripsi());
        holder.tvTanggal.setText(mPatient.getTanggal());

        deskripsi = mPatient.getDeskripsi();
        disease = mPatient.getDisease();
        id = mPatient.getId_check();
        diastol = mPatient.getDiastol();
        sistol = mPatient.getSistol();
        tanggal = mPatient.getTanggal();
        id_check =  mPatient.getId_check();
        dokter =  mPatient.getNama_dokter();
    }

    @Override
    public int getItemCount() {
        return  mData == null ? 0 : mData.size();
//        return mData.size();
    }

    public interface ListClickListener {
        void onItemClick(int position, View v, String deskripsi, String dokter, String disease, String id, String id_check);
    }

    public void setOnItemClickListener(ListClickListener clickListener) {
        RecyclerHistoryAdapter.mListener = clickListener;
    }

    private String deskripsi, disease, id, tanggal, sistol, diastol, id_check, dokter;

    public Patients getItem(int position) {
        return mData.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //private Textview
        private TextView tvSymptomp, tvKeterangan, tvTanggal;
        private ImageButton IbShare;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSymptomp = itemView.findViewById(R.id.tv_symptom);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            IbShare = itemView.findViewById(R.id.ivb_share);

            itemView.setFocusable(true);
            itemView.setClickable(true);

            itemView.setOnClickListener(this);

            IbShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String shareBody = "Penyakit : "+disease + "\n" +
                                        "Tanggal : "+tanggal + "\n" +
                                        "Dokter : "+dokter + "\n" +
                                        "Sistol : "+sistol + "\n" +
                                        "Diastol : "+diastol + "\n" +
                                        "Saran Dokter : " +deskripsi;
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "SmartCare Report: "+disease +" "+ tanggal);
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    mContext.startActivity(Intent.createChooser(sharingIntent, "Share your data with"));
                }
            });
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition(), v, deskripsi, dokter, disease, id, id_check);
        }
    }
}
