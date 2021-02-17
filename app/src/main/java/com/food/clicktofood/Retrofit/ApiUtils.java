package com.food.clicktofood.Retrofit;

import android.content.Context;

/**
 * Created by Khan on 2/21/2018.
 */

public class ApiUtils {
    private static Context mContext;

    //public static final String BASE_URL = "https://api.aurkii.com/api/Agents/";  // new production server for bd

    //public static final String BASE_URL = "http://18.222.191.88:8083/api/Agents/";  // new production server

    //public static final String BASE_URL = "http://3.22.233.47:8089/api/Agents/";  // new testing server old 1
    //public static final String BASE_URL = "http://3.14.12.76:8089/api/Agents/";  // new testing server old 2
    //public static final String BASE_URL = "http://3.129.17.252:8089/api/Agents/";  // new testing server old 3

//    public static final String BASE_URL = "http://13.59.166.80:8089/api/Agents/";  // latest running test server given by arman bhai

    public static final String BASE_URL = "http://18.222.191.88:8083/api/Agents/";  // latest production server given by arman bhai



//    public static APIRetrofitInterface getUserService() {
//        return RetrofitClient.getClient(BASE_URL).create(APIRetrofitInterface.class);
//    }

    public static APIInterface getService() {
        return RetrofitClient.getClient(BASE_URL).create(APIInterface.class);
    }

//    public static APIInterface getMfgService() {
//        return RetrofitClient.getClientMfg(Mfg_URL).create(APIInterface.class);
//    }
//
//    public static APIInterface getCtgShopService() {
//        return RetrofitClient.getCtgShopUrl(CTGShop_URL).create(APIInterface.class);
//    }
//
//    public static APIInterface getBerichService() {
//        return RetrofitClient.getBerichUrl(Berich_URL).create(APIInterface.class);
//    }
//
//    public static APIInterface getBerichService2() {
//        return RetrofitClient.getBerichUrl(Berich_URL2).create(APIInterface.class);
//    }

//    public static APIInterface getCtgShopService2() {
//        return RetrofitClient.getCtgShopUrl(CTGShop_URL_2).create(APIInterface.class);
//    }

//    public static APIInterface getServiceContext(Context context) {
//        mContext = context;
//        return RetrofitClient.getClientContext(BASE_URL, mContext).create(APIInterface.class);
//    }

//    public static APIRetrofitInterface getStringResponse() {
//        return RetrofitScalerClient.getClient(BASE_URL).create(APIRetrofitInterface.class);
//    }


}
