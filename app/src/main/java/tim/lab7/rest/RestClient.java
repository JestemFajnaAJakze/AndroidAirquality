package tim.lab7.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import tim.lab7.rest.model.CommentPOJO;
import tim.lab7.rest.model.EntryPOJO;
import tim.lab7.rest.model.StationPOJO;
import tim.lab7.rest.service.ExampleApiService;

public class RestClient
{
    private  String BASE_URL = "";
    private ExampleApiService apiService;

    public RestClient(String url)
    {
        this.BASE_URL = url;

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(ExampleApiService.class);
    }

    public ExampleApiService getApiService()
    {
        return apiService;
    }



    public void addEntry(EntryPOJO entryPOJO)
    {
        ExampleApiService api = this.getApiService();
        api.addEntry(entryPOJO);
    }


    public void delEntry(String id) {
        ExampleApiService api = this.getApiService();
        api.delEntry(id);
    }

    public void putEntry(EntryPOJO entryPOJO, String id) {
        ExampleApiService api = this.getApiService();
        api.putEntry(entryPOJO,id);
    }

    public void delAllEntry() {
        ExampleApiService api = this.getApiService();
        api.delAllEntry();
    }

    public void addComment(String entryId, CommentPOJO commentPOJO) {
        ExampleApiService api = this.getApiService();
        api.addComment(entryId, commentPOJO);
    }

    public void delComment(String entryId, String commentId) {
        ExampleApiService api = this.getApiService();
        api.delComment(entryId, commentId);
    }

    public EntryPOJO getEntryById (String id) throws Exception
    {
        ExampleApiService api = this.getApiService();
        EntryPOJO response = api.getEntry(id);
        return response;
    }
//    public List<StationPOJO> getAllStations()
//    {
//        ExampleApiService api = this.getApiService();
//        List<StationPOJO> response = api.getAllStations();
//        return response;
//    }


}