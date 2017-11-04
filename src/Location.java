/**
 * default location class.
 * @author Batuhan Mete
 * @version 1.0
 */
public class Location {
    protected double latitude;
    protected double longitude;

    public Location(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    @Override
    public String toString(){
        return longitude + ", " + latitude;
    }
}
