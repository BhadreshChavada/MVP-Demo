package com.de.bookedCar;

import com.de.baseClass.basePresenter;
import com.de.baseClass.baseView;
import com.de.bookedCar.model.Trip;
import com.de.data.APIResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Bhadresh on 12/6/17.
 */

public interface bookedCarListContract {

    interface View extends baseView<Presenter> {


        void setRecycleview(List<Trip> responses);


    }

    interface Presenter extends basePresenter {

        void doGetBookedCarList(HashMap<String, String> map, APIResponse response);

    }
}
