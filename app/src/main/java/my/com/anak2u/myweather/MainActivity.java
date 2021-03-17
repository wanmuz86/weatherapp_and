package my.com.anak2u.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
EditText searchEditText;
ImageView iconImageView;
TextView tempTextView, feelsLikeTextView, pressureTextView,
    cityTextView, humidityTextView, windTextView, sunsetTextView,
    sunriseTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchEditText = findViewById(R.id.searchCity);
        iconImageView = findViewById(R.id.iconImageView);
        tempTextView = findViewById(R.id.tempTextView);
        feelsLikeTextView = findViewById(R.id.ramalanTextView);
        pressureTextView = findViewById(R.id.pressure);
        humidityTextView = findViewById(R.id.humidity);
        windTextView = findViewById(R.id.windSpeed);
        sunsetTextView = findViewById(R.id.sunsetTextView);
        sunriseTextView = findViewById(R.id.sunriseTextView);
        cityTextView = findViewById(R.id.cityName);
    }

    public void searchClicked(View view) {
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.openweathermap.org/data/2.5/weather?q="+searchEditText.getText().toString()+"&appid=9fd7a449d055dba26a982a3220f32aa2";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        cityTextView.setText("Response is: "+ response);
                        // Untuk saya buat operasi seterusnya
                        try {
                            JSONObject responseObj = new JSONObject(response);
                            String city = responseObj.getString("name");
                            String country = responseObj.getJSONObject("sys")
                                    .getString("country");
                            cityTextView.setText(city+" ,"+country);
                            double temp = responseObj.getJSONObject("main")
                                    .getDouble("temp");
                            tempTextView.setText(Math.round(temp-273.15)+" C");
                            int pressure = responseObj.getJSONObject("main").getInt("pressure");

                            pressureTextView.setText(pressure+" hpa");

                            int humidity = responseObj.getJSONObject("main").getInt("humidity");
                            humidityTextView.setText(humidity+"");
                            double windspeed = responseObj.getJSONObject("wind").getDouble("speed");
                            windTextView.setText(windspeed +" m/s");

                            int sunsentdt = responseObj.getJSONObject("sys").getInt("sunset");
                            Date sunset = new Date(sunsentdt* 1000);
                            sunsetTextView.setText(sunset.toString());

                            String weather = responseObj
                                    .getJSONArray("weather").getJSONObject(0)
                                    .getString("main");
                            feelsLikeTextView.setText(weather);

                            String iconId = responseObj
                                    .getJSONArray("weather").getJSONObject(0)
                                    .getString("icon");
                            String url = "https://openweathermap.org/img/w/"+iconId+".png";
                            Glide.with(MainActivity.this)
                                    .load(url).into(iconImageView);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, error -> cityTextView.setText("That didn't work!"));

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}