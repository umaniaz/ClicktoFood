/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.food.clicktofood.Retrofit;

//import com.myapps.x.models.*;

import com.food.clicktofood.Model.DutyStatus;
import com.food.clicktofood.Model.LoginResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("GetMemberLogin") //LOGIN
    Observable<LoginResponse>postLogin(@Query("email") String email,
                                        @Query("phone") String phone,
                                        @Query("password") String password,
                                        @Query("firebaseToken") String firebaseToken);

    @GET("GetDutyStatus") //LOGIN
    Observable<DutyStatus>getDutyStatus(@Query("agentID") String agentID,
                                    @Query("firebaseToken") String firebaseToken);

//    @Multipart
//    @POST("ncsapi.ashx?cmnd=_REQUISITIONV2_")
//    Observable<PostingResponse> postMedicineRequisitionData(@Part("cartData") String cartData,
//                                                            @Part MultipartBody.Part profile_image);



//    @FormUrlEncoded
//    @POST("ncsapi.ashx?cmnd=_FEEDBACK_")
//    Observable<PostingResponse> postFeedback(@Field("name") String name,
//                                             @Field("number") String number,
//                                             @Field("email") String email,
//                                             @Field("message") String message);
}
