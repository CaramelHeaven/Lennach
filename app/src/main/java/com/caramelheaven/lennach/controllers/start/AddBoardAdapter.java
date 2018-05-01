package com.caramelheaven.lennach.controllers.start;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.databinding.ItemAddBoardBinding;
import com.caramelheaven.lennach.datasourse.model.Board;

import java.util.List;

public class AddBoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Board> boardList;

    public AddBoardAdapter(List<Board> boardList) {
        this.boardList = boardList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAddBoardBinding bindingItem = ItemAddBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BoardViewHolder(bindingItem.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BoardViewHolder boardVH = (BoardViewHolder) holder;
        boardVH.binding.textBoard.setText(boardList.get(position).getBoard());
    }

    @Override
    public int getItemCount() {
        return boardList.size();
    }

    private class BoardViewHolder extends RecyclerView.ViewHolder {

        ItemAddBoardBinding binding;

        BoardViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.executePendingBindings();
            //setOnClickListener is here
        }
    }
}
