/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA6
 * Date: 4/15/13
 * Time: 8:09 PM
 * Java Class: PACKAGE_NAME
 */
public class ItemNotFoundException extends RuntimeException
{
    /**
     * Construct this exception object.
     */
    public ItemNotFoundException( )
    {
        super( );
    }
    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public ItemNotFoundException( String message )
    {
        super( message );
    }
}
