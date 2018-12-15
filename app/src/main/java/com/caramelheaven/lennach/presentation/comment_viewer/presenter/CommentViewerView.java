package com.caramelheaven.lennach.presentation.comment_viewer.presenter;

import com.caramelheaven.lennach.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 16:53, 15/12/2018.
 */
public interface CommentViewerView<T> extends BaseView {
    void onShowComments(List<T> posts);
}
