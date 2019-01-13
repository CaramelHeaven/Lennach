package com.caramelheaven.lennach.presentation.navigation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.caramelheaven.lennach.models.model.common.Delegatable;
import com.caramelheaven.lennach.presentation.navigation.vh_delegates.BoardContainerAdapterDelegate;
import com.caramelheaven.lennach.presentation.navigation.vh_delegates.UsenetAdapterDelegate;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:54, 13/01/2019.
 */
public class NavigationAdapter extends RecyclerView.Adapter {

    private AdapterDelegatesManager<List<Delegatable>> delegatesManager;
    private List<Delegatable> items;

    public NavigationAdapter(LayoutInflater inflater, List<Delegatable> items) {
        this.items = items;

        delegatesManager = new AdapterDelegatesManager<>();

        delegatesManager
                .addDelegate(new BoardContainerAdapterDelegate(inflater))
                .addDelegate(new UsenetAdapterDelegate(inflater));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return delegatesManager.onCreateViewHolder(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        delegatesManager.onBindViewHolder(items, i, viewHolder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<Delegatable> newItems) {
        this.items = newItems;

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items, position);
    }

//    private static final DiffUtil.ItemCallback<Delegatable> DIFF_CALLBACK = new DiffUtil.ItemCallback<Delegatable>() {
//        @Override
//        public boolean areItemsTheSame(@NonNull Delegatable oldItems, @NonNull Delegatable newItems) {
//            if (oldItems instanceof Board && newItems instanceof Board) {
//                return ((Board) oldItems).getBoardName().equals(((Board) newItems).getBoardName());
//            } else if (oldItems instanceof Usenet && newItems instanceof Usenet) {
//                return ((Usenet) oldItems).getNum().equals(((Usenet) newItems).getNum());
//            }
//            return false;
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull Delegatable oldItems, @NonNull Delegatable newItems) {
//            return oldItems.equals(newItems);
//        }
//    };


}
