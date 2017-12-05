package tim.lab7.rest.service;


import org.parceler.guava.net.MediaType;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import tim.lab7.rest.model.CommentPOJO;
import tim.lab7.rest.model.EntryPOJO;
import tim.lab7.rest.model.LiterarySubstancePOJO;
import tim.lab7.rest.model.RequestPOJO;
import tim.lab7.rest.model.ResponsePOJO;
import tim.lab7.rest.model.StationPOJO;


public interface ExampleApiService {

//    @POST("/json/{name}/data/")
//    String consumeJSON(@Path("name")String name, @Body RequestPOJO requestPOJO);
//
    @GET("/blog/entry/{id}")
    EntryPOJO getEntry(@Path("id")String id);

    @POST("/blog/entry")
    ResponsePOJO addEntry(@Body EntryPOJO entryPOJO);

    @DELETE("/blog/entry/{id}")
    List<EntryPOJO> delEntry(@Path("id")String id);

    @PUT("/blog/entry/{id}")
    EntryPOJO putEntry(@Body EntryPOJO entryPOJO, @Path("id")String id);

    @DELETE("/blog/entry")
    ResponsePOJO delAllEntry();


    @PUT("/blog/entry/{id}/comment")
    CommentPOJO addComment(@Path("id")String id, @Body CommentPOJO commentPOJO);

    @DELETE("/blog/entry/{id}/comment/{id2}")
    ResponsePOJO delComment(@Path("id")String entryId, @Path("id2")String commentId);



    @GET("/airquality/") //zwraca wszystkie stacje
    void getAllStations(Callback<List<StationPOJO>> cb);

    @GET("/airquality/{stationId}/") //zwraca stacje
    void getStationInfo(@Path("stationId")String stationId, Callback<StationPOJO> cb);

    @GET("/literary-substances/")
    void getLiterarySubstances(Callback<List<LiterarySubstancePOJO>> cb);


    @GET("/hello")
    String hello();

    @GET("/json")
    ResponsePOJO produceJSON();

    @POST("/json/{name}/data/")
    String consumeJSON(@Path("name")String name, @Body RequestPOJO requestPOJO);

}
