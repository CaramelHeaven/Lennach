/*
package ru.caramelheaven.lennach;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.caramelheaven.lennach.adapters.BoardAdapter;
import ru.caramelheaven.lennach.data.Thread;

*/
/**
 * Created by Sergey F on 02.01.2018.
 *//*

ИСТИННЫЙ БОАРД БЕЗ БАЗЫ ДАННЫх
public class FF {
    public class BoardAdapter extends RecyclerView.Adapter<ru.caramelheaven.lennach.adapters.BoardAdapter.ViewHolder> {

        private List<Thread> threads;

        public BoardAdapter(List<Thread> threads) {
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
        public ru.caramelheaven.lennach.adapters.BoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_elements, parent, false);
            return new ru.caramelheaven.lennach.adapters.BoardAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ru.caramelheaven.lennach.adapters.BoardAdapter.ViewHolder holder, int position) {
            final Thread thread = threads.get(position);
            holder.subject.setText(Html.fromHtml(thread.getName()));
            holder.comment.setText(Html.fromHtml(thread.getComment()));
            holder.views.setText(Html.fromHtml(thread.getDate()));

        }

        @Override
        public int getItemCount() {
            return threads.size();
        }

*/
