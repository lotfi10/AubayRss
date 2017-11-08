package com.aubayrss.networking;

import com.aubayrss.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lotfi Fetteni on 08/11/2017.
 */

public class NewsRetrofitApi {

    private static final String APIKEY = "62f1d4e17fca4b5ab746f68af0c8c00e";
    private static final String APIPATH = "https://newsapi.org/v1/";

    private static NewsService newsService = null;

    public static NewsService getApi() {
        if(newsService == null) {
            // initialize NewsService
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIPATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsService = retrofit.create(NewsService.class);
        }
        return newsService;
    }

    public interface NewsService {
        @GET("articles?apiKey=" + APIKEY)
        Call<ArticlesResponse> getArticles(@Query("source") String source, @Query("sortBy") String sortBy);
    }
}
