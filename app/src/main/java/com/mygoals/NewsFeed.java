package com.mygoals;


import android.content.Context;
import android.util.Log;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.mygoals.fragments.Page1;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewsFeed {

public static void getData( Page1 page1) {
  String url = "https://www.themealdb.com/api/json/v1/1/random.php";
//  String url="https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?tags=vegetarian%2Cdessert&number=1";

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.i("News", "Response: " + response.toString());
                    Gson gson = new Gson();

                   NewsReponse news = gson.fromJson(response.toString(), NewsReponse.class);
                    Log.i("News",news.getNews().toString());
                    page1.addNews(news.getNews().get(0));
                    Log.i("News",news.getNews().get(0).toString());
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("News", "Response: " +error.toString());
                }
            }){
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = new HashMap<>();
            headers.put("X-RapidAPI-Key", "6b5c22851bmshb38dd2d2122f7e6p17bfc2jsn6fcf892671ff");
            headers.put("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com");
            return headers;
        }
    };

    // Access the RequestQueue through your singleton class.
    VolleySingleton.getInstance(page1.getContext()).addToRequestQueue(jsonObjectRequest);

}}