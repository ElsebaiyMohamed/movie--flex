package com.s3.movieflex.adapters.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieDBApi {
    @GET("movie/{cate}?api_key=452671fab9e5a7eb0349b6139855d282")
    Call<JsonMovieRespons> getMovies(@Path("cate") String cate);

    @GET("tv/{cate}?api_key=452671fab9e5a7eb0349b6139855d282")
    Call<JsonMovieRespons> getTvShows(@Path("cate") String cate);

}
