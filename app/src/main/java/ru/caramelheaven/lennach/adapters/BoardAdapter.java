package ru.caramelheaven.lennach.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import ru.caramelheaven.lennach.R;
import ru.caramelheaven.lennach.data.Thread;
import ru.caramelheaven.lennach.database.ThreadViewDB;

/**
 * Created by Sergey F on 02.01.2018.
 */

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private RealmList<ThreadViewDB> threads;

    public BoardAdapter(RealmList<ThreadViewDB> threads) {
        this.threads = threads;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject;
        TextView comment;
        TextView views;

        public ViewHolder(View itemView) {
            super(itemView);
            views = itemView.findViewById(R.id.views);
            subject = itemView.findViewById(R.id.subject);
            comment = itemView.findViewById(R.id.comment);
        }
    }

    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_elements, parent, false);
        return new BoardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ThreadViewDB thread = threads.get(position);
        holder.subject.setText(Html.fromHtml(thread.getSubject()));
        holder.comment.setText(Html.fromHtml(thread.getComment()));
        holder.views.setText(Html.fromHtml(thread.getDate()));

    }

    @Override
    public int getItemCount() {
        return threads.size();
    }

    public void changeDataSet(RealmList<ThreadViewDB> threadViewDBS) {
        threads.clear();
        threads.addAll(threadViewDBS);
        notifyDataSetChanged();;
    }
}
