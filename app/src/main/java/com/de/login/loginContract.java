package com.de.login;

import com.de.baseClass.basePresenter;
import com.de.baseClass.baseView;
import com.de.data.APIResponse;

import java.util.Map;

/**
 * Created by Bhadresh on 9/6/17.
 */

public interface loginContract {

    interface View extends baseView<Presenter> {

        boolean validateInput();

    }

    interface Presenter extends basePresenter {

        void doLogin(Map<String,String> params, APIResponse response);
    }
}
