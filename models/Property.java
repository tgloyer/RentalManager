package models;
public class Property {

    //variables for the class
    private String address;
    private double rentAmount;
    private int numberOfUnits;
    private int availableUnits;

    /**
     * This method is a constructor that sets the values of the object
     * 
     * @param address
     * @param rentAmount
     * @param numberOfUnits
     */
    public Property(String address, double rentAmount, int numberOfUnits, 
        int availableUnits){
        this.address = address;
        this.rentAmount = rentAmount;
        this.numberOfUnits = numberOfUnits;
        this.availableUnits=availableUnits;
    }

    /**
     * this method gets the address
     * 
     * @return address
     */
    public String getAddress(){
        return this.address;
    }

    /**
     * This method gets the rent amount
     * 
     * @return rent amount
     */
    public double getRentAmount(){
        return this.rentAmount;
    }

    /**
     * This method gets the number of units
     * 
     * @return number of units
     */
    public int getNumberOfUnits(){
        return this.numberOfUnits;
    }

    /**
     * This method returns available units
     * 
     * @return available units
     */
    public int getAvailableUnits(){
        return availableUnits;
    }

    /**
     * This sets address
     * @param address
     */
    public void setAddress(String address){
        this.address=address;
    }

    /**
     * This sets rent amount
     * @param rentAmount
     */
    public void setRentAmount(double rentAmount){
        this.rentAmount = rentAmount;
    }

    /**
     * This sets number of Units
     * @param numberOfUnits
     */
    public void setNumberOfUnits(int numberOfUnits){
        this.numberOfUnits = numberOfUnits;
    }

    /**
     * This sets available Units
     * @param availableUnits
     */
    public void setAvailableUnits(int availableUnits){
        this.availableUnits = availableUnits;
    }


    /**
     * This is the toString method that prints the information
     * 
     * @return the printed message of the property information
     */
    @Override
    public String toString() {
        String message = "Property at: "+address+
                         " | Rent: $"+rentAmount+
                         " | Total Units: "+numberOfUnits+
                         " | Units Available: "+availableUnits;
        return message;
    }
}
