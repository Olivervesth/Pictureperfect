package dk.picture.myapplication;

public class Hex implements Comparable<Hex> {
    String hexcode;
    int amount;

    /**
     * Constructor*/
    public Hex(String hexcode, int amount){
        this.amount = amount;
        this.hexcode = hexcode;
    }

    /**
     * Check's the difference between two hex*/
    @Override
    public int compareTo(Hex hex) {
        if (this.amount > hex.amount) {
            // if current object is greater,then return 1
            return 1;
        }
        else if (this.amount < hex.amount) {
            // if current object is greater,then return -1
            return -1;
        }
        else {
            // if current object is equal to o,then return 0
            return 0;
        }
    }
}
