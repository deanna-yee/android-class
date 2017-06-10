/*
* Deanna Yee
* deannayee@my.smccd.edu
* CIS 135 OL
* File Name: ChineseZodiacDetails.java
* File Description: contains the class defintion for the chinese zodiac list activity
*                   and displays a list of all the chinese zodiacs
* Assignment #: Midterm
* Date: 3/12/17
*/
package com.yee.chinesezodiac;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ChineseZodiacListFragment extends Fragment {

    //variables for each array
    private int[] imageResIds;
    private String[] names;
    private  String[] descriptions;
    private String[] urls;

    private OnChineseZodiacSelected listener;

    //creates a new instance of a chinese zodiac list fragment
    public static ChineseZodiacListFragment newInstance() {
        return new ChineseZodiacListFragment();
    }

    //accesses the resources you need via the context to which the fragment has attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnChineseZodiacSelected) {
            listener = (OnChineseZodiacSelected) context;
        } else {
            throw new ClassCastException(context.toString() +
                    " must implement OnChineseZodiacSelected.");
        }

        //Get Chinese Zodiacs names and descriptions
        final Resources resources = context.getResources();
        names = resources.getStringArray(R.array.names);
        descriptions = resources.getStringArray(R.array.descriptions);
        urls = resources.getStringArray(R.array.urls);

        //Get Chinese Zodiac images
        final TypedArray imageArray = resources.obtainTypedArray(R.array.images);
        final int imageCount = names.length;
        imageResIds = new int[imageCount];

        for(int i = 0; i < imageCount; i++) {
            imageResIds[i] = imageArray.getResourceId(i, 0);
        }
        imageArray.recycle();
    }

    //displays the list of chinese zodiacs in two columns
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chinese_zodiac_list, container, false);

        final Activity activity = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        recyclerView.setAdapter(new ChineseZodiacAdapter(activity));
        return view;
    }


    class ChineseZodiacAdapter extends RecyclerView.Adapter<ViewHolder>{
        private LayoutInflater layoutInflater;

        public ChineseZodiacAdapter(Context context){
            layoutInflater = LayoutInflater.from(context);
        }


        //designs the framework of how the chinese zodiac and names are diplayed
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            return new ViewHolder(layoutInflater.inflate(R.layout.recycler_item_chinese_zodiac,
                    viewGroup, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position){

            //gets all the information for each image
            final int imageResId = imageResIds[position];
            final String name = names[position];
            final String description = descriptions[position];
            final String url = urls[position];
            viewHolder.setData(imageResId, name);

            //makes the images and names clickable
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void  onClick(View v) {
                    listener.onChineseZodiacSelected(imageResId, name, description, url);
                }
            });
        }

        //gets how many items that need to be displayed
        @Override
        public int getItemCount() {
            return names.length;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // Views
        private ImageView imageView;
        private TextView nameTextView;

        private ViewHolder(View itemView) {
            super(itemView);

            // Get references to image and name.
            imageView = (ImageView) itemView.findViewById(R.id.animal_image);
            nameTextView = (TextView) itemView.findViewById(R.id.name);
        }

        //sets the image and the name of the chinese zodiacs
        private void setData(int imageResId, String name) {
            imageView.setImageResource(imageResId);
            nameTextView.setText(name);
        }
    }

    //listener interface for the activity to listen to the fragment
    public interface OnChineseZodiacSelected {
        void onChineseZodiacSelected(int imageResId, String name, String description, String url);
    }
}


