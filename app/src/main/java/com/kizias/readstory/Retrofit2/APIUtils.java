package com.kizias.readstory.Retrofit2;

public class APIUtils {
    public static final String baseURL = "https://truyenyeuthich.com/api/v2/";

    public static DataClient getData(){
        return RetrofitClient.getClient(baseURL).create(DataClient.class);
    }
}
