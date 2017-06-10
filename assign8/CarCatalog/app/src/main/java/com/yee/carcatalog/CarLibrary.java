package com.yee.carcatalog;

/*
* Deanna Yee
* CIS 135 OL
* File Name: CarLibrary.java
* File Description: contains the class definition for main activity
* Assignment #: 8
* Date: 5/11/17
*/

import android.app.Fragment;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.widget.ImageView;

public class CarLibrary extends FragmentActivity{

    //XML typed resource array indexes
    private static final int IDX_MODEL = 0;
    private static final int IDX_YEAR = 1;
    private static final int IDX_PRICE = 2;
    private static final int IDX_SPECS = 3;
    private static final int IDX_IMAGE = 4;

    private DotsPageIndicator mPageIndicator;
    private GridViewPager mViewPager;
    private ImageView mImageView;

    private static String[][] mCarInfo;
    private static Drawable[] mCarImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrieve our car library info and images
        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(R.array.cars);
        int n = ta.length();
        mCarInfo = new String[n][];
        mCarImages =  new Drawable[n];
        for (int i = 0; i < n; ++i) {
            mCarImages[i] = res.obtainTypedArray(ta.getResourceId(i, 0)).getDrawable(IDX_IMAGE);
            mCarInfo[i] = res.getStringArray(ta.getResourceId(i, 0));
        }
        ta.recycle();

        //Get UI references
        mImageView = (ImageView)findViewById(R.id.car_imageview);
        mImageView.setScaleType(ImageView.ScaleType.FIT_START);
        mImageView.setBackgroundColor(Color.parseColor("gray"));
        mPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        mViewPager = (GridViewPager) findViewById(R.id.pager);

        //Assigns an adapter to provide the content for this pager
        mViewPager.setAdapter(new CarGridPagerAdapter(this));

        /*Supply the GridViewPager instance in order to attach OnPageChangeListener and
        * OnAdapterChangeListener to the pager*/
        mPageIndicator.setPager(mViewPager);

        mViewPager.setOnPageChangeListener(new GridViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int row, int column, float rowOffset,
                                       float columnOffset, int rowOffsetPixels,
                                       int columnOffsetPixels) {
                //This is called when the scroll state changes
                mPageIndicator.onPageScrolled(row, column, rowOffset, columnOffset, rowOffsetPixels,
                        columnOffsetPixels);
            }

            //sets the info on the page that is selected
            @Override
            public void onPageSelected(int row, int column) {
                mImageView.setImageDrawable(mCarImages[row]);
                mPageIndicator.onPageSelected(row, column);
            }

            //changes the page when scrolled up, down, left, or right
            @Override
            public void onPageScrollStateChanged(int state) {
                mPageIndicator.onPageScrollStateChanged(state);
            }
        });

        //set our first background image
        mImageView.setImageDrawable(mCarImages[0]);
    }

    private static final class CarGridPagerAdapter extends FragmentGridPagerAdapter {

        private static final int SUMMARY = 0;
        private static final int SPECIFICATIONS = 1;
        private static final int ADD_NOTES = 2;
        private static final int SCHEDULE_TEST_DRIVE = 3;
        private static final int COLUMNS = 4;

        private CarGridPagerAdapter(FragmentActivity activity) {
            super(activity.getFragmentManager());
        }

        //gets the correct fragment for either summary, specification, add notes or schedule
        @Override
        public Fragment getFragment(int row, int column) {
            switch (column) {
                case SUMMARY:
                    final String title = mCarInfo[row][IDX_YEAR] + " " + mCarInfo[row][IDX_MODEL];
                    final String price = "Starting at $" + NumberFormat.getIntegerInstance()
                            .format(Integer.parseInt(mCarInfo[row][IDX_PRICE]));
                    return CardFragment.create(title, price);
                case SPECIFICATIONS:
                    CardFragment cardFragment= CardFragment.create("Specifications",
                            mCarInfo[row][IDX_SPECS]);
                    cardFragment.setExpansionEnabled(true);
                    cardFragment.setExpansionFactor(3.0f);
                    return  cardFragment;
                case ADD_NOTES:
                    return new FragmentAddNote();
                case SCHEDULE_TEST_DRIVE:
                    return new FragmentTestDrive();
                default:
                    throw new IllegalArgumentException("getFragment(row=" + row +
                            ", column=" + column + ")");
            }
        }

        //gets the row count
        @Override
        public int getRowCount(){
            return mCarInfo.length;
        }

        //gets the column count
        @Override
        public int getColumnCount(int row) {
            return COLUMNS;
        }
    }
}
