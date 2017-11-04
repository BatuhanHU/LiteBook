import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * default text post class.
 * @author Batuhan Mete
 * @version 1.0
 */
public class TextPost extends Post{
    public TextPost(String text, Location location, ArrayList<User> taggedfriends){
        this.text = text;
        this.location = location;
        this.taggedFriends = taggedfriends;
    }

    @Override
    public void setText(String n) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public UUID getID() {
        return postID;
    }

    @Override
    public Date getDate() {
        return date;
    }

    /**
     * shows tagged friends
     * @return tagged friends with ready to print string format
     */
    public String  showTaggedFriends(){
        String out = "";
        if(taggedFriends.size() != 0){
            out = "\nFriends tagged in this post:";
            for(User person:taggedFriends) {
                out += person.name + " ";
            }
        }
        return out;
    }
    @Override
    public String toString(){
        return  text +
                "\nDate: " + date +
                "\nLocation: " + location +
                showTaggedFriends();

    }

}
