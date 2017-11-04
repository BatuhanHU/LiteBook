import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * default post class
 * @author Batuhan Mete
 * @version 1.0
 */
public abstract class Post implements IPostInterface {
    protected UUID postID = UUID.randomUUID();
    protected String text;
    protected Date date = new Date();
    protected ArrayList<User> taggedFriends = new ArrayList<User>();
    protected Location location;
}
