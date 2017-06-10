/*
* Deanna Yee
* deannayee@my.smccd.edu
* CIS 135 OL
* File Name: ChineseZodiacDetails.java
* File Description: contains the class defintion for the chinese zodiac details activity
*                   and displays details for the selected Chinese Zodiac
* Assignment #: Midterm
* Date: 3/12/17
*/
package com.yee.chinesezodiac;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ChineseZodiacDetails extends Fragment {

    //create constants for the image, name, description, and url
    private static final String ARGUMENT_IMAGE_RES_ID = "imageResId";
    private static final String ARGUMENT_NAME = "name";
    private static final String ARGUMENT_DESCRIPTION = "description";
    private static final String ARGUMENT_URL = "url";

    //creates new instances of the chinese zodiac fragment with the image, name, description, and url
    public static ChineseZodiacDetails newInstance(int imageResId, String name,
                                                       String description, String url) {

        final Bundle args = new Bundle();
        //puts all the details into one bundle
        args.putInt(ARGUMENT_IMAGE_RES_ID, imageResId);
        args.putString(ARGUMENT_NAME, name);
        args.putString(ARGUMENT_DESCRIPTION, description);
        args.putString(ARGUMENT_URL, url);

        //creates a new fragment and puts all the details in it
        final ChineseZodiacDetails fragment = new ChineseZodiacDetails();
        fragment.setArguments(args);
        return fragment;
    }

    //creates the view hierarchy controlled by the fragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //gets the different views
        final View view = inflater.inflate(R.layout.fragment_chinese_zodiac_details, container, false);
        final ImageView imageView = (ImageView) view.findViewById(R.id.animal_image);
        final TextView nameTextView = (TextView) view.findViewById(R.id.name);
        final TextView descriptionTextView = (TextView) view.findViewById(R.id.description);

        final Bundle args = getArguments();

        //sets the images and text to the views
        imageView.setImageResource(args.getInt(ARGUMENT_IMAGE_RES_ID));
        nameTextView.setText(args.getString(ARGUMENT_NAME));
        final String text = String.format(getString(R.string.description_format),
                args.getString(ARGUMENT_DESCRIPTION), args.getString(ARGUMENT_URL));
        descriptionTextView.setText(text);
        return view;
    }
}
