package com.de.bookCar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.de.R;
import com.de.baseClass.baseFragment;
import com.de.baseClass.baseGooglePlacesAutocompleteAdapter;
import com.de.bookCar.model.CarList;
import com.de.bookCar.model.DriverList;
import com.de.bookCar.model.cityListModel;
import com.de.bookCar.model.paymentDataModel;
import com.de.data.APIResponse;

import java.util.ArrayList;
import java.util.HashMap;

import data.Injection;
import retrofit2.Call;
import retrofit2.Response;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 15/6/17.
 */

public class bookCarInformation extends baseFragment implements bookCarInformationContract.View, View.OnClickListener {


    View mView;
    private View ViewHeader, viewAutoInvoie;
    Button btn_autoInvoice, btn_manualInvoice;
    LinearLayout fragment_bookcar_linear;

    TextView  txtCarno, txtCarType, txtCarName, txtDriverName, txtPickupDateTime, txtDropDateTime;
    AutoCompleteTextView edtPickupLocation, edtDropLocation;
    Spinner spinnerAutomaticCity, spinnerAutomaticPayment, spinnerAutomaticInvoiceCategory, spinnerAutomaticRateContract;
    CheckBox checkBoxDebit;

    bookCarInformationContract.Presenter mPresenter;
    private String fromDate, toDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_bookcar_information, null);

        ViewHeader = inflater.inflate(R.layout.layout_bookcar_invoice_tab, null);
        viewAutoInvoie = inflater.inflate(R.layout.fragment_bookcarinformation_automatic_invoice, null);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getArguments();
        ArrayList<CarList> carArray = bundle.getParcelableArrayList("carList");
        ArrayList<DriverList> driverArray = bundle.getParcelableArrayList("driverList");
        int carPosition = bundle.getInt("carPosition", 0);
        int driverPosition = bundle.getInt("driverPosition", 0);
        fromDate = bundle.getString("fromDate");
        toDate = bundle.getString("toDate");


        mPresenter = new bookCarInformationPresenter(Injection.provideDERepository(getActivity()), this);
        fragment_bookcar_linear = (LinearLayout) mView.findViewById(R.id.fragment_bookcar_linear);

        fragment_bookcar_linear.addView(ViewHeader, 0);
        fragment_bookcar_linear.addView(viewAutoInvoie, 1);
        btn_autoInvoice = (Button) ViewHeader.findViewById(R.id.invoice_tab_automatic);
        btn_manualInvoice = (Button) ViewHeader.findViewById(R.id.invoice_tab_manual);

        txtCarName = (TextView) viewAutoInvoie.findViewById(R.id.tv_car_name);
        txtCarType = (TextView) viewAutoInvoie.findViewById(R.id.tv_car_type);
        txtCarno = (TextView) viewAutoInvoie.findViewById(R.id.tv_car_number);
        txtCarno = (TextView) viewAutoInvoie.findViewById(R.id.tv_car_number);
        txtDriverName = (TextView) viewAutoInvoie.findViewById(R.id.tv_driver_name);
        txtPickupDateTime = (TextView) viewAutoInvoie.findViewById(R.id.txt_pickupdate_time);
        txtDropDateTime = (TextView) viewAutoInvoie.findViewById(R.id.txt_dropdate_time);

        edtPickupLocation = (AutoCompleteTextView) viewAutoInvoie.findViewById(R.id.et_pick_location);
        edtDropLocation = (AutoCompleteTextView) viewAutoInvoie.findViewById(R.id.et_drop_location);

        spinnerAutomaticCity = (Spinner) viewAutoInvoie.findViewById(R.id.sp_city);
        spinnerAutomaticPayment = (Spinner) viewAutoInvoie.findViewById(R.id.sp_payment);
        spinnerAutomaticInvoiceCategory = (Spinner) viewAutoInvoie.findViewById(R.id.sp_invoice_category);
        spinnerAutomaticRateContract = (Spinner) viewAutoInvoie.findViewById(R.id.sp_select_rate_contract);

        checkBoxDebit = (CheckBox) viewAutoInvoie.findViewById(R.id.checkbox_debits);

        btn_autoInvoice.setOnClickListener(this);
        btn_manualInvoice.setOnClickListener(this);
        setGooglePlaceApi(edtPickupLocation);
        setGooglePlaceApi(edtDropLocation);

        txtCarno.setText(carArray.get(carPosition).getCarNumber());
        txtCarType.setText(carArray.get(carPosition).getCarType());
        txtCarName.setText(carArray.get(carPosition).getCarName());
        txtDriverName.setText(driverArray.get(driverPosition).getDriverName());
        txtPickupDateTime.setText(fromDate);
        txtDropDateTime.setText(toDate);

