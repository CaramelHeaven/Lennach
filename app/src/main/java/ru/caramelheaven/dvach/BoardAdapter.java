package ru.caramelheaven.dvach;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.caramelheaven.dvach.data.Board;
import ru.caramelheaven.dvach.data.File;
import ru.caramelheaven.dvach.data.Thread;
import ru.caramelheaven.dvach.fragment_or_activity.ThreadActivity;

/**
 * Created by Sergey F on 24.12.2017.
 */

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    public static final String NUMBER_THREAD = "NUMBER_THREAD";
    private List<Thread> threads;
    Context context;

    public BoardAdapter(Context context, List<Thread> threads) {
        this.context = context;
        this.threads = threads;
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

        /*@Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                switch (v.getId()) {
                    case 0:
                        Intent intent = new Intent(context, ThreadActivity.class);
                        context.startActivity(intent);
                }
            }
        }*/
    }

    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.board_activity, parent, false);
        return new BoardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BoardAdapter.ViewHolder holder, final int position) {
        final Thread thread = threads.get(position);
        holder.subject.setText(Html.fromHtml(thread.getSubject()));
        holder.comment.setText(Html.fromHtml(thread.getComment()));
        holder.views.setText(Html.fromHtml(thread.getDate()));
        holder.image.setVisibility(View.GONE);
        int files = thread.getFiles().size();
        if (files > 0) {
            for (File file : thread.getFiles()) {
                if (file.getPath() != null) {
                    holder.image.setVisibility(View.VISIBLE);
                    String f = file.getPath();
                    Picasso.with(context).load("https://2ch.hk/" + f).into(holder.image);
                }
            }
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fa = thread.getNum();
                Toast.makeText(context, "Get Position" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ThreadActivity.class);
                intent.putExtra(NUMBER_THREAD, fa);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return threads.size();
    }
}