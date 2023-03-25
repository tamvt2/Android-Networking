package com.example.assigment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.example.assigment.Adapter.Slider_Adapter;

public class MainActivity extends AppCompatActivity {

    SliderView sliderView;
    int[] image_slide = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderView = findViewById(R.id.slide_view);
        Slider_Adapter slider_adapter = new Slider_Adapter(image_slide);
        sliderView.setSliderAdapter(slider_adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }
}