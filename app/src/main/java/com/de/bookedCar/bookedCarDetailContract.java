package com.de.bookedCar;

import com.de.baseClass.basePresenter;
import com.de.baseClass.baseView;
import com.de.data.APIResponse;

import java.util.HashMap;

/**
 * Created by Bhadresh on 13/6/17.
 */

public interface bookedCarDetailContract {

    interface View extends baseView<bookedCarDetailContract.Presenter> {

        void setValue();

        boolean validateInput();

    }

    interface Presenter extends basePresenter {

        void doUpdateTrip(HashMap<String, String> map, APIResponse response);

    }


}
