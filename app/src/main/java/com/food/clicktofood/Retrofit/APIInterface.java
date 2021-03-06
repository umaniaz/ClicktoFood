/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.food.clicktofood.Retrofit;

//import com.myapps.x.models.*;

import com.food.clicktofood.Model.AcceptedTaskListResponse;
import com.food.clicktofood.Model.AllTaskResponseModel;
import com.food.clicktofood.Model.CashCollectionResponse;
import com.food.clicktofood.Model.DataUpdatePostModel;
import com.food.clicktofood.Model.DutyStatus;
import com.food.clicktofood.Model.ImageUploadResponse;
import com.food.clicktofood.Model.JobCompleteResponse;
import com.food.clicktofood.Model.JobListResponse;
import com.food.clicktofood.Model.LocationResponse;
import com.food.clicktofood.Model.LoginResponse;
import com.food.clicktofood.Model.OTPResponse;
import com.food.clicktofood.Model.StatusPostingResponse;
import com.food.clicktofood.Model.WaitingResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
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

    @GET("GetTaskList") //LOGIN
    Observable<JobListResponse>getJobList(@Query("agentID") String agentID);

    @GET("GetTaskListV1") //LOGIN    // V1 for real data
    Observable<JobListResponse>getJobListNew(@Query("agentID") String agentID);

    @GET("GetNewTaskListV1") //new accepted task    // V1 for real data
    Observable<AcceptedTaskListResponse>getAcceptedJobListNew(@Query("agentID") String agentID);

//    @GET("GetTaskListV2") //LOGIN
//    Observable<JobListResponse>getJobListNew(@Query("agentID") String agentID);

    @GET("GetTaskAcceptOrReject") //LOGIN
    Observable<StatusPostingResponse>postStatus(@Query("agentID") String agentID,
                                                   @Query("taskid") String firtaskidebaseToken,
                                                   @Query("status") Integer status);

    @GET("GetLatlon") //location
    Observable<LocationResponse>postLocation(@Query("lat") Double lat,
                                             @Query("lon") Double lon,
                                             @Query("agentID") String agentID);


    @Multipart
    @POST("PostImagetest")
    Observable<ImageUploadResponse> postImage(@Part MultipartBody.Part profile_image);


    @GET("GetProfileUpdate")
    Observable<LoginResponse> postDataUpdate(@Query("agentid") String agentid,
                                             @Query("FullName") String FullName,
                                             @Query("password") String password);


    @GET("GetCompletedTask") //LOGIN
    Observable<AllTaskResponseModel>getAllJobList(@Query("agentid") String agentID,
                                                  @Query("CreatedDate") String CreatedDate);

    @GET("GetLoggedOut") //LOGOUT
    Observable<StatusPostingResponse>postLogout(@Query("agentid") String agentID);

    @GET("GetCashOn") //LOGOUT
    Observable<CashCollectionResponse>getCashCollection(@Query("agentid") String agentID,
                                                        @Query("CreatedDate") String CreatedDate);

    @GET("GetWaitingTime") //LOGIN
    Observable<WaitingResponse>postStatus(@Query("agentid") String agentID,
                                          @Query("taskid") String taskId);

    @GET("GetOtpMatch") //LOGIN
    Observable<OTPResponse>verifyOTP(@Query("agentid") String agentid,
                                      @Query("taskid") String taskid,
                                      @Query("otp") String otp);

    @GET("GetCompleteTask") //LOGIN
    Observable<JobCompleteResponse>postFinalStatus(@Query("agentid") String agentID,
                                              @Query("taskid") String firtaskidebaseToken,
                                              @Query("status") Integer status,
                                                   @Query("lat") Double lat,
                                                   @Query("lon") Double lon);

//    @FormUrlEncoded
//    @POST("ncsapi.ashx?cmnd=_FEEDBACK_")
//    Observable<PostingResponse> postFeedback(@Field("name") String name,
//                                             @Field("number") String number,
//                                             @Field("email") String email,
//                                             @Field("message") String message);
}
