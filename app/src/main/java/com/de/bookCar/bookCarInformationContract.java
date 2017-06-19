package com.de.bookCar;

import android.widget.AutoCompleteTextView;

import com.de.baseClass.basePresenter;
import com.de.baseClass.baseView;
import com.de.data.APIResponse;

import java.util.HashMap;

/**
 * Created by Bhadresh on 15/6/17.
 */

public interface bookCarInformationContract {

    interface View extends baseView<Presenter> {



        void setAutomaticInvoice();

        void setManualInvoice();


        void setGooglePlaceApi(AutoCompleteTextView autoCompleteTextView);
    }

    interface Presenter extends basePresenter {

        void doGetCity(HashMap<String, String> map, APIResponse apiResponse);

        void doGetPaymentData(HashMap<String, String> map, APIResponse apiResponse);

        void doBookCar(HashMap<String, String> map, APIResponse apiResponse);

    }
}
