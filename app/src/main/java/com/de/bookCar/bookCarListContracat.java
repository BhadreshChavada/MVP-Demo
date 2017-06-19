package com.de.bookCar;

import com.de.baseClass.basePresenter;
import com.de.baseClass.baseView;

/**
 * Created by Bhadresh on 14/6/17.
 */

public interface bookCarListContracat {

    interface view extends baseView<Presenter> {

        void setRecycleView();


    }

    interface Presenter extends basePresenter {

    }
}
