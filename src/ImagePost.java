import java.util.ArrayList;

/**
 * default image post class
 * @author Batuhan Mete
 * @version 1.0
 */
public class ImagePost extends TextPost {
    protected int[] resolution = new int[2];
    protected String imageFilepath;
    public ImagePost(String text, Location location, ArrayList<User> taggedfriends, String filepath, int[] resolution) {
        super(text, location, taggedfriends);
        this.resolution = resolution;
        this.imageFilepath = filepath;
    }
    @Override
    public String toString(){
        return  super.toString() +
                "\nImage: " + imageFilepath +
                "\nImage resolution: " + resolution[0] + "x" + resolution[1];
    }
}
