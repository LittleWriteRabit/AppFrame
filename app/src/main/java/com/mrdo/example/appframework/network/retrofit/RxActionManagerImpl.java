package com.mrdo.example.appframework.network.retrofit;

import java.util.HashMap;
import java.util.Set;

import io.reactivex.disposables.Disposable;


/**
 * Created by dulijie on 2018/9/11.
 */
public class RxActionManagerImpl implements RxActionManager<Object> {

    private static RxActionManagerImpl sInstance = null;

    private HashMap<Object, Disposable> maps;

    public static RxActionManagerImpl getInstance() {
        if (sInstance == null) {
            synchronized (RxActionManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new RxActionManagerImpl();
                }
            }
        }
        return sInstance;
    }

    private RxActionManagerImpl() {
        maps = new HashMap<>();
    }

    @Override
    public void add(Object tag, Disposable disposable) {
        maps.put(tag, disposable);
    }

    @Override
    public void remove(Object tag) {
        if (!maps.isEmpty()) {
            maps.remove(tag);
        }
    }

    public void removeAll() {
        if (!maps.isEmpty()) {
            maps.clear();
        }
    }

    @Override
    public void cancel(Object tag) {
        if (maps.isEmpty()) {
            return;
        }
        if (maps.get(tag) == null) {
            return;
        }
        if (!maps.get(tag).isDisposed()) {
            maps.get(tag).dispose();
            maps.remove(tag);
        }
    }

    @Override
    public void cancelAll() {
        if (maps.isEmpty()) {
            return;
        }
        Set<Object> keys = maps.keySet();
        for (Object apiKey : keys) {
            cancel(apiKey);
        }
    }
}
