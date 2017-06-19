package com.de.bookCar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.de.R;
import com.de.baseClass.baseFragment;
import com.de.bookCar.model.CarList;
import com.de.bookCar.model.DriverList;

import java.util.List;

import data.Injection;

/**
 * Created by Bhadresh on 14/6/17.
 */

public class bookCarListFragment extends baseFragment implements bookCarListContracat.view {

    bookCarListContracat.Presenter mPresenter;
    View mView;
    List<CarList> carLists;
    List<DriverList> DriverList;
    RecyclerView availableCarList;
    LinearLayout mainLinearLayout;
    String fromDate,toDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.layout_recycleview, null);

        return mView;

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter = new bookCarListPresenter(Injection.provideDERepository(getActivity()), this);


        View view = View.inflate(getActivity(), R.layout.layout_bookcar_header, null);
        mainLinearLayout = (LinearLayout)mView.findViewById(R.id.layout_recycle_view_linear_layout);
        mainLinearLayout.addView(view,0);

        Bundle bundle = getArguments();
        carLists = bundle.getParcelableArrayList("carList");
        DriverList = bundle.getParcelableArrayList("driverList");
        fromDate = bundle.getString("fromdate");
        toDate = bundle.getString("todate");
        availableCarList = (RecyclerView) mView.findViewById(R.id.layout_recycle_view);


        setRecycleView();
    }

    @Override
    public void setPresenter(bookCarListContracat.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public boolean isNetworkAvailable() {
        return false;
    }

    @Override
    public void showToast(String message) {

    }

    // TODO: 15/6/17 Set the recycleView
    @Override
    public void setRecycleView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        availableCarList.setLayoutManager(mLayoutManager);
        availableCarList.setItemAnimator(new DefaultItemAnimator());
        availableCarList.setAdapter(new bookCarListAdapter(carLists, DriverList, getActivity().getSupportFragmentManager(), getActivity(),fromDate,toDate));

    }


}
