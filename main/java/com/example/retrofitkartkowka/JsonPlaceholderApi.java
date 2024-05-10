package com.example.retrofitkartkowka;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {
    @GET("gryPlanszowe")
    Call<List<Planszowki>> getPlanszowki();
}
