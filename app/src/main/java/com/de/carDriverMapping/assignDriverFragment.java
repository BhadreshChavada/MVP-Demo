package com.de.carDriverMapping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.de.R;
import com.de.baseClass.baseFragment;
import com.de.carDriverMapping.model.CarList;
import com.de.carDriverMapping.model.DriverList;
import com.de.carDriverMapping.model.driverListModel;
import com.de.carDriverMapping.model.updateCarDriverMappingModel;
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

public class assignDriverFragment extends baseFragment implements assignDriverContract.View, View.OnClickListener {

    View mView;

    private assignDriverContract.Presenter mPresenter;
    List<DriverList> driverList;
    Spinner spinnerDriver;
    TextView txtCarno, txtCarType;
    Button btnAssign;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_car_driver_mapping, null);
        return mView;

    }


    @Override
    public void onResume() {
        super.onResume();


        mPresenter = new assignDriverPresenter(Injection.provideDERepository(getActivity()), this);
        init();
        setValue();
        if (isNetworkAvailable()) {
            getDriverList();
        }
    }

    // TODO: 12/6/17 Initialize the view
    private void init() {
        spinnerDriver = (Spinner) mView.findViewById(R.id.sp_driver_name);
        txtCarno = (TextView) mView.findViewById(R.id.tv_car_number);
        txtCarType = (TextView) mView.findViewById(R.id.tv_car_type);
        btnAssign = (Button) mView.findViewById(R.id.btn_assign);
        btnAssign.setOnClickListener(this);
    }

    // TODO: 12/6/17 Get the Unassigned Driver list
    private void getDriverList() {
        setLoadingIndicator(true);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", "");
        map.put("userTenantId", "");
        map.put("offset", "0");


        mPresenter.doGetDriverList(map, new APIResponse<driverListModel>() {
            @Override
            public void onSuccess(Call<driverListModel> call, Response<driverListModel> response) {
                setLoadingIndicator(false);
                driverList = response.body().getDriverList();
                setSpinner();
            }

            @Override
            public void onFailure(Call<driverListModel> call, Throwable t) {
                setLoadingIndicator(false);
            }
        });
    }


    // TODO: 12/6/17 set the UnassignedDriver to Driver Spinner
    @Override
    public void setSpinner() {

        driverSpinnerAdapter adapter = new driverSpinnerAdapter(getActivity(), R.layout.layout_spinner_backgroung, driverList);
        spinnerDriver.setAdapter(adapter);

    }

    // TODO: 12/6/17 Get the value from carDrviewMappingFragment and set the Value
    @Override
    public void setValue() {

        Bundle bundle = this.getArguments();
        List<CarList> carList = null;
        if (bundle != null) {
            carList = bundle.getParcelableArrayList("CarData");
            int pos = bundle.getInt("Position");
            txtCarno.setText(carList.get(pos).getCarNumber());
            txtCarType.setText(carList.get(pos).getCarType().getType());
        }


    }

    @Override
    public void setPresenter(assignDriverContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);

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

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_assign) {
            if (isNetworkAvailable()) {
                AssignCartoDriver();
            }
        }

    }

    // TODO: 12/6/17 Assign the Driver to specific Car
    private void AssignCartoDriver() {

        setLoadingIndicator(true);
        HashMap<String, String> map = new HashMap<String, String>();


        mPresenter.doAssignCartoDriver(map, new APIResponse<updateCarDriverMappingModel>() {
            @Override
            public void onSuccess(Call<updateCarDriverMappingModel> call, Response<updateCarDriverMappingModel> response) {

                if (response.code() == 200) {
                    setLoadingIndicator(false);
                    displayToast(response.body().getMessage());


                    callFragment(new carDriverMappingFragment(), R.id.content_frame, "");
                }
            }

            @Override
            public void onFailure(Call<updateCarDriverMappingModel> call, Throwable t) {

                setLoadingIndicator(false);
            }
        });
    }
}
