package com.ablack13.mosbyexampleapp.fragments.books_list;

import android.util.Log;

import com.ablack13.mosbyexampleapp.providers.BookProvider;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by ablack13 on 17.12.16.
 */

public class BooksPresenterImpl extends BooksPresenter {
    private Disposable loadBooksDisposable;

    public BooksPresenterImpl() {
        Log.d("BooksPresenterImpl", "constructor");
    }

    @Override
    void loadData() {
        Log.d("BooksPresenterImpl", "loadData()");
        if (isViewAttached()) {
            getView().showLoading(true);
        }
        loadBooksDisposable =
                BookProvider.loadData()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<String>>() {
                            @Override
                            public void accept(List<String> books) throws Exception {
                                if (isViewAttached()) {
                                    getView().showContent();
                                    getView().setData(books);
                                }
                            }
                        });
    }

    @Override
    public void attachView(BooksView view) {
        super.attachView(view);
        Log.d("BooksPresenterImpl", "attachView()");
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            if (loadBooksDisposable != null && !loadBooksDisposable.isDisposed()) {
                loadBooksDisposable.dispose();
            }
        }
        Log.d("BooksPresenterImpl", "retainInstance::" + retainInstance);
        Log.d("BooksPresenterImpl", "detachView()");
    }
}
