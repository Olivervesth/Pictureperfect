package dk.picture.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

public class Presenter {
    HexCalculator hexcalc;
    SortingMachina sortingmachina;
    MainActivity main;
    /**
     * Starts a HexCalculator thread*/
    public void createCalcThread(ImageView image,MainActivity main){
        main = main;
        hexcalc = new HexCalculator(image,Presenter.this);
        Thread t = new Thread(hexcalc);
        t.start();
    }
    /**
     * Starts a SortingMachina thread*/
    public void sortHexList(List<Hex> hexlist) {
        sortingmachina = new SortingMachina(this, hexlist);
        Thread k = new Thread(sortingmachina);
        k.run();
    }
    /**
     * Sends Hex data string to MainActivity*/
    public void updateHexList(List<Hex> hexlist) {
        String hexstring = "";
        for (int i = 0; i < hexlist.size(); i++) {
            hexstring += hexlist.get(i).hexcode + " : " + hexlist.get(i).amount + "\n";

        }
        UpdateDrawableUi(hexstring,hexlist);

    }
    /**
     * Sets the info for Presenter in order to make it work probably*/
    public void info(MainActivity m){
        main = m;
    }
    /**
     * Returns a bitmap of the input data*/
    public Bitmap getImageBitmap(Intent data){
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        return imageBitmap;
    }
    /**
     * Sends the hexcode's converted to color to Main*/
    public void UpdateDrawableUi(String hexstring,List<Hex> hexlist){
        if (hexlist.size()>0){
            for (int i=0;i <5;i++){
                String hexcode = hexlist.get(i).hexcode;
                ColorDrawable newcolor = null;
                try {
                    newcolor = new ColorDrawable(Color.parseColor(hexcode));//Convert hexcode into ColorDrawable
                }catch (NumberFormatException e){
                    Log.d("NumberFormatException", "updateTopFiveColors: "+e);
                }

                if ( newcolor != null){
                    main.updateColor(hexlist.get(i),i,newcolor);
                }
            }
        }else{
            Hex hex = null;
            ColorDrawable draw = null;
            main.updateColor(hex,0,draw);
        }
    }

}
