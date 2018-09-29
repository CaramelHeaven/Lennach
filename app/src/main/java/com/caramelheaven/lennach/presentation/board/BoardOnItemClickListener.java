package com.caramelheaven.lennach.presentation.board;

import android.widget.ImageView;

public interface BoardOnItemClickListener {
    void onImageClick(int position, ImageView image);

    void onUsenetClick(int position);
}
