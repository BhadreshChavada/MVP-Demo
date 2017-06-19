package com.de.bookedCar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.de.R;
import com.de.baseClass.baseFragment;
import com.de.bookedCar.model.Trip;
import com.de.bookedCar.model.bookedCarModel;
import com.de.data.APIResponse;

import java.util.HashMap;
import java.util.List;

import data.Injection;
import retrofit2.Call;
import retrofit2.Response;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 10/6/17.
 */

public class bookedCarFragment extends baseFragment implements bookedCarListContract.View {

    View mView;
    RecyclerView mCarList;
    bookedCarListContract.Presenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.layout_recycleview, null);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter = new bookedCarPresenter(Injection.provideDERepository(getActivity()), this);

        init();
        if (isNetworkAvailable()) {
            setLoadingIndicator(true);
            getBookedCar();

        }
    }

    // TODO: 12/6/17 Get Booked CarList
    private void getBookedCar() {
        HashMap<String, String> map = new HashMap<>();

        mPresenter.doGetBookedCarList(map, new APIResponse<bookedCarModel>() {
            @Override
            public void onSuccess(Call<bookedCarModel> call, Response<bookedCarModel> response) {

                if (response.code() == 200) {

                    setLoadingIndicator(false);
                    setRecycleview(response.body().getTrips());
                }

            }

            @Override
            public void onFailure(Call<bookedCarModel> call, Throwable t) {

                setLoadingIndicator(false);
            }
        });
    }


    // TODO: 12/6/17 initialize the view
    private void init() {

        mCarList = (RecyclerView) mView.findViewById(R.id.layout_recycle_view);

    }

    // TODO: 12/6/17 Set Presenter
    @Override
    public void setPresenter(bookedCarListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);

    }

    // TODO: 12/6/17 Set the Progress Dialog
    @Override
    public void setLoadingIndicator(boolean active) {

        displayProgressDialog(active);
    }

    // TODO: 12/6/17 Check the internet
    @Override
    public boolean isNetworkAvailable() {
        return isNetworkAvailable(getActivity());
    }


    @Override
    public void showToast(String message) {

    }

    // TODO: 12/6/17 Set RecycleView
    @Override
    public void setRecycleview(List<Trip> tripList) {

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mCarList.setLayoutManager(mLayoutManager);
        mCarList.setItemAnimator(new DefaultItemAnimator());
        mCarList.setAdapter(new bookedCarListAdapter(tripList, getActivity().getSupportFragmentManager()));
    }
}
