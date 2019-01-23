package com.caramelheaven.lennach.presentation.board_choose.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board.BoardFavourite;
import com.caramelheaven.lennach.utils.OnCheckItemListener;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

/**
 * Created by CaramelHeaven on 21:52, 20/01/2019.
 */
public class BoardChooseDelegate extends AdapterDelegate<List<BoardFavourite>> {

    private LayoutInflater inflater;
    private OnCheckItemListener onCheckItemListener;

    public BoardChooseDelegate(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    protected boolean isForViewType(@NonNull List<BoardFavourite> items, int position) {
        return items.get(position) != null;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new BoardAllVH(inflater.inflate(R.layout.item_board_choose, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<BoardFavourite> items, int position, @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {
        BoardAllVH boardAllVH = (BoardAllVH) holder;

        boardAllVH.tvTitle.setText("/" + items.get(position).getId() + "/ - " +
                items.get(position).getName());
        boardAllVH.tvDescription.setText("Category: " + items.get(position).getCategory());

        boardAllVH.checkBox.setChecked(items.get(position).isSelected());
    }

    class BoardAllVH extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription;
        private CheckBox checkBox;

        public BoardAllVH(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) ->
                    onCheckItemListener.isChecked(getAdapterPosition(), isChecked));
        }
    }

    public void setOnCheckItemListener(OnCheckItemListener onCheckItemListener) {
        this.onCheckItemListener = onCheckItemListener;
    }
}
