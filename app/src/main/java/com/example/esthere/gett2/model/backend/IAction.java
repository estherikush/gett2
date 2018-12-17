package com.example.esthere.gett2.model.backend;

public interface IAction<T> {
    void onSuccess(T obj);
    void onFailure(Exception exception);
}
