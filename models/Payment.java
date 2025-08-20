package models;
public class Payment {
    private String tenantName;
    private double amountPaid;
    private String paymentDate;
    private boolean isLate;
    private String propertyAddress;

    /**
     * This is the constructor that sets the values of the specified information
     * 
     * @param tenantName
     * @param amountPaid
     */
    public Payment(String tenantName, String propertyAddress,
                    double amountPaid, String paymentDate, boolean isLate){
        this.tenantName = tenantName;
        this.amountPaid = amountPaid;
        this.propertyAddress = propertyAddress;
        this.paymentDate = paymentDate;
        this.isLate = isLate;
    }

    //Getter methods
    public String getTenantName(){
        return this.tenantName;
    }
    public String getPropertyAddress(){
        return this.propertyAddress;
    }
    public double getAmountPaid(){
        return this.amountPaid;
    }
    public String getPaymentDate(){
        return this.paymentDate;
    }
    public boolean getIsLate(){
        return this.isLate;
    }

    //Setter methods
    public void setTenantName(String tenantName){
        this.tenantName=tenantName;
    }
    public void setAmountPaid(double amountPaid){
        this.amountPaid=amountPaid;
    }
    public void setPaymentDate(String paymentDate){
        this.paymentDate=paymentDate;
    }
    public void setIsLate(boolean isLate){
        this.isLate = isLate;
    }

    /**
     * This is the toString method to print the resulting variables
     * 
     * @return the variables.
     */
    @Override
    public String toString() {
        String lateMessage;
        if(isLate){
            lateMessage = " | (Late)";
        }
        else {
            lateMessage = " | (On time)";
        }
        String message = "Payment by " + tenantName + " | Amount: $"+amountPaid
            +" | Paid on: "+paymentDate +lateMessage;
        return message;
    }

}
