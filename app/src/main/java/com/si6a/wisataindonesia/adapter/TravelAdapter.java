package com.si6a.wisataindonesia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.si6a.wisataindonesia.R;
import com.si6a.wisataindonesia.Utilities.Utilities;
import com.si6a.wisataindonesia.model.TravelData;

import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ViewHolder> {

    private final Context context;
    private final List<TravelData> mList;

    public TravelAdapter(Context context, List<TravelData> mList) {
        this.context = context;
        this.mList = mList;
    }

    private OnItemClickListener listener = null;

    public interface OnItemClickListener{
        void onItemDetail(int position);
        void onItemEdited(int position);
        void onItemDeleted(int position);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TravelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_wisata, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelAdapter.ViewHolder holder, int position) {
        TravelData data = mList.get(position);

        holder.ivWisata.setImageBitmap(Utilities.convertBase64ToBitmap(data.getImage()));
        holder.tvWisata.setText(data.getTitle());
        holder.tvDeskripsi.setText(data.getDescription());

        int adapterPosition = holder.getAdapterPosition();
        holder.btnDetail.setOnClickListener(view -> {
            if (adapterPosition != RecyclerView.NO_POSITION){
                listener.onItemDetail(adapterPosition);
            }
        });
        holder.btnUbah.setOnClickListener(view -> {
            if (adapterPosition != RecyclerView.NO_POSITION){
                listener.onItemEdited(adapterPosition);
            }
        });
        holder.btnHapus.setOnClickListener(view -> {
            if (adapterPosition != RecyclerView.NO_POSITION){
                listener.onItemDeleted(adapterPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivWisata;
        TextView tvWisata, tvDeskripsi;
        Button btnUbah, btnDetail, btnHapus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivWisata = itemView.findViewById(R.id.iv_wisata);
            tvWisata = itemView.findViewById(R.id.tv_wisata);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            btnDetail = itemView.findViewById(R.id.btn_deskripsi);
            btnUbah = itemView.findViewById(R.id.btn_ubah);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
        }
    }
}
