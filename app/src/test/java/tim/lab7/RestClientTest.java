package tim.lab7;

import org.junit.Assert;
import org.junit.Test;

import tim.lab7.rest.model.EntryPOJO;
import tim.lab7.rest.service.ExampleApiService;
import tim.lab7.rest.model.RequestPOJO;
import tim.lab7.rest.model.ResponsePOJO;
import tim.lab7.rest.RestClient;


public class RestClientTest {

    @Test
    public void hello() throws Exception {
        RestClient restClient = new RestClient();

        ExampleApiService api = restClient.getApiService();

        String helloString = api.hello();

        Assert.assertEquals("Hello",helloString);
    }

    @Test
    public void produceJSON() throws Exception {
        RestClient restClient = new RestClient();

        ExampleApiService api = restClient.getApiService();

//        ResponsePOJO response = api.produceJSON();

        EntryPOJO entryPOJO = api.getEntry("1");

//        Assert.assertEquals("response name",response.getName());
//        Assert.assertEquals(3 , response.getAttributes().size());
    }

    @Test
    public void consumeJSON() throws Exception {
        RestClient restClient = new RestClient();

        ExampleApiService api = restClient.getApiService();

        RequestPOJO requestPOJO = new RequestPOJO();
        requestPOJO.setRequestData("request data");
        api.consumeJSON("request name" , requestPOJO);

    }


}