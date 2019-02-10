package com.caramelheaven.lennach.presentation.navigation;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.utils.Constants;
import com.caramelheaven.lennach.utils.bus.GlobalBus;
import com.caramelheaven.lennach.utils.bus.RxBus;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:06, 13/01/2019.
 * Adapter for reflect hot board items
 */
public class BoardItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Board> boardList;

    private static final int ADD_BOARD = -1;

    public BoardItemAdapter(List<Board> boardList) {
        this.boardList = boardList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == ADD_BOARD) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_board_click_to_add, viewGroup, false);
            return new AddBoardVH(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_board, viewGroup, false);
            return new BoardVH(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == ADD_BOARD) {
            //nothing
        } else {
            BoardVH boardVH = (BoardVH) viewHolder;
            Timber.d("toString: " + boardList.get(position).toString());
            boardVH.tvNameBoard.setText("/" + boardList.get(position).getBoardName());
        }
    }

    @Override
    public int getItemCount() {
        return boardList.size() + 1;
    }

    public void updateAdapter(List<Board> boards) {
        boardList = boards;
    }

    public Board getItemByPosition(int pos) {
        return boardList.get(pos);
    }

    @Override
    public int getItemViewType(int position) {
        Timber.d("pos: " + position + " and size: " + boardList);
        if (position == boardList.size()) {
            return ADD_BOARD;
        }
        return position;
    }

    class BoardVH extends RecyclerView.ViewHolder {
        TextView tvNameBoard;
        CardView cvBase;

        public BoardVH(@NonNull View itemView) {
            super(itemView);
            tvNameBoard = itemView.findViewById(R.id.tv_name_board);
            cvBase = itemView.findViewById(R.id.cv_base);

            cvBase.setOnClickListener(v ->
                    RxBus.getInstance().passChooseBoard(boardList.get(getAdapterPosition()).getBoardName()));
        }
    }

    class AddBoardVH extends RecyclerView.ViewHolder {
        CardView cvAddBoard;

        public AddBoardVH(@NonNull View itemView) {
            super(itemView);
            cvAddBoard = itemView.findViewById(R.id.cv_add_board);

            cvAddBoard.setOnClickListener(v -> {
                Timber.d("click");
                GlobalBus.getEventBus().post(Constants.INSTANCE.getADD_NEW_BOARD());
            });
        }
    }

}
