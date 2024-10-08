package com.jeeclass.androidappclass.apiClient;

import com.jeeclass.androidappclass.model.NotificationModel;
import com.jeeclass.androidappclass.model.Slide;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NotificationApi {

    @GET("get_notices.php")
    Call<List<NotificationModel>> getNotifications();
}
