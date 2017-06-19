package com.de.baseClass;

/**
 * Created by emxcel on 1/6/17.
 */

public interface baseView<T> {
    void setPresenter(T presenter);

    void setLoadingIndicator(boolean active);

    boolean isNetworkAvailable();

    void showToast(String message);
}
