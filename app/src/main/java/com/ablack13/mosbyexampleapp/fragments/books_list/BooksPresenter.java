package com.ablack13.mosbyexampleapp.fragments.books_list;

import com.ablack13.mosbyexampleapp.common.BasePresenter;

/**
 * Created by ablack13 on 17.12.16.
 */

public abstract class BooksPresenter extends BasePresenter<BooksView> {
    abstract void loadData();
}
