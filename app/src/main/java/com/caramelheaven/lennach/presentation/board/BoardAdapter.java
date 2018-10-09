package com.caramelheaven.lennach.presentation.board;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.presentation.common.AdapterMethods;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements AdapterMethods<Usenet> {

    private List<Usenet> usenetList;
    private Set<Usenet> usenetUnique;

    private BoardOnItemClickListener boardOnItemClickListener;

    public BoardAdapter(List<Usenet> usenetList) {
        this.usenetList = usenetList;
        usenetUnique = new LinkedHashSet<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_thread, viewGroup, false);
        return new UsenetVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UsenetVH usenetVH = (UsenetVH) viewHolder;
        usenetVH.tvTitle.setText(Html.fromHtml(usenetList.get(i).getComment()));
        usenetVH.tvDate.setText("Anon - " + usenetList.get(i).getDate());

        if (usenetList.get(i).getImage().getThumbnail() != null) {
            Glide.with(usenetVH.ivThread.getContext())
                    .load("https://2ch.hk" + usenetList.get(i).getImage().getThumbnail())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(usenetVH.ivThread);
        }
    }

    @Override
    public int getItemCount() {
        return usenetList.size();
    }

    @Override
    public void updateAdapter(List<Usenet> items) {
        usenetUnique.addAll(items);
        usenetList.clear();
        usenetList.addAll(usenetUnique);
        notifyDataSetChanged();
    }

    @Override
    public Usenet getItemByPosition(int position) {
        return usenetList.get(position);
    }

    private class UsenetVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle, tvDate;
        ImageView ivThread;
        CardView cvContainer, cvImage;

        UsenetVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tv_date);
            ivThread = itemView.findViewById(R.id.ivThread);
            cvContainer = itemView.findViewById(R.id.cardView);
            cvImage = itemView.findViewById(R.id.cv_container_image);
            cvImage.setOnClickListener(this::onClick);
            cvContainer.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            if (v instanceof CardView) {
                if (v == cvImage) {
                    boardOnItemClickListener.onImageClick(getAdapterPosition(), ivThread);
                } else {
                    boardOnItemClickListener.onUsenetClick(getAdapterPosition());
                }
            }
        }
    }

    public void setBoardOnItemClickListener(BoardOnItemClickListener boardOnItemClickListener) {
        this.boardOnItemClickListener = boardOnItemClickListener;
    }

    public ArrayList<Usenet> getUsenetList() {
        return new ArrayList<>(usenetList);
    }
}
