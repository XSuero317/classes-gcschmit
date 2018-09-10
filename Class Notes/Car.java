
/**
 * This class models a car.
 *
 * @author gcschmit
 * @version 10 September 2018
 */
public class Car
{
    /*
     * Instance variables store the object's properties
     *      specify the visibility (e.g., private)
     *          public: accessible by anyone
     *          private: only accessible by methods in this class
     *      specify the type (e.g., double)
     *      specify the name (e.g., fuelEfficienc, fuelInTank)
     *  
     *  Instance variables differ from local variables in the
     *      following ways:
     *      scoped to their class (accessible for all methods)
     *      automatically initialized to a default value (0, false, null)
     */
    private double fuelEfficiency; // in units of miles per gallon
    private double fuelInTank; // in units of gallons
    
    /*
     * When defining a method, specify:
     *      the visibility (e.g., public)
     *      the return type (e.g., void)
     *      the method name (e.g., drive)
     *      the parameters and their types (e.g., distance
     *          of type double)
     */
    
    /**
     * Drives this car the specified distance, consuming fuel
     * 
     * @param   distance    the distance, in miles, this car drives
     */
    public void drive(double distance)
    {
    }
    
    /**
     * Adds the specified amount of fuel to this car's tank
     * 
     * @param   amount  the amount of fuel, in gallons, to add to
     *                  this car's tank
     */
    public void addFuel(double amount)
    {
    }
    
    /**
     * Returns the amount of fuel in this car's tank
     * 
     * @return the amount of fuel, in gallons, in this car's tank
     */
    public double getFuelInTank()
    {
        return 0;
    }
}
