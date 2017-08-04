package com.curbside.automation.common.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hitesh.grover on 04/08/17.
 */
public interface SmsApiInterface {

    @GET("v1/")
    Call<Modal>getmsg();
}
