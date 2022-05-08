package dk.picture.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    Presenter mainactivitypresenter = new Presenter();
    /**
     * When the app gets created it will call this function
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    /**
     * Starts the process of taking a picture
     */
    public void triggerBtn(View view) {
        mainactivitypresenter.info(MainActivity.this);
       dispatchTakePictureIntent();
    }

    /**
     * Opens up the camera app
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }

    /**
     * Inserts the image delivered into our imageview
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            image = (ImageView) findViewById(R.id.Selfie);
            Bitmap imagebitmap=mainactivitypresenter.getImageBitmap(data);
            image.setImageBitmap(imagebitmap);
        }
    }

    /**
     *Sends the taken image to the presenter*/
    public void startColorProccess(View view) {
        ImageView imageView = ((ImageView) image);
        mainactivitypresenter.createCalcThread(imageView, MainActivity.this);
    }

    /**
     * Updates the Ui with the top five colors in the taken picture*/
    public void updateColor(Hex hex,int count,ColorDrawable color){
        if (hex != null){
            switch (count){
                case 0:
                    TextView t1 = (TextView) findViewById(R.id.color1);
                    t1.setBackground(color);
                    t1.setText(hex.hexcode);
                    break;
                case 1:
                    TextView t2 = (TextView) findViewById(R.id.color2);
                    t2.setBackground(color);
                    t2.setText(hex.hexcode);
                    break;
                case 2:
                    TextView t3 = (TextView) findViewById(R.id.color3);
                    t3.setBackground(color);
                    t3.setText(hex.hexcode);
                    break;
                case 3:
                    TextView t4 = (TextView) findViewById(R.id.color4);
                    t4.setBackground(color);
                    t4.setText(hex.hexcode);
                    break;
                case 4:
                    TextView t5 = (TextView) findViewById(R.id.color5);
                    t5.setBackground(color);
                    t5.setText(hex.hexcode);
                    break;
                default:
                    break;
            }

        }
    }
}


