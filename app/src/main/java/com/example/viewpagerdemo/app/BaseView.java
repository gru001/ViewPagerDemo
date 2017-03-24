package com.example.viewpagerdemo.app;

/**
 * Base View for all view
 * Note: here view is part of MVP
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
    void setLoadingIndicator(boolean active);
    void showError();
}
