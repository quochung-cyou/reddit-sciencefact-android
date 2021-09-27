package com.quochung.redditapi_sciencefact.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.quochung.redditapi_sciencefact.R;
import com.quochung.redditapi_sciencefact.adapter.PostAdapter;
import com.quochung.redditapi_sciencefact.api.VolleyS;
import com.quochung.redditapi_sciencefact.model.PostModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<PostModel> postlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = VolleyS.getmInstance(this).getRequestQueue();
        postlist = new ArrayList<>();
        fetchpost();

    }


    public void fetchpost() {
        String url = "https://www.reddit.com/r/ScienceFacts.json";

        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray allpost = response.getJSONObject("data").getJSONArray("children");
                            for (int i = 0 ; i < allpost.length() ; i ++){
                                JSONObject post = allpost.getJSONObject(i).getJSONObject("data");
                                String author = post.getString("author_fullname");
                                String title = post.getString("title");
                                Random random = new Random();
                                String thumbnail = "https://picsum.photos/" + random.nextInt(2000) + "/" + random.nextInt(2000) + "?grayscale" + "?blur";
                                 String secondFolder = String.valueOf(random.nextInt(9));
                                String randomFile = String.valueOf(random.nextInt(999));
                                String avatar = "https://ozgrozer.github.io/100k-faces/0/" + secondFolder + "/00" + secondFolder + randomFile + ".jpg";


                                PostModel newpost = new PostModel(title, author, thumbnail, avatar);
                                postlist.add(newpost);
                            }
                            PostAdapter adapter = new PostAdapter(MainActivity.this , postlist);

                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, String.valueOf(e), Toast.LENGTH_LONG).show();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(JsonObjectRequest);
    }

    }