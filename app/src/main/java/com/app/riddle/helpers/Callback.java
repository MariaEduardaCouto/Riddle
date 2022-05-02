package com.app.riddle.helpers;

public interface Callback {
    void onSuccess(Object responseObject);
    void onError(String error);
}
