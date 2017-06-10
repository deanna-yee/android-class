package com.yee.carcatalog;

/*
* Deanna Yee
* CIS 135 OL
* File Name: FragmentTestDrive.java
* File Description: contains the class definition for setting up a test drive
* Assignment #: 8
* Date: 5/11/17
*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTestDrive extends android.app.Fragment implements
        DelayedConfirmationView.DelayedConfirmationListener {

    //Timeout delay for confirmation
    private static final long DELAY_TIMEOUT = 2500L;

    private DelayedConfirmationView mConfirmationView;
    private TextView mTextView;
    private boolean mIsAnimating = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate and customize the UI from its XML Layout definition
        final View view = inflater.inflate(R.layout.fragment_view, container, false);
        mConfirmationView = (DelayedConfirmationView) view.findViewById(R.id.delayed_confirm);
        mConfirmationView.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_call));
        mTextView = (TextView) view.findViewById(R.id.label);
        mTextView.setText("Schedule Test Drive");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Handle click in order to start the free-form recognition
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAnimating) {
                mConfirmationView.setImageResource(R.drawable.ic_action_call);
                mIsAnimating = false;
                return;
                }
                mIsAnimating = true;
                mConfirmationView.setImageResource(R.drawable.ic_full_cancel);
                mConfirmationView.setTotalTimeMs(DELAY_TIMEOUT);
                mConfirmationView.start();
            }
        });
        //set a listener when the view is tapped or time has elapsed
        mConfirmationView.setListener(this);
    }

    @Override
    public void onTimerFinished(View view) {
        mIsAnimating = false;
        final Activity activity = getActivity();
        if (activity == null) {
            //Fragment no longer belongs to the activity
            return;
        }

        //Starting the confirmation screen
        Intent intent =  new Intent(activity, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                ConfirmationActivity.SUCCESS_ANIMATION);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Scheduled");
        startActivity(intent);

        mConfirmationView.reset();
        mConfirmationView.setImageResource(R.drawable.ic_action_call);
    }

    @Override
    public void onTimerSelected(View view){
        mConfirmationView.reset();
    }

}
