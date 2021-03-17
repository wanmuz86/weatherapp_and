package my.com.anak2u.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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


    }
}