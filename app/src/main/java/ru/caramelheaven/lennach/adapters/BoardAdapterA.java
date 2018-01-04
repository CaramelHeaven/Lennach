package ru.caramelheaven.lennach.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import ru.caramelheaven.lennach.R;
import ru.caramelheaven.lennach.data.Board;
import ru.caramelheaven.lennach.database.ThreadViewDB;

/**
 * Created by Sergey F on 02.01.2018.
 */

public class BoardAdapterA extends RealmRecyclerViewAdapter<ThreadViewDB, BoardAdapterA.ViewHolder> {

    private static String TAG = "MY LOGS";

    public BoardAdapterA(OrderedRealmCollection<ThreadViewDB> threads) {
        super(threads, true);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject;
        TextView comment;
        TextView views;
        ImageView image;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subject);
            comment = itemView.findViewById(R.id.comment);
            views = itemView.findViewById(R.id.views);
            image = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.opPost);
        }
    }

    @Override
    public BoardAdapterA.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.board_elements, parent, false);
        Log.d(TAG, "onCreateViewHolder: " + view + viewType);
        return new BoardAdapterA.ViewHolder(view);
    }

    private List<Board> boards;

    @Override
    public void onBindViewHolder(BoardAdapterA.ViewHolder holder, final int position) {
        final ThreadViewDB thread = getData().get(position);//The getData() was previously missing
        //holder.data = thread;
        //other
        holder.subject.setText(Html.fromHtml(thread.getSubject()));
        holder.comment.setText(Html.fromHtml(thread.getComment()));
        holder.views.setText(Html.fromHtml(thread.getDate()));
        holder.image.setVisibility(View.GONE);
    }
}
