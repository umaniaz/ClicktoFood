package com.food.clicktofood.Retrofit;

import android.content.Context;

/**
 * Created by Khan on 2/21/2018.
 */

public class ApiUtils {
    private static Context mContext;
    //public static final String BASE_URL = "http://119.18.148.10/cshop/xapi/";
    //public static final String BASE_URL = "http://3.134.89.59:8083/api/Agents/";
    //public static final String BASE_URL = "http://18.220.161.232:8083/api/Agents/";

    //public static final String BASE_URL = "http://18.222.191.88:8083/api/Agents/";  // new production server
    //public static final String BASE_URL = "http://3.22.233.47:8089/api/Agents/";  // new testing server old
    public static final String BASE_URL = "http://3.14.12.76:8089/api/Agents/";  // new testing server new



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
