package com.example.assigment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.assigment.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class Slider_Adapter extends SliderViewAdapter<Slider_Adapter.Holder> {

    int[] image_slide;

    public Slider_Adapter(int[] image_slide){
        this.image_slide = image_slide;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        viewHolder.imageView.setImageResource(image_slide[position]);

    }



    @Override
    public int getCount() {
        return image_slide.length;
    }

    public class Holder extends ViewHolder{
        ImageView  imageView;

        public Holder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_slide);
        }
    }

}
