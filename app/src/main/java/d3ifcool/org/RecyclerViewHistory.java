package d3ifcool.org;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import d3ifcool.org.Models.Obat;
import d3ifcool.org.Models.Patients;

public class RecyclerViewHistory extends RecyclerView.Adapter<RecyclerViewHistory.ViewHolder> {

    private Context mContext;
    private ArrayList<Obat> mData;

    public RecyclerViewHistory(Context mContext, ArrayList<Obat> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.medicine_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO : di isi dengan textview, glider, dsb..
        Obat mObat = mData.get(position);
        holder.tvNamaObat.setText(mObat.getNama_obat());
//        holder.imageView.setText(mPatient.getSymptomp());
        holder.tvKeterangan.setText(mObat.getKeterangan_obat());
    }

    @Override
    public int getItemCount() {
        return  mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //private Textview
        private TextView tvNamaObat, tvKeterangan;
        private ImageView iv_obat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_obat = itemView.findViewById(R.id.iv_obat);
            tvNamaObat = itemView.findViewById(R.id.tv_obat);
            tvKeterangan = itemView.findViewById(R.id.tv_obat_desc);
        }
    }
}
