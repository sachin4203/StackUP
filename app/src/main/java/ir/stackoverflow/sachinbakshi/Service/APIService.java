package ir.stackoverflow.sachinbakshi.Service;


import java.util.List;

import ir.stackoverflow.sachinbakshi.Model.Question;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ir.stackoverflow.sachinbakshi.Model.Movie;

/**
 * Created by bkhezry on 2/20/2016.
 */
public interface APIService {
    @GET("/search/advanced?order=desc&sort=activity&accepted=False&answers=0&site=stackoverflow")
    Call<Question> getQuestionsService(@Query("tagged") String tagged);

    @GET("movies.json")
    Call<List<Movie>> getMoviesService();
}
