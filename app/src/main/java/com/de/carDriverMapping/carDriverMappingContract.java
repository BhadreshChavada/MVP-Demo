package com.de.carDriverMapping;

import com.de.baseClass.basePresenter;
import com.de.baseClass.baseView;
import com.de.data.APIResponse;

import java.util.HashMap;

/**
 * Created by Bhadresh on 10/6/17.
 */

public interface carDriverMappingContract {


    interface View extends baseView<Presenter> {

        void SetRecycleView();

    }


    public interface Presenter extends basePresenter {

        void doGetCarList(HashMap<String, String> map, APIResponse Responses);


    }
}
