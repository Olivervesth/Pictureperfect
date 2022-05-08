package dk.picture.myapplication;

import java.util.ArrayList;
import java.util.List;

public class SortingMachina implements Runnable {
    List<Hex> topFive = new ArrayList<>();
    Presenter mainActivityPresenter;
    List<Hex> hlist;
    /**
     * Constructor*/
    public SortingMachina(Presenter presenter, List<Hex> hexlist) {
        mainActivityPresenter = presenter;
        hlist = hexlist;
    }
    /**
     *Sorting Hex items */
    @Override
    public void run() {
        if (hlist.size()>0){
            for (int i = 0; i < 5; i++) {
                Hex temp = new Hex(null, 0);
                int ind = 0;
                List<Hex> aa = new ArrayList<>(hlist);
                for (int y = 0; y < aa.size(); y++) {
                    if (aa.get(y).amount > temp.amount) {
                        temp.amount = aa.get(y).amount;
                        temp.hexcode = aa.get(y).hexcode;
                        ind = y;
                    }
                }
                topFive.add(temp);
                hlist.remove(ind);
            }
        }
        mainActivityPresenter.updateHexList(topFive);
    }

}


