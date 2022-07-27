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

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    private Context context;
    private List<Datauser>list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public Dialog setDialog(){
        return dialog;
    }

    public DataAdapter(Context context,List<Datauser>list){
        this.context= context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_datawarga,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.email.setText(list.get(position).getInputemail());
    holder.nikktp.setText(list.get(position).getInputnik());
    holder.nikkk.setText(list.get(position).getInputnikkk());
    holder.names.setText(list.get(position).getInputnama());
    holder.agama.setText(list.get(position).getInputagama());
    holder.tanggal.setText(list.get(position).getInputtgl());
    holder.nomer.setText(list.get(position).getInputnomer());
    holder.alamat.setText(list.get(position).getInputalamat());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
    TextView email,nikktp,nikkk,names,agama,tanggal,nomer,alamat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.emailwar);
            nikktp=itemView.findViewById(R.id.nikwar);
            nikkk=itemView.findViewById(R.id.kkwar);
            names=itemView.findViewById(R.id.namawar);
            agama=itemView.findViewById(R.id.agamawar);
            tanggal=itemView.findViewById(R.id.tglwar);
            nomer=itemView.findViewById(R.id.nomerwar);
            alamat=itemView.findViewById(R.id.alamatwar);
        }
    }
}
