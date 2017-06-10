package com.yee.asyncimagedownload;

/*
* Deanna Yee
* CIS 135 OL
* File Name: DownloadAsync.java
* File Description: contains the class definition for the download async activity
*                  downloads and displays the image when button is pressed and sends a notification
* Assignment #: 6
* Date: 4/18/17
*/

import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadAsync extends AppCompatActivity {
    private ImageView imagePlaceHolder;
    private Button downloadButton;

    //path to image
    private static final String imageDownloadPath =
            "https://images-assets.nasa.gov/image/PIA20912/PIA20912~orig.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_async);
        imagePlaceHolder = (ImageView)findViewById(R.id.downloaded_image);
        downloadButton = (Button)findViewById(R.id.download_button);

        //when button is click download image
        downloadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DownloadImageWithURLTask downloadTask =
                        new DownloadImageWithURLTask(imagePlaceHolder);
                downloadTask.execute(imageDownloadPath);
            }
        });
    }

    //send notification
    protected void sendNotification(View view) {
        //create notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Image downloaded")
                        .setContentText("Your NASA image is available");

        //issue notification
        int notificationId = 101;
        NotificationManager notifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notifyMgr.notify(notificationId, builder.build());

    }

    //download image
    private class DownloadImageWithURLTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageWithURLTask(ImageView bmImage){
            this.bmImage = bmImage;
        }

        //download image in the background
        protected Bitmap doInBackground(String... urls){
            //download image
            String pathToFile = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(pathToFile).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            //send notification
            sendNotification(downloadButton);
            return bitmap;
        }

        //post image
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
