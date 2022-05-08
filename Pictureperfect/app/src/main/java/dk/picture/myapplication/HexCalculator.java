package dk.picture.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HexCalculator implements Runnable {
    List<Hex> hexlist;
    List<Hex> finalhexlist;
    ImageView imageView;
    Presenter mainActivityPresenter;
    SortingMachina sortthis;
    /**
     * Constructor*/
    public HexCalculator(ImageView image, Presenter presenter) {
        this.imageView = image;
        mainActivityPresenter = presenter;
    }
//    /**
//     * Calculates space between each pixel so we dont get white or black*/
//    public int sortPixels(int r1, int g1,int b1,int r2, int g2,int b2){
//        return (int) Math.sqrt((r2 -r1)^2+(g2 -g1) ^2+(b2-b1) ^2);
//
//    }
    /**
     * Crates hexidecimal list from bitmap*/
    @Override
    public void run() {
        int oldRedValue = 0;
        int oldBlueValue = 0;
        int oldGreenValue = 0;
        int redValue = 0;
        int blueValue = 0;
        int greenValue = 0;
        hexlist = new ArrayList<>();
        finalhexlist = new ArrayList<>();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        for (int y = 0; y < bitmap.getHeight(); y++) {
            for (int x = 0; x < bitmap.getWidth(); x++) {
                int pixel = bitmap.getPixel(x, y);
                oldRedValue = redValue;
                oldBlueValue = blueValue;
                oldGreenValue = greenValue;
                redValue = +Color.red(pixel);
                greenValue = +Color.green(pixel);
                blueValue = +Color.blue(pixel);
                    String hexcode = String.format("#%02x%02x%02x", redValue, greenValue, blueValue);
                    int hexcount = 1;
                    Hex hex = (Hex) new Hex(hexcode, hexcount);
                    updateListHex(hex);
            }
        }
        mainActivityPresenter.sortHexList(hexlist);
    }
    /**
     *Inserts the input hex if it does not exist in the list already */
    private boolean updateListHex(Hex hex) {
        boolean containshex = false;
        for (Hex item : hexlist) {
            if (item.hexcode.equals(hex.hexcode)) {
                containshex = true;
                item.amount++;
            }
        }
        if (!containshex) {
            hexlist.add(hex);
        }
        return containshex;
    }


}
