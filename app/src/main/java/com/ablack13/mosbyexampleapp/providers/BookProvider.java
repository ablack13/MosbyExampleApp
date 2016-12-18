package com.ablack13.mosbyexampleapp.providers;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ablack13 on 17.12.16.
 */

public class BookProvider {
    public static Observable<List<String>> loadData() {
        final Disposable[] disposable = new Disposable[1];
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<String>> emitter) throws Exception {
                disposable[0] = Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                        List<String> books = new ArrayList<>();
                        for (int i = 0; i < 100; i++) {
                            books.clear();
                            for (int j = 0; j < 100; j++) {
                                books.add("Book " + j + "(section " + i + ")");
                            }
                            e.onNext(books);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<List<String>>() {
                            @Override
                            public void accept(List<String> books) throws Exception {
                                emitter.onNext(books);
                            }
                        });
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                if (disposable[0] != null && !disposable[0].isDisposed()) {
                    disposable[0].dispose();
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
