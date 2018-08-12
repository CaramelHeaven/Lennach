package com.caramelheaven.lennach.ui.base;

import java.util.List;

public interface AdapterMethods<T> {
    void updateAdapter(List<T> models);

    T getItemByPosition(int position);

    List<T> getItems();
}
