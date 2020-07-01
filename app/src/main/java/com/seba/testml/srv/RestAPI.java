package com.seba.testml.srv;

import com.seba.testml.srv.dto.QueryModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestAPI {

    // example: curl -X GET https://api.mercadolibre.com/sites/MLA/search?q=Motorola%20G6
    @GET("sites/{SITE_ID}/search")
    Call<QueryModel> getItemsByQuery(@Path("SITE_ID") String idSite, @Query("q") String query);

    @GET("sites/{SITE_ID}/search")
    Call<QueryModel> getItemsByQuery(@Path("SITE_ID") String idSite, @Query("q") String query, @Query("offset") String offset);

    @GET("sites/{SITE_ID}/search")
    Call<QueryModel> getItemsByQuery(@Path("SITE_ID") String idSite, @Query("q") String query, @Query("offset") int offset, @Query("limit") int limit);

    // example: curl -X GET https://api.mercadolibre.com/sites/MLA/search?category=MLA1055
    @GET("sites/{SITE_ID}/search?category={CATEGORY_ID}")
    Call<ResponseBody> getItemsByCategory(@Path("SITE_ID") String idSite, @Path("CATEGORY_ID") String idCategory);

}