package models;
public class Tenant {
    
    private String name;
    private String email;
    private String phone;
    private String address;
    private String unit;
    private String leaseStart;
    private String leaseEnd;

    /**
     * This is the constructor for the tenant information
     * 
     * @param name
     * @param email
     * @param phone
     * @param address
     * @param unit
     * @param leaseStart
     * @param leaseEnd
     */
    public Tenant(String name, String email, String phone, String address, String unit, 
                  String leaseStart, String leaseEnd){
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.address=address;
        this.unit=unit;
        this.leaseStart=leaseStart;
        this.leaseEnd=leaseEnd;
    }

    //Getter Methods
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getAddress(){
        return this.address;
    }
    public String getUnit(){
        return this.unit;
    }
    public String getLeaseStart(){
        return this.leaseStart;
    }
    public String getLeaseEnd(){
        return this.leaseEnd;
    }

    //Setter methods
    public void setName(String name){
        this.name=name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setUnit(String unit){
        this.unit=unit;
    }
    public void setLeaseStart(String leaseStart){
        this.leaseStart=leaseStart;
    }
    public void setLeaseEnd(String leaseEnd){
        this.leaseEnd=leaseEnd;
    }
    /**
     * This is the toString method to print the information of the tenant
     * 
     * @return the information
     */
    @Override
    public String toString(){
        String message = "Name: "+name+ " | Email: "+email+" | phone: "+phone+
            " | unit number: "+unit+" | Start of Lease: "+leaseStart+
            " | End of Lease: "+leaseEnd;
        return message;
    }
}
