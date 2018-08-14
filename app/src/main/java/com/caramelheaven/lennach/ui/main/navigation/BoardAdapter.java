package com.caramelheaven.lennach.ui.main.navigation;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.model.BoardNavModel;
import com.caramelheaven.lennach.ui.base.AdapterMethods;
import com.caramelheaven.lennach.utils.myOnItemClickListener;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterMethods<BoardNavModel> {

    private List<BoardNavModel> boardNavModelList;
    private Set<BoardNavModel> boardUnique;

    private myOnItemClickListener myOnItemClickListener;

    public BoardAdapter(List<BoardNavModel> boardNavModelList) {
        this.boardNavModelList = boardNavModelList;
        boardUnique = new LinkedHashSet<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_nav_board, viewGroup, false);
        return new BoardVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        BoardVH boardVH = (BoardVH) viewHolder;
        boardVH.tvBoardName.setText(boardNavModelList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return boardNavModelList.size();
    }

    @Override
    public void updateAdapter(List<BoardNavModel> models) {
        boardUnique.addAll(models);
        if (boardNavModelList.size() != 0)
            boardNavModelList.clear();
        boardNavModelList.addAll(boardUnique);
        notifyDataSetChanged();
    }

    @Override
    public BoardNavModel getItemByPosition(int position) {
        return boardNavModelList.get(position);
    }

    @Override
    public List<BoardNavModel> getItems() {
        return null;
    }

    public void setMyOnItemClickListener(com.caramelheaven.lennach.utils.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    private class BoardVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvBoardName;
        ImageView ivImageBoard;
        CardView cardView;

        public BoardVH(@NonNull View itemView) {
            super(itemView);
            tvBoardName = itemView.findViewById(R.id.tv_name_board);
            ivImageBoard = itemView.findViewById(R.id.iv_board);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            myOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }
}
