package com.example.myapplication.base;

import com.example.myapplication.interfaces.IBasePersenter;
import com.example.myapplication.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePersenter<V extends IBaseView> implements IBasePersenter<V> {

    protected V mView;
    WeakReference<V> weakReference;
    CompositeDisposable compositeDisposable;

    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        mView = weakReference.get();
    }

    public void addSubscribe(Disposable disposable){
        if(compositeDisposable == null) compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

    public void unSubscribe(){
        if(compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
