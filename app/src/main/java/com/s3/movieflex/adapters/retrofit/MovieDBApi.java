package com.s3.movieflex.adapters.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieDBApi {
    @GET("{type}/{cate}?api_key=452671fab9e5a7eb0349b6139855d282")
    Call<jsonRespons> getMovies(@Path("type") String type, @Path("cate") String cate);


}
