package com.de.navigationMenu;

import android.support.v4.app.Fragment;

/**
 * Created by Bhadresh on 10/6/17.
 */

public interface navigationContract {

    interface View {

        void showToast(String message);

        void navigationItemClick(Fragment secondFragment);


    }
}
