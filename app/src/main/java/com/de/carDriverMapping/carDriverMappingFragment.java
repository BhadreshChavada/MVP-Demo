package com.de.carDriverMapping;

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
import com.de.carDriverMapping.model.CarList;
import com.de.carDriverMapping.model.carDriverMappingModel;
import com.de.data.APIResponse;

import java.util.HashMap;
import java.util.List;

import data.Injection;
import retrofit2.Call;
import retrofit2.Response;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 9/6/17.
 */

public class carDriverMappingFragment extends baseFragment implements carDriverMappingContract.View {

    View mView;
    RecyclerView mCarList;
    private carDriverMappingContract.Presenter mCarDriverMaappingPresenter;
    List<CarList> carList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.layout_recycleview, null);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCarDriverMaappingPresenter = new carDriverMappingPresenter(Injection.provideDERepository(getActivity()), this);
        init();

    }


    void init() {

        mCarList = (RecyclerView) mView.findViewById(R.id.layout_recycle_view);

        if (isNetworkAvailable()) {
            getCarList();
        }


    }

    // TODO: 09/6/17 Get the List of Car by Tanent Id
    void getCarList() {
        setLoadingIndicator(true);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", "");
        map.put("userTenantId", "");
        map.put("offset", "");

        mCarDriverMaappingPresenter.doGetCarList(map, new APIResponse<carDriverMappingModel>() {
            @Override
            public void onSuccess(Call<carDriverMappingModel> call, Response<carDriverMappingModel> response) {
                setLoadingIndicator(false);
                if (response.code() == 200) {


                    carList = response.body().getCarList();
                    SetRecycleView();

                }

            }

            @Override
            public void onFailure(Call<carDriverMappingModel> call, Throwable t) {

                setLoadingIndicator(false);
            }
        });

    }

    @Override
    public void setPresenter(carDriverMappingContract.Presenter presenter) {
        mCarDriverMaappingPresenter = checkNotNull(presenter);


    }

    @Override
    public void setLoadingIndicator(boolean active) {
        displayProgressDialog(active);
    }

    @Override
    public boolean isNetworkAvailable() {
        return isNetworkAvailable(getActivity());
    }

    @Override
    public void showToast(String message) {

    }



    // TODO: 09/6/17 set the List in RecycleView
    @Override
    public void SetRecycleView() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mCarList.setLayoutManager(mLayoutManager);
        mCarList.setItemAnimator(new DefaultItemAnimator());
        mCarList.setAdapter(new carListAdapter(carList, getActivity().getSupportFragmentManager()));
    }


}
