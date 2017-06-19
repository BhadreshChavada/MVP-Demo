package com.de.bookCar;

import android.widget.TextView;

import com.de.baseClass.baseDate;
import com.de.baseClass.basePresenter;
import com.de.baseClass.baseView;
import com.de.bookCar.model.CarTypeList;
import com.de.data.APIResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Bhadresh on 13/6/17.
 */

public interface bookCarSearchContract {

    interface View extends baseView<Presenter>, baseDate {

        void displayCalender(TextView textView);

        void setSpinner(List<CarTypeList> carTypeList);

        boolean validateInput();
    }

    interface Presenter extends basePresenter {

        void doGetCarType(HashMap<String, String> map, APIResponse apiResponses);

        void doGetSearch(HashMap<String, String> map, APIResponse apiResponse);

    }
}