//        setAutomaticInvoice();

        if (isNetworkAvailable()) {
            getCity();
            getPaymentSpinnerData();
        }
    }

    // TODO: 16/6/17 Get All Payment Spinner data and set Adapter
    private void getPaymentSpinnerData() {
        HashMap<String, String> map = new HashMap<String, String>();
        mPresenter.doGetPaymentData(map, new APIResponse<paymentDataModel>() {
            @Override
            public void onSuccess(Call<paymentDataModel> call, Response<paymentDataModel> response) {

                if (response.code() == 200) {

                    spinnerAutomaticRateContract.setAdapter(new rateOfContractSpinnerAdapter(getActivity(), R.layout.layout_spinner_backgroung, response.body().getRateOfContractList()));
                    spinnerAutomaticInvoiceCategory.setAdapter(new invoiceCategorySpinnerAdapter(getActivity(), R.layout.layout_spinner_backgroung, response.body().getInvoiceCategoryList()));
                    spinnerAutomaticPayment.setAdapter(new paymentTypeSpinnerAdapter(getActivity(), R.layout.layout_spinner_backgroung, response.body().getPaymentModeList()));


                }
            }

            @Override
            public void onFailure(Call<paymentDataModel> call, Throwable t) {

            }
        });
    }


    // TODO: 16/6/17 Get the city and set the spinner
    private void getCity() {
        HashMap<String, String> map = new HashMap<String, String>();

        mPresenter.doGetCity(map, new APIResponse<cityListModel>() {
            @Override
            public void onSuccess(Call<cityListModel> call, Response<cityListModel> response) {

                if (response.code() == 200) {
                    spinnerAutomaticCity.setAdapter(new citySpinnerAdapter(getActivity(), R.layout.layout_spinner_backgroung, response.body().getCityList()));

                }
            }

            @Override
            public void onFailure(Call<cityListModel> call, Throwable t) {

            }
        });

    }


    // TODO: 16/6/17 set Presenter
    @Override
    public void setPresenter(bookCarInformationContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    // TODO: 16/6/17 set progress Dialog
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
    public void setAutomaticInvoice() {

        btn_autoInvoice.setBackgroundResource(R.drawable.left_selected_tab);
        btn_autoInvoice.setTextColor(getResources().getColor(R.color.button_singup));
        btn_manualInvoice.setBackgroundResource(R.drawable.right_unselected_tab);
        btn_manualInvoice.setTextColor(getResources().getColor(R.color.white));

        spinnerAutomaticRateContract.setVisibility(View.VISIBLE);
        checkBoxDebit.setVisibility(View.GONE);


    }

    @Override
    public void setManualInvoice() {

        btn_manualInvoice.setBackgroundResource(R.drawable.right_selected_tab);
        btn_manualInvoice.setTextColor(getResources().getColor(R.color.button_singup));
        btn_autoInvoice.setBackgroundResource(R.drawable.left_unselected_tab);
        btn_autoInvoice.setTextColor(getResources().getColor(R.color.white));

        spinnerAutomaticRateContract.setVisibility(View.GONE);
        checkBoxDebit.setVisibility(View.VISIBLE);


    }


    @Override
    public void setGooglePlaceApi(AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setAdapter(new baseGooglePlacesAutocompleteAdapter(getContext(), R.layout.layout_google_place));
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btn_autoInvoice.getId()) {
            setAutomaticInvoice();
        } else if (v.getId() == btn_manualInvoice.getId()) {
            setManualInvoice();
        }
    }


}
