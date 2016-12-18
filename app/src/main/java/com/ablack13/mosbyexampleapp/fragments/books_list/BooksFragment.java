package com.ablack13.mosbyexampleapp.fragments.books_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ablack13.mosbyexampleapp.R;
import com.ablack13.mosbyexampleapp.adapters.BooksAdapter;
import com.ablack13.mosbyexampleapp.common.BaseFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

/**
 * Created by ablack13 on 17.12.16.
 */

public class BooksFragment extends BaseFragment<View, List<String>, BooksView, BooksPresenter> implements BooksView {
    RecyclerView recyclerView;
    BooksAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.contentView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BooksAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onNewViewStateInstance() {
        super.onNewViewStateInstance();
    }

    @Override
    public BooksPresenter createPresenter() {
        return new BooksPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return "Eoror coc";
    }

    @Override
    public void setData(List<String> data) {
        adapter.setItems(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadData();
    }

    @Override
    public LceViewState<List<String>, BooksView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public List<String> getData() {
        return adapter == null ? null : adapter.getItems();
    }
}
