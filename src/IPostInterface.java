import java.util.Date;
import java.util.UUID;

/**
 * default post interface
 * @author Batuhan Mete
 * @version 1.0
 */
public interface IPostInterface {
    void setText(String n);
    String getText();
    UUID getID();
    Date getDate();

}
