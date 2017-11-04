import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Collects,adds,removes users; makes not sign in needed operations
 * @author Batuhan Mete
 * @version 1.0
 */
public class UserCollection {
	protected static int SignedInUserId;
    protected static ArrayList<User> users = new ArrayList<User>();

    /**
     * takes user data, convertes date of birth format to date and creates new user
     * @param data Users data array.Format: name,username,password,dateofbirth,schoolgraduated
     * @return true if user created correctly
     */
    public static  boolean AddUser(String[] data){
        users.add(new User(data[0], data[1], data[2], data[3], data[4], data[5]));
        return true;
    }
    public static void AddFriend(String name1, String name2){
        for(User user:users){
            if (user.username.toLowerCase().equals(name1)){
                user.addFriend(name2);
            }
            if (user.username.toLowerCase().equals(name2)){
                user.addFriend(name1);
            }
        }
    }
    public static void Block(String name1, String name2) {
        for(User user:users){
            if (user.username.toLowerCase().equals(name1)){
                user.block(name2);
            }
            if (user.username.toLowerCase().equals(name2)){
                user.block(name1);
            }
        }
    }

    /**
     * removes user
     * @param name users username
     * @return true if user removed else false
     */
    public static boolean Removeuser(String name){
        for (int i=0;i < users.size();i++){
            if(users.get(i).username.equals(name)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * checks username-password correction, makes log in operation and prints informative message.
     * @param username username
     * @param password users password
     * @return true if user is logs in else false
     */
    public static boolean UserSign(String username, String password){
        for (int i = 0; i < users.size();i++){
            if (users.get(i).username.equals(username)){
                if (users.get(i).signIn(password)) {
                	SignedInUserId = i;
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    

    /**
     * checks users sign status
     * @param username username
     * @return users index on arraylist if user signed in else -1
     */
    public static int IsSignedIn(String username){
        for (User user:users){
            if (user.username.equals(username)){
                if(user.signedin){
                    return users.indexOf(user);

                }
                else{
                    return -1;
                }
            }
        }
        return -1;
    }

    /**
     * checks user is exist or not
     * @param name users name
     * @return users index on arraylis if user exist else -1
     */
    public static int isExists(String name){
        for (User user:users){
            if(user.username.equals(name)){
                return users.indexOf(user);
            }
        }
        return -1;
    }

    
    public static String[] getAllUsernames(){
    	String[] userList = new String[users.size()];
    	for(int i=0;i < users.size();i++){
    		userList[i] = users.get(i).username;
    	}
    	return userList;
    }
}
