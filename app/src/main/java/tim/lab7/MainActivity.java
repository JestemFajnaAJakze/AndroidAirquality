package tim.lab7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import tim.lab7.rest.RestClient;
import tim.lab7.rest.model.StationPOJO;
import tim.lab7.rest.service.ExampleApiService;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    RestAdapter restAdapter;
    static final String API_URL = "http://10.0.2.2:8282/lab6_v2Web/";

    @ViewById
    ListView stationList;
    private ArrayList<String> stationIdList;
    private ArrayAdapter<String> adapter;
    List<StationPOJO> stations = new ArrayList<StationPOJO>();

    @AfterViews
    void initList() {
        stationIdList = new ArrayList<>();
//        stationIdList.add("Stacja 1");
//        stationIdList.add("Stacja 2");
//        stationIdList.add("Stacja 3");
//        stationIdList.add("Stacja 3");
//        stationIdList.add("Stacja 3");
//        stationIdList.add("Stacja 3");
//        stationIdList.add("Stacja 3");
//        stationIdList.add("Stacja 3");
//        stationIdList.add("Stacja 3");
//        stationIdList.add("Stacja 3");
//        stationIdList.add("Stacja 3");
//        stationIdList.add("Stacja 3");
//        stationIdList.add("Stacja 3");
//        stationIdList.add("Stacja 3");


        getStationList();
        stationList.setOnItemClickListener(this);



    }

//    @Click
//    void EntryMethodsButton() {
//        Intent i = new Intent(this, EntryActivity_.class);
//        startActivityForResult(i, 1);
//    }

    @Click
    void getStationListButton() {
        getStationList();
    }


    @Background
    void getStationList() {
//        Toast.makeText(getApplicationContext(),
//                "REST: ", Toast.LENGTH_SHORT)
//                .show();
        //TODO tu bedzie RESTowa komunikacja

        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(15000, TimeUnit.MILLISECONDS);
        mOkHttpClient.setReadTimeout(15000, TimeUnit.MILLISECONDS);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setClient(new OkClient(mOkHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        ExampleApiService methods = restAdapter.create(ExampleApiService.class);


        Callback<List<StationPOJO>> cb = new Callback<List<StationPOJO>>() {

            @Override
            public void success(List<StationPOJO> categories, retrofit.client.Response response) {

                stationIdList = new ArrayList<>();
                for (StationPOJO c : categories) {

                    stationIdList.add(c.getStationId());

                }

                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stationIdList);
                stationList.setAdapter(adapter);
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1, categoryNameList);

            }


            @Override
            public void failure(RetrofitError error) {
                Log.e("MainActivity", error.getMessage() + "\n" + error.getStackTrace());
                error.printStackTrace();


            }
        };
        methods.getAllStations(cb);

        /*try {
            RestClient client = new RestClient("http://localhost:8282/lab6_v2Web/");



            stations = client.getAllStations();
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("ILE STACJI: " + stations.size());
//                    idText.setText(Integer.toString(entryPOJO.getId()));
//                    dateText.setText(entryPOJO.getDate());
//                    subjectText.setText(entryPOJO.getSubject());
//                    contentText.setText(entryPOJO.getContent());
//                    commentsText.setText(entryPOJO.getComments().toString());
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Wrong request parameters!", Toast.LENGTH_SHORT).show();
        }*/
    }




    @Background
//    void getEntry(){
//        try {
//            RestClient client = new RestClient(urlText.getText().toString());
//            final EntryPOJO entryPOJO = client.getEntryById(getEntryIdText.getText().toString());
//            EntryActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(entryPOJO.toString());
//                    idText.setText(Integer.toString(entryPOJO.getId()));
//                    dateText.setText(entryPOJO.getDate());
//                    subjectText.setText(entryPOJO.getSubject());
//                    contentText.setText(entryPOJO.getContent());
//                    commentsText.setText(entryPOJO.getComments().toString());
//                }
//            });
//        }
//        catch (Exception e){
//            Toast.makeText(getApplicationContext(), "Wrong request parameters!",Toast.LENGTH_SHORT).show();
//        }
//
//
//    }


    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_main);
        getStationList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        getStationList();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String choosenTestId = "0";
//        choosenTestId = stationIdList.get(position);
//        //choosenTestId = position+1;
        Toast.makeText(getApplicationContext(),
                "Wybrales: " + stationIdList.get(position), Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(getApplicationContext(), EntryActivity_.class);
        intent.putExtra("choosenStationId", stationIdList.get(position));

        startActivity(intent);
    }

//    @Click
//    void CommentMethodsButton() {
//        Intent i = new Intent(this, CommentActivity_.class);
//        startActivityForResult(i, 1);
//    }

}
