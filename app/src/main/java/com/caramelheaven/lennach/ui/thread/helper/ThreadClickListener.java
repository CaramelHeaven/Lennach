package com.caramelheaven.lennach.ui.thread.helper;

import android.view.View;

public interface ThreadClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
