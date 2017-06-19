package com.de.carDriverMapping;

import com.de.baseClass.basePresenter;
import com.de.baseClass.baseView;
import com.de.data.APIResponse;

import java.util.HashMap;

/**
 * Created by Bhadresh on 12/6/17.
 */

public interface assignDriverContract {


    interface View extends baseView<Presenter> {




        void setSpinner();

        void setValue();


    }


    public interface Presenter extends basePresenter {


        void doGetDriverList(HashMap<String, String> map, APIResponse Responses);

        void doAssignCartoDriver(HashMap<String, String> map, APIResponse Response);
    }
}
