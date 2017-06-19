package com.de.bookCar;

import com.de.data.DERepository;

/**
 * Created by Bhadresh on 14/6/17.
 */

public class bookCarListPresenter implements bookCarListContracat.Presenter {

    private final DERepository mRepository;

    private final bookCarListContracat.view mView;

    public bookCarListPresenter(DERepository mRepository, bookCarListContracat.view mView) {
        this.mRepository = mRepository;
        this.mView = mView;
        mView.setPresenter(this);
    }
}
