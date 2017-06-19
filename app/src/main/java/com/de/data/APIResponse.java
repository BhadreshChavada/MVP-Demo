package com.de.data;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by emxcel on 1/6/17.
 */

public interface APIResponse<T> {

    void onSuccess(Call<T> call, Response<T> response);

    void onFailure(Call<T> call, Throwable t);
}
