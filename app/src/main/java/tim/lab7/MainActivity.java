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

    @AfterViews
    void initList() {
        stationIdList = new ArrayList<>();
        getStationList();
        stationList.setOnItemClickListener(this);
    }

    @Click
    void getStationListButton() {
        getStationList();
    }


    @Background
    void getStationList() {
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
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("MainActivity", error.getMessage() + "\n" + error.getStackTrace());
                error.printStackTrace();

            }
        };
        methods.getAllStations(cb);
    }

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
        Intent intent = new Intent(getApplicationContext(), EntryActivity_.class);
        intent.putExtra("choosenStationId", stationIdList.get(position));
        startActivity(intent);
    }
}
