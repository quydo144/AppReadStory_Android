package com.kizias.readstory.Retrofit2;

public class APIUtils {
    public static final String baseURL = "https://truyenyeuthich.com/api/v2/";
    public static final String baseURLHistory = "http://13.229.207.6:9000";

    public static DataClient getData(){
        return RetrofitClient.getClient(baseURL).create(DataClient.class);
    }

    public static DataClient getDataHistory(){
        return RetrofitClient.getClient(baseURLHistory).create(DataClient.class);
    }
}
