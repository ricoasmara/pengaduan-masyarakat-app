package com.example.pengaduanmasyakarat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pengaduanmasyakarat.R;
import com.example.pengaduanmasyakarat.model.Datauser;
import com.example.pengaduanmasyakarat.model.laporanuser;

import java.util.Calendar;
import java.util.List;

public class LaporanwargaAdapater extends RecyclerView.Adapter<LaporanwargaAdapater.MyViewHolder> {
    private Context context;
    private List<laporanuser> listlaporan;
    private List<Datauser>listdata;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public LaporanwargaAdapater(Context context,List<laporanuser>listlaporan){
        this.context = context;
        this.listlaporan= listlaporan;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_laporan, parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public int getItemCount()
    {
        return listlaporan.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.nama.setText(listlaporan.get(position).getNama());
    holder.tgl.setText(listlaporan.get(position).getTgl());
    holder.jam.setText(listlaporan.get(position).getJam());
    holder.laporan.setText(listlaporan.get(position).getLaporan());
    holder.solusi.setText(listlaporan.get(position).getSolusi());
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama,tgl,jam,laporan,solusi;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nama=itemView.findViewById(R.id.namaread);
        tgl=itemView.findViewById(R.id.tglread);
        jam=itemView.findViewById(R.id.jamread);
        laporan=itemView.findViewById(R.id.laporanread);
        solusi=itemView.findViewById(R.id.solosiread);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog!=null){
                    dialog.onClick(getLayoutPosition());
                }
            }
        });
    }

}
}
