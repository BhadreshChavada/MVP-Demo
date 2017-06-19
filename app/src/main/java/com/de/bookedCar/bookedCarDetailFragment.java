package com.de.bookedCar;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.de.R;
import com.de.baseClass.baseFragment;
import com.de.bookedCar.model.Trip;
import com.de.bookedCar.model.updateTripModel;
import com.de.data.APIResponse;

import java.util.HashMap;
import java.util.List;

import data.Injection;
import retrofit2.Call;
import retrofit2.Response;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 13/6/17.
 */

public class bookedCarDetailFragment extends baseFragment implements bookedCarDetailContract.View, View.OnClickListener {

    View mView;

    TextView tv_car_Name, tv_car_type;
    EditText edt_guest_name, edt_guest_contact_no, edt_trip_start_date, edt_trip_enddate, edt_driver_name, edt_car_no, edt_status;
    Spinner sp_driver_name;
    Button btn_Assign, btn_edit, btn_cancel_trip, btn_cancel, btn_update, btn_tracking, btn_change_car_driver;
    private Dialog dialog;
    Button dialog_confirm_cancel, dialog_confirm_submit;
    bookedCarDetailContract.Presenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_booked_car_details, null);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter = new bookedCarDetailPresenter(Injection.provideDERepository(getActivity()), this);
        init();
        setValue();

    }

    // TODO: 14/6/17 Initialize the view
    private void init() {

        edt_guest_name = (EditText) mView.findViewById(R.id.edt_guest_name);
        edt_guest_contact_no = (EditText) mView.findViewById(R.id.edt_guest_contact_no);
        edt_trip_start_date = (EditText) mView.findViewById(R.id.edt_trip_start_date);
        edt_trip_enddate = (EditText) mView.findViewById(R.id.edt_trip_enddate);
        edt_driver_name = (EditText) mView.findViewById(R.id.edt_driver_name);
        edt_car_no = (EditText) mView.findViewById(R.id.edt_car_no);
        edt_status = (EditText) mView.findViewById(R.id.edt_status);


        btn_edit = (Button) mView.findViewById(R.id.btn_edit);
        btn_cancel_trip = (Button) mView.findViewById(R.id.btn_cancel_trip);
        btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        btn_update = (Button) mView.findViewById(R.id.btn_update);
        btn_tracking = (Button) mView.findViewById(R.id.btn_tracking);
        btn_change_car_driver = (Button) mView.findViewById(R.id.btn_change_car_driver);

        SetEditableFalse();

        btn_edit.setOnClickListener(this);
        btn_cancel_trip.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_tracking.setOnClickListener(this);
        btn_change_car_driver.setOnClickListener(this);
    }

    // TODO: 14/6/17 Set the EditText NonEditable
    void SetEditableFalse() {
        edt_guest_name.setInputType(InputType.TYPE_NULL);
        edt_guest_contact_no.setInputType(InputType.TYPE_NULL);
        edt_trip_start_date.setInputType(InputType.TYPE_NULL);
        edt_trip_enddate.setInputType(InputType.TYPE_NULL);
        edt_driver_name.setInputType(InputType.TYPE_NULL);
        edt_car_no.setInputType(InputType.TYPE_NULL);
        edt_status.setInputType(InputType.TYPE_NULL);
    }

    // TODO: 14/6/17 set the EditText Editable
    void SetEditable() {
        edt_guest_name.setInputType(InputType.TYPE_CLASS_TEXT);
        edt_guest_contact_no.setInputType(InputType.TYPE_CLASS_PHONE);
//        edt_trip_start_date.setInputType(InputType.TYPE_CLASS_DATETIME);
//        edt_trip_enddate.setInputType(InputType.TYPE_CLASS_DATETIME);
        edt_driver_name.setInputType(InputType.TYPE_CLASS_TEXT);
        edt_car_no.setInputType(InputType.TYPE_CLASS_TEXT);
//        edt_status.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    @Override
    public void setPresenter(bookedCarDetailContract.Presenter presenter) {
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

    // TODO: 14/6/17 get the Data from bookedCarFragment and set Single Value
    @Override
    public void setValue() {

        Bundle bundle = this.getArguments();
        List<Trip> tripList;
        if (bundle != null) {
            tripList = bundle.getParcelableArrayList("TripData");
            int pos = bundle.getInt("Position");


            edt_guest_name.setText(tripList.get(pos).getGuestDetails().getGuestName());
            edt_guest_contact_no.setText(tripList.get(pos).getGuestDetails().getGuestContactNo());
            edt_trip_start_date.setText(tripList.get(pos).getTripStartStartDate());
            edt_trip_enddate.setText(tripList.get(pos).getTripEndDate());
            edt_driver_name.setText(tripList.get(pos).getDriverDetails().getDriverName());
            edt_car_no.setText(tripList.get(pos).getCarDetails().getCarNumber());
            edt_status.setText(String.valueOf(tripList.get(pos).getTripStatus()));

        }
    }

    @Override
    public boolean validateInput() {
        return false;
    }

    // TODO: 14/6/17 Set the Click Listener
    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_edit:
                btn_cancel.setVisibility(View.VISIBLE);
                btn_update.setVisibility(View.VISIBLE);

                btn_edit.setVisibility(View.GONE);
                btn_cancel_trip.setVisibility(View.GONE);
                btn_tracking.setVisibility(View.GONE);
                btn_change_car_driver.setVisibility(View.GONE);
                SetEditable();
                break;
            case R.id.btn_cancel_trip:
                btn_cancel.setVisibility(View.GONE);
                btn_update.setVisibility(View.GONE);

                btn_edit.setVisibility(View.VISIBLE);
                btn_cancel_trip.setVisibility(View.VISIBLE);
                btn_tracking.setVisibility(View.VISIBLE);
                btn_change_car_driver.setVisibility(View.VISIBLE);
                SetEditableFalse();
                CancelDialog();
                break;
            case R.id.btn_cancel:
                btn_cancel.setVisibility(View.GONE);
                btn_update.setVisibility(View.GONE);
                btn_edit.setVisibility(View.VISIBLE);
                btn_cancel_trip.setVisibility(View.VISIBLE);
                btn_tracking.setVisibility(View.VISIBLE);
                btn_change_car_driver.setVisibility(View.VISIBLE);
                SetEditableFalse();


                break;
            case R.id.btn_update:

                updateTrip();

                break;
            case R.id.btn_tracking:
                btn_cancel.setVisibility(View.GONE);
                btn_update.setVisibility(View.GONE);
                btn_edit.setVisibility(View.VISIBLE);
                btn_cancel_trip.setVisibility(View.VISIBLE);
                btn_tracking.setVisibility(View.VISIBLE);
                btn_change_car_driver.setVisibility(View.VISIBLE);

//                Intent intent = new Intent(getActivity(), MapsActivity.class);
//                getActivity().startActivity(intent);
                break;
            case R.id.btn_change_car_driver:
                btn_cancel.setVisibility(View.GONE);
                btn_update.setVisibility(View.GONE);
                btn_edit.setVisibility(View.VISIBLE);
                btn_cancel_trip.setVisibility(View.VISIBLE);
                btn_tracking.setVisibility(View.VISIBLE);
                btn_change_car_driver.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_cancel_confirm:
                dialog.dismiss();
                break;
            case R.id.btn_submit_confirm:
                dialog.dismiss();
                break;
        }
    }

    // TODO: 14/6/17 UpdateTrip details Webservice
    private void updateTrip() {
        HashMap<String,String> map = new HashMap<>();
        if(isNetworkAvailable()){
            setLoadingIndicator(true);
            mPresenter.doUpdateTrip(map, new APIResponse<updateTripModel>() {
                @Override
                public void onSuccess(Call<updateTripModel> call, Response<updateTripModel> response) {

                    btn_cancel.setVisibility(View.GONE);
                    btn_update.setVisibility(View.GONE);
                    btn_edit.setVisibility(View.VISIBLE);
                    btn_cancel_trip.setVisibility(View.VISIBLE);
                    btn_tracking.setVisibility(View.VISIBLE);
                    btn_change_car_driver.setVisibility(View.VISIBLE);

                    SetEditableFalse();
                }

                @Override
                public void onFailure(Call<updateTripModel> call, Throwable t) {

                }
            });
        }
    }

    // TODO: 14/6/17 CancelTrip Dialog Button
    private void CancelDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.popup_confirmation);
        dialog.show();
        dialog_confirm_cancel = (Button) dialog.findViewById(R.id.btn_cancel_confirm);
        dialog_confirm_submit = (Button) dialog.findViewById(R.id.btn_submit_confirm);

        dialog_confirm_cancel.setOnClickListener(this);
        dialog_confirm_submit.setOnClickListener(this);
    }
}
