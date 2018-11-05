package com.caramelheaven.lennach.presentation.main.saved_history;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.presentation.common.AdapterMethods;
import com.caramelheaven.lennach.utils.callbacks.myOnItemClickListener;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

public class HistoryFavouriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterMethods<Usenet> {

    private List<Usenet> usenetList;
    private Set<Usenet> usenetUnique;

    private myOnItemClickListener myOnItemClickListener;

    public HistoryFavouriteAdapter(List<Usenet> usenetList) {
        this.usenetList = usenetList;
        usenetUnique = new LinkedHashSet<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_favourite, viewGroup, false);
        return new FavouriteVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FavouriteVH favouriteVH = (FavouriteVH) viewHolder;
        Timber.d("getText: " + usenetList.get(i).toString());
        favouriteVH.tvName.setText(usenetList.get(i).getComment());
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
        return null;
    }

    public class FavouriteVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvCounter, tvName;

        public FavouriteVH(@NonNull View itemView) {
            super(itemView);
            tvCounter = itemView.findViewById(R.id.tv_count_missed_post);
            tvName = itemView.findViewById(R.id.tv_name_thread);
        }

        @Override
        public void onClick(View v) {
            myOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public void setMyOnItemClickListener(com.caramelheaven.lennach.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
}
