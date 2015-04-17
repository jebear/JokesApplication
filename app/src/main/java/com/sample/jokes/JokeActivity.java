package com.sample.jokes;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;



public class JokeActivity extends Activity {

    /**
     * Views
     * */
    private TextView tvJoke;

    private String stringURL;
    private String stringResponse;
    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Bundle bundle = getIntent().getExtras();
        stringURL = bundle.get("url")== null?"":bundle.get("url").toString();

        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getRequest(stringURL);

    }

    private void initViews(){
        tvJoke = (TextView) findViewById(R.id.tvJoke);
    }



    private void getRequest(String url){
        RequestQueue queue = Volley.newRequestQueue(this);

// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        stringResponse = response.toString();
                        jsonParse(stringResponse);

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                        finish();
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);

    }

    public void jsonParse(String response) {
        JsonElement jelement = new JsonParser().parse(response);
        JsonObject  jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("value");
        joke = jobject.get("joke").getAsString();
        tvJoke.setText(Html.fromHtml(joke));
    }
}
