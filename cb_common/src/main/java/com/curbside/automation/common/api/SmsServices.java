package com.curbside.automation.common.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hitesh.grover on 04/08/17.
 */
public class SmsServices {

    static SmsApiInterface smsApiInterface;

    public static void getmsg(){

        Call<Modal> call = smsApiInterface.getmsg();
        call.enqueue(new Callback<Modal>() {
            @Override
            public void onResponse(Call<Modal> call, Response<Modal> response) {
                System.out.println("call = [" + call + "], response = [" + response.body() + "]");
                Modal modal=response.body();
                System.out.println("call = [" + call + "], response = [" + modal.getMessage() + "]");
            }

            @Override
            public void onFailure(Call<Modal> call, Throwable throwable) {
                System.out.println("call = [" + call + "], response = [" + throwable.getMessage() + "]");
            }
        });
    }

}
