
/**
 * This class models a door.
 *
 * @author @gcschmit
 * @version 17 September 2018
 */
public class Door
{
    private String name;
    private boolean isOpened;

    /**
     * Constructs a new Door object
     * 
     * @param   initialName         the name of this door
     * @param   initialIsOpened     the state of this door
     *                              (true: opened; false: closed)
     */
    public Door(String initialName, boolean initialIsOpened)
    {
        this.name = initialName;
        this.isOpened = initialIsOpened;
    }

    /**
     * Opens this door.
     *
     */
    public void open()
    {
        this.isOpened = true;
    }
    
    /**
     * Closes this door.
     *
     */
    public void close()
    {
        this.isOpened = false;
    }
    
    /**
     * Returns the name of this door.
     * 
     * @return the name of this door
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Returns whether this door is opened.
     * 
     * @return true if this door is opened; otherwise, false
     */
    public boolean isOpened()
    {
        return this.isOpened;
    }
    
    /**
     * Sets the name of this door to the specified name.
     * 
     * @param   newName     the new name of this door
     */
    public void setName(String newName)
    {
        this.name = newName;
    }
}
