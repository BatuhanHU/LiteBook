import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * makes all user operations
 * @author Batuhan Mete
 * @version 1.0
 */
public class User {
    protected int userID;
    private String password;
    protected String name;
    protected String username;
    private String dateOfBirth;
    protected String schoolGraduated;
    private Date lastLogin = new Date();
    protected boolean signedin = false;
    protected String relationship_status;
    private static int userCounter = 0;
    private ArrayList<User> friends = new ArrayList<User>();
    private ArrayList<User> blockedUsers = new ArrayList<User>();
    protected ArrayList<Post> posts = new ArrayList<Post>();


    public User(String name,String username,String password,String dateOfBirth,String schoolGraduated, String relationship_status){
        this.name = name;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.schoolGraduated = schoolGraduated;
        this.relationship_status = relationship_status;
        userID = ++userCounter;
    }
    @Override
    public String toString(){
        return  "Name: " + name +
                "\nUsername: " + username +
                "\nDate of Birth: " + dateOfBirth +
                "\nSchool: " + schoolGraduated +
                "\n---------------------------";
    }

    /**
     * checks given password
     * @param password user's password
     * @return true if passwords matched else false
     */
    public boolean passwordCheck(String password){
        return this.password.equals(password);
    }

    /**
     * sign in's user
     * @param password user's password
     * @return true if sign in operation is success else false
     */
    public boolean signIn(String password){
        if (passwordCheck(password)){
            signedin = true;
            lastLogin = new Date();
            return true;
        }
        return false;
    }
    /**
     * changes user's some information
     * @param relationship_status users 
     */
    public void updateProfile(String relationship_status){
        this.relationship_status = relationship_status;
    }

    /**
     * changes user's password
     * @param oldPassword old password
     * @param newPassword new password
     * @return true if password matches else false
     */
    public boolean changePass(String oldPassword, String newPassword){
        if(passwordCheck(oldPassword)){
            this.password = newPassword;
            return true;
        }
        return false;
    }

    /**
     * adds persons to the user's friend list and prints information message
     * @param name the name of the person, user wants to add friend
     * @return true if person exists(already friend or not) else false
     */
    public boolean addFriend(String name){
        for (User person:UserCollection.users){
            if(person.username.toLowerCase().equals(name)){
                if(! friends.contains(person)){
                    friends.add(person);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * removes user's friend on friend list
     * @param name the name of the person, user wants to delete friend
     * @return true if friend exists else false
     */
    public boolean removeFriend(String name){
        for (User person:friends){
            if(person.username.equals(name)){
                friends.remove(person);
                return true;
            }
        }
        return false;
    }

    /**
     * adds persons to users blocked lists
     * @param name the name of the person, user wants to block
     * @return true if person exists else false
     */
    public boolean block(String name){
        for (User person:friends) {
            if (person.username.equals(name)) {
                friends.remove(person);
                blockedUsers.add(person);
                return true;
            }
        }
        for (User person:UserCollection.users){
            if(person.name.equals(name)){
                blockedUsers.add(person);
                return true;
            }
        }
        return false;
    }

    /**
     * unblocks blocked persons
     * @param name the name of the person, user wants to unblock
     * @return true if blocked person found, else false
     */
    public boolean unblock(String name){
        for (User person:blockedUsers){
            if (person.username.equals(name)){
                blockedUsers.remove(person);
                return true;
            }
        }
        return false;
    }

    
    public String[] getFriends(){
    	String[] ret = new String[friends.size()];
    	for(int i = 0; i < friends.size();i++){
    		ret[i] = friends.get(i).username;
        }
    	return ret;
    }
    public String[] getBlockedUsers(){
    	String[] ret = new String[blockedUsers.size()];
    	for(int i = 0;i < blockedUsers.size(); i++){
    		ret[i] = blockedUsers.get(i).username;
    	}
    	return ret;
    }
    

    

    /**
     * creates new text post with location and tagged friends
     * @param text post text
     * @param longitude post's longitude location
     * @param latitude post's latitude location
     * @param friends users who wants to tagged by user
     */
    public void addTextPost(String text, double longitude, double latitude,String friends){
        Location location = new Location(latitude,longitude);
        String[] friendList = friends.split(":");
        ArrayList<User> taggedFriends = friendControl(friendList);
        posts.add(new TextPost(text, location, taggedFriends));
    }

    /**
     * creates new image post with location,tagged friends and image
     * @param text post text
     * @param longitude post's longitude location
     * @param latitude post's latitude location
     * @param friends users who wants to be tagged by user
     * @param filepath image's location
     * @param resolution image's location
     */
    public void addImagePost(String text, double longitude, double latitude,String friends, String filepath, String resolution){
        Location location = new Location(latitude,longitude);
        String[] friendList = friends.split(":");
        ArrayList<User> taggedFriends = friendControl(friendList);
        String[] res1 = resolution.split("<x>");
        int[] res2 = new int[2];res2[0] = Integer.valueOf(res1[0]);res2[1] = Integer.valueOf(res1[1]);
        posts.add(new ImagePost(text, location, taggedFriends, filepath, res2));
    }

    /**
     * creates new video post with location,tagged friends and video
     * @param text post text
     * @param longitude post's longitude location
     * @param latitude post's latitude location
     * @param friends users who wants to be tagged by user
     * @param filepath video's location
     * @param duration video's duration
     */
    public void addVideoPost(String text, double longitude, double latitude,String friends, String filepath, int duration){
        if(VideoPost.durationControl(duration)) {
            Location location = new Location(latitude, longitude);
            String[] friendList = friends.split(":");
            ArrayList<User> taggedFriends = friendControl(friendList);
            posts.add(new VideoPost(text, location, taggedFriends, filepath, duration));
        }
        else{
            System.out.println("Error: Your video exceeds maximum allowed duration of " + VideoPost.maxVideoDuration + " minutes.");
        }
    }


    /**
     * checks every user on list, if friend found, adds this user to return list
     * @param friendList list of friends to tag post
     * @return friend array list inculding passed friend control users.
     */
    public ArrayList<User> friendControl(String[] friendList){
        ArrayList<User> taggedFriends = new ArrayList<User>();
        for(int i=0;i < friendList.length;i++){
            boolean isfound = false;
            User pers = null;
            for(User person:UserCollection.users){
                if(person.username.toLowerCase().equals(friendList[i])){
                    isfound = true;
                    pers = person;
                    break;
                }
            }
            if(! isfound){
            }
            else{
                taggedFriends.add(pers);
            }
        }
        return taggedFriends;
    }
    public String getDateofBirth(){
    	return dateOfBirth;
    }
}


