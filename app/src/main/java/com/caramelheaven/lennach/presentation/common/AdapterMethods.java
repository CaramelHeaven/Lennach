package com.caramelheaven.lennach.presentation.common;

import java.util.List;

public interface AdapterMethods<T> {
    void updateAdapter(List<T> items);

    T getItemByPosition(int position);
}
