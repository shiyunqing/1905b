package com.example.myapplication.interfaces;


public interface IBasePersenter<V extends IBaseView> {
    void attachView(V view);
    void detachView();

}
