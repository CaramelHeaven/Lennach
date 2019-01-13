package com.caramelheaven.lennach.presentation.navigation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board.Board;

import java.util.List;

/**
 * Created by CaramelHeaven on 16:06, 13/01/2019.
 */
public class BoardItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Board> boardList;

    public BoardItemAdapter(List<Board> boardList) {
        this.boardList = boardList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_board, viewGroup, false);

        return new BoardVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return boardList.size();
    }

    public void updateAdapter(List<Board> boards) {
        boardList = boards;
    }

    public Board getItemByPosition(int pos) {
        return boardList.get(pos);
    }

    class BoardVH extends RecyclerView.ViewHolder {

        public BoardVH(@NonNull View itemView) {
            super(itemView);
        }
    }


}
