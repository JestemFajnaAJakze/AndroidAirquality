package tim.lab7;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import org.parceler.transfuse.annotations.Asynchronous;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import tim.lab7.rest.RestClient;
import tim.lab7.rest.model.Address;
import tim.lab7.rest.model.CommentPOJO;
import tim.lab7.rest.model.EntryPOJO;
import tim.lab7.rest.model.LiterarySubstancePOJO;
import tim.lab7.rest.model.StationAdapter;
import tim.lab7.rest.model.StationPOJO;
import tim.lab7.rest.model.SubstanceAll;
import tim.lab7.rest.model.SubstancePOJO;
import tim.lab7.rest.service.ExampleApiService;

import static tim.lab7.R.layout.station_row;

@EActivity(R.layout.activity_station)
public class EntryActivity extends AppCompatActivity {

    private String choosenStationId;

    StationPOJO stationPOJO;
    List<LiterarySubstancePOJO> literarySubstances = new ArrayList<LiterarySubstancePOJO>();
    List<SubstanceAll> substanceAllList;


    RestAdapter restAdapter;
    static final String API_URL = "http://10.0.2.2:8282/lab6_v2Web/";

    @ViewById
    TextView stationId;
    @ViewById
    TextView city;

    @ViewById
    TextView street;

    @ViewById
    ListView substanceList;

    @Click
    void GetStationInfoByIdButton() {
        substanceAllList.clear();
        initializeData();
    }

    private StationPOJO getStationInfoById() {

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

        Callback<StationPOJO> cb = new Callback<StationPOJO>() {

            @Override
            public void success(StationPOJO questions, retrofit.client.Response response) {

                stationPOJO = new StationPOJO();
                stationPOJO.setStationId(questions.getStationId());
                Address address = new Address();
                address.setCity(questions.getStationAddress().getCity());
                address.setStreet(questions.getStationAddress().getStreet());
                stationPOJO.setStationAddress(address);
                List<SubstancePOJO> substancePOJOS = new ArrayList<>();

                for (int i = 0; i < questions.getSubstances().size(); i++) {
                    SubstancePOJO substancePOJO = new SubstancePOJO();
                    substancePOJO.setSubstanceName(questions.getSubstances().get(i).getSubstanceName());
                    substancePOJO.setType(questions.getSubstances().get(i).getType());
                    substancePOJO.setValue(questions.getSubstances().get(i).getValue());
                    substancePOJOS.add(substancePOJO);
                }
                stationPOJO.setSubstances(substancePOJOS);
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }

        };
        methods.getStationInfo(choosenStationId, cb);

        OkHttpClient mOkHttpClient2 = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(15000, TimeUnit.MILLISECONDS);
        mOkHttpClient.setReadTimeout(15000, TimeUnit.MILLISECONDS);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setClient(new OkClient(mOkHttpClient2))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        ExampleApiService methods2 = restAdapter.create(ExampleApiService.class);


        Callback<List<LiterarySubstancePOJO>> cb2 = new Callback<List<LiterarySubstancePOJO>>() {

            @Override
            public void success(List<LiterarySubstancePOJO> literarySubstancePOJOList, retrofit.client.Response response) {

                for (LiterarySubstancePOJO literarySubstancePOJO : literarySubstancePOJOList) {
                    literarySubstances.add(literarySubstancePOJO);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }


        };
        methods2.getLiterarySubstances(cb2);

        return stationPOJO;

    }

    public double findSubstanceTreshold(SubstancePOJO substancePOJO, List<LiterarySubstancePOJO> literarySubstancePOJOS) {

        for (LiterarySubstancePOJO literarySubstancePOJO : literarySubstancePOJOS) {
            if (substancePOJO.getType().equals(literarySubstancePOJO.getSubstanceId())) {
                return literarySubstancePOJO.getTreshold();
            }
        }
        return 0d;
    }

    public String findSubstanceUnit(SubstancePOJO substancePOJO, List<LiterarySubstancePOJO> literarySubstancePOJOS) {

        for (LiterarySubstancePOJO literarySubstancePOJO : literarySubstancePOJOS) {
            if (substancePOJO.getType().equals(literarySubstancePOJO.getSubstanceId())) {
                return literarySubstancePOJO.getUnit();
            }
        }
        return "EMPTY";
    }

    private List<SubstanceAll> mergeSubstancesLists(List<SubstancePOJO> substancePOJOS, List<LiterarySubstancePOJO> literarySubstancePOJOS) {
        for (SubstancePOJO substancePOJO : substancePOJOS) {
            SubstanceAll substanceAll = new SubstanceAll();
            substanceAll.setSubstanceId(substancePOJO.getType());
            substanceAll.setSubstanceName(substancePOJO.getSubstanceName());
            substanceAll.setTreshold(findSubstanceTreshold(substancePOJO, literarySubstancePOJOS));
            substanceAll.setUnit(findSubstanceUnit(substancePOJO, literarySubstancePOJOS));
            substanceAll.setValue(substancePOJO.getValue());
            substanceAllList.add(substanceAll);
        }
        return substanceAllList;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_station);
        initializeData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeData();
    }

    void initializeData() {
        stationPOJO = new StationPOJO();
        choosenStationId = "0";
        choosenStationId = getIntent().getStringExtra("choosenStationId");
        stationPOJO = getStationInfoById();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setStationData();
            }
        }, 300);
    }

    void setStationData() {
        substanceAllList = new ArrayList<>();

        try {
            stationId.setText("ID: " + stationPOJO.getStationId());
            city.setText("Miasto: " + stationPOJO.getStationAddress().getCity());
            street.setText("Ulica: " + stationPOJO.getStationAddress().getStreet());
            mergeSubstancesLists(stationPOJO.getSubstances(), literarySubstances);
            System.out.println("all " + substanceAllList.size());
            StationAdapter stationAdapter = new StationAdapter(this, R.layout.station_row, substanceAllList);
            substanceList.setAdapter(stationAdapter);
        } catch (Exception e) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setStationData();
                }
            }, 300);
        }
    }

}
