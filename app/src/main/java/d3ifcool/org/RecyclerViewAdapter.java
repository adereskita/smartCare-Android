package d3ifcool.org;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import d3ifcool.org.Models.Patients;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Patients> mData;

    public RecyclerViewAdapter(Context mContext, ArrayList<Patients> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.history_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO : di isi dengan textview, glider, dsb..
        Patients mPatient = mData.get(position);
        holder.tvSymptomp.setText(mPatient.getSymptomp());
//        holder.imageView.setText(mPatient.getSymptomp());
        holder.tvKeterangan.setText(mPatient.getKeterangan_doc());
        holder.tvTanggal.setText(mPatient.getTanggal());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //private Textview
        private TextView tvSymptomp, tvKeterangan, tvTanggal;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvSymptomp = itemView.findViewById(R.id.tv_symptom);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, HistoryActivity.class);
                    mContext.startActivity(i);
                }
            });
        }
    }
}
