import java.util.ArrayList;

/**
 * default video post class
 * @author Batuhan Mete
 * @version 1.0
 */
public class VideoPost extends TextPost {
    protected static int maxVideoDuration = 100000;
    protected String videoFilepath;
    protected int duration;
    public VideoPost(String text, Location location, ArrayList<User> taggedfriends, String videoFilepath, int duration) {
        super(text, location, taggedfriends);
        this.videoFilepath = videoFilepath;
        this.duration = duration;
    }

    /**
     * checks video's duration
     * @param duration video's duration
     * @return true if video duration is below the max video duration value else false
     */
    public static boolean durationControl(int duration){
        return (maxVideoDuration > duration);
    }
    @Override
    public String toString(){
        return  super.toString() +
                "\nVideo: " + videoFilepath +
                "\nVideo duration: " + duration + " minutes";
    }
}
