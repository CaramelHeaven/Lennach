package com.caramelheaven.lennach.presentation.image_viewer;

public interface ImageViewerCallback {
    void close(boolean flag);

    void passAlphaCounter(float data);
}
