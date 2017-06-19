package com.de.bookCar;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.de.R;
import com.de.baseClass.baseFragment;
import com.de.bookCar.model.CarList;
import com.de.bookCar.model.CarTypeList;
import com.de.bookCar.model.DriverList;
import com.de.bookCar.model.searchCarModel;
import com.de.bookCar.model.searchCarTypeModel;
import com.de.data.APIResponse;
import com.de.utils.constantString;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import data.Injection;
import retrofit2.Call;
import retrofit2.Response;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 13/6/17.
 */

public class bookCarSearchFragment extends baseFragment implements bookCarSearchContract.View, View.OnClickListener {


    View mView;
    bookCarSearchContract.Presenter mPresenter;
    Spinner spinnerCarType;
    TextView textFromDate, textToData;
    Button btnSearch;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_bookcar_search, null);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter = new bookCarSearchPresenter(Injection.provideDERepository(getActivity()), this);
        init();
    }

    // TODO: 14/6/17 Initialize the View
    private void init() {

        spinnerCarType = (Spinner) mView.findViewById(R.id.spinner_car_type);
        textFromDate = (TextView) mView.findViewById(R.id.txt_bookcar_from_date);
        textToData = (TextView) mView.findViewById(R.id.txt_bookcar_to_date);
        btnSearch = (Button) mView.findViewById(R.id.btn_bookcar_search);

        textFromDate.setOnClickListener(this);
        textToData.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        if (isNetworkAvailable()) {
            getCarType();
        }

    }

    // TODO: 14/6/17 Get the CarType
    private void getCarType() {
        setLoadingIndicator(true);
        HashMap<String, String> map = new HashMap<>();
        mPresenter.doGetCarType(map, new APIResponse<searchCarTypeModel>() {
            @Override
            public void onSuccess(Call<searchCarTypeModel> call, Response<searchCarTypeModel> response) {
                setLoadingIndicator(false);

                if (response.code() == 200) {
                    setSpinner(response.body().getCarTypeList());

                }
            }

            @Override
            public void onFailure(Call<searchCarTypeModel> call, Throwable t) {
                setLoadingIndicator(false);
            }
        });

    }

    @Override
    public void setPresenter(bookCarSearchContract.Presenter presenter) {

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
        displayToast(message);
    }

    @Override
    public void displayCalender(TextView textView) {

        chooseDate(textView, this);
    }

    // TODO: 14/6/17 set carType Spinner
    @Override
    public void setSpinner(List<CarTypeList> carTypeList) {
        carTypeSpinnerAdapter adapter = new carTypeSpinnerAdapter(getActivity(), R.layout.layout_spinner_backgroung, carTypeList);
        spinnerCarType.setAdapter(adapter);
    }

    // TODO: 14/6/17 check date validation
    @Override
    public boolean validateInput() {

        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            Date mFromDate = dateFormatter.parse(textFromDate.getText().toString().replace("/", "-"));
            Date mToDate = dateFormatter.parse(textToData.getText().toString().replace("/", "-"));

            if (mFromDate.compareTo(mToDate) <= 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == textFromDate.getId()) {
            displayCalender(textFromDate);
        } else if (v.getId() == textToData.getId()) {
            displayCalender(textToData);
        } else if (v.getId() == btnSearch.getId()) {
            if (isNetworkAvailable()) {
                if (validateInput()) {
                    SearchCar();
                } else {
                    showToast(constantString.dateValidation);
                }
            }
        }
    }

    // TODO: 14/6/17 get the available Car
    private void SearchCar() {
        HashMap<String, String> map = new HashMap<>();
        mPresenter.doGetSearch(map, new APIResponse<searchCarModel>() {
            @Override
            public void onSuccess(Call<searchCarModel> call, Response<searchCarModel> response) {

                if (response.code() == 200) {
                    if (response.body().getCarList().size() > 0) {

                        List<CarList> carList = response.body().getCarList();
                        List<DriverList> driverList = response.body().getDriverList();
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("carList", (ArrayList<? extends Parcelable>) carList);
                        bundle.putParcelableArrayList("driverList", (ArrayList<? extends Parcelable>) driverList);
                        bundle.putString("fromdate", textFromDate.getText().toString());
                        bundle.putString("todate", textToData.getText().toString());
                        callFragment(new bookCarListFragment(), R.id.content_frame, bundle);

                    }
                }
            }

            @Override
            public void onFailure(Call<searchCarModel> call, Throwable t) {

            }
        });
    }

    // TODO: 14/6/17 Get the selected date and set it
    @Override
    public void onDateSelect(String date, TextView textView) {
        textView.setText(date);
    }


}
