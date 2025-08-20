package services;
import models.Property;
import models.Tenant;
import models.Payment;
import java.util.*;
import java.io.*;

public class RentalService {

    private ArrayList<Property> properties = new ArrayList<>();
    private ArrayList<Tenant> tenants = new ArrayList<>();
    private ArrayList<Payment> payments = new ArrayList<>();

    /**
     * Adds a property to the list
     * 
     * @param property
     */
    public void addProperty(Property property){
        properties.add(property);
    }
    
    /**
     * removes a property from the list
     * 
     * @param property
     */
    public void removeProperty(Property property){
        properties.remove(property);
    }

    /**
     * This method removes the address from the property list
     * 
     * @param data
     */
    public void removeAddress(String data){
        for(int i=0;i<properties.size();i++){
            if(properties.get(i).getAddress().trim().equalsIgnoreCase(data)){
                removeProperty(properties.get(i));
                System.out.println("Entry successfully removed.");
                return;
            }
        }
        System.out.println("Unable to remove entry. Address not found.");
    }

    /**
     * lists the properties in the list
     */
    public void listProperties(){
        if(properties.size()==0){
            System.out.println("No properties added yet.");
        }
        else{
            for(int i = 0;i<properties.size();i++){
                System.out.println(properties.get(i));
            }
        }
    }

    /**
     * This adds a tenant to the list
     * 
     * @param newTenant
     */
    public boolean addTenant(Tenant tenant){
        for(int i=0;i<tenants.size();i++){
            if(tenants.get(i).getAddress().trim().equalsIgnoreCase(tenant.getAddress())&&
                tenants.get(i).getUnit().trim().equals(tenant.getUnit())){
                    System.out.println("This unit is already occupied, try adding tenant to another unit.");
                    return false;
            }
        }
        for(int i=0;i<properties.size();i++){
            if(properties.get(i).getAddress().trim().equalsIgnoreCase(tenant.getAddress())){
                if(properties.get(i).getAvailableUnits()<=0){
                    System.out.println("There are no available units at this address.");
                    return false;
                }
                else{
                    properties.get(i).setAvailableUnits(properties.get(i).getAvailableUnits()-1);
                    tenants.add(tenant);
                    return true;
                }
            }
        }
        System.out.println("Property of tenant does not exist. First add the property then add the tenant");
        return false;
    }

      /**
     * lists the tenants in the list
     */
    public void listTenants(){
        if(tenants.size()==0){
            System.out.println("No tenants added yet.");
        }
        for(int i = 0;i<tenants.size();i++){
            System.out.println(tenants.get(i));
        }
    }

     /**
     * This method removes the tenant from the tenant list
     * 
     * @param data
     */
    public void removeTenant(String address, String unit, String name){
        for(int i=0;i<tenants.size();i++){
            if(tenants.get(i).getAddress().trim().equalsIgnoreCase(address)&&
                tenants.get(i).getUnit().trim().equals(unit)&&
                tenants.get(i).getName().trim().equalsIgnoreCase(name)){
                    tenants.remove(tenants.get(i));
                    System.out.println("Entry successfully removed.");
                    for(int j=0;j<properties.size();j++){
                        if(properties.get(j).getAddress().trim().equalsIgnoreCase(address)){
                            properties.get(j).setAvailableUnits(properties.get(j).getAvailableUnits()+1);
                        }
                    }
                    return;
            }
        }
        System.out.println("There is no tenant ("+name+") at ("+address+") unit number ("+unit+").");
        System.out.println("Try listing the tenants and finding the correct name, address, and unit number.");
    }

    /**
     * Adds a payment to the list
     * 
     * @param payment
     * @return true if added payment
     */
    public boolean addPayment(Payment payment){
        for(int i=0;i<tenants.size();i++){
            if(tenants.get(i).getName().trim().equalsIgnoreCase(payment.getTenantName())){
                    payments.add(payment);
                    System.out.println("Payment for "+payment.getTenantName()+" successfully paid $"+
                        payment.getAmountPaid()+" on "+payment.getPaymentDate());
                    return true;
            }
        }
        System.out.println("No tenant by this name. First add the tenant then add the payment.");
        return false;
    }
    
    /**
     * lists the Payments in the list
     */
    public void listPayments(){
        if(payments.size()==0){
            System.out.println("No payments added yet.");
        }
        for(int i = 0;i<payments.size();i++){
            System.out.println(payments.get(i));
        }
    }

    /**
     * lists the late payments in the list
     */
    public void listLatePayments(){
        int count=0;
        if(payments.size()==0){
            System.out.println("No payments added yet.");
        }
        for(int i = 0;i<payments.size();i++){
            if(payments.get(i).getIsLate()){
                System.out.println(payments.get(i));
                count++;
            }
        }
        if(count==0){
            System.out.println("There have been no late payments.");
        }
    }

    /**
     * This method removes the payment from the payment list
     * 
     * @param name
     * @param address
     * @param date
     */
    public void removePayment(String name, String address, String date){
        for(int i=0;i<payments.size();i++){
            if(payments.get(i).getTenantName().trim().equalsIgnoreCase(name)&&
                payments.get(i).getPropertyAddress().trim().equalsIgnoreCase(address)&&
                payments.get(i).getPaymentDate().trim().equalsIgnoreCase(date)){
                    payments.remove(payments.get(i));
                    System.out.println("Payment successfully removed.");
                    return;
            }
        }
        System.out.println("There is no tenant ("+name+") at ("+address+") that paid on ("+date+").");
        System.out.println("Try listing the payments and finding the correct name, address, and date.");
    }

    /**
     * This method sums up the total amount paid
     * 
     * @param address
     */
    public void totalPayments(String address){
        double sum=0;
        for(int i=0;i<payments.size();i++){
            if(payments.get(i).getPropertyAddress().trim().equalsIgnoreCase(address)){
                sum+=payments.get(i).getAmountPaid();
            }
        }
        if(sum==0){
            System.out.println("No rent collected yet.");
            return;
        }
        System.out.println("Total rent collected from "+address+" is: $"+sum);
    }

    /**
     * Method for saving to file
     * 
     * @param filename
     */
    public void saveData(String filename){
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))){
            Property p;
            Payment s;
            Tenant t;
            for(int i=0;i<properties.size();i++){
                p=properties.get(i);
                out.println("PROPERTY,"+p.getAddress()+","+p.getRentAmount()+","
                    + p.getNumberOfUnits()+","+ p.getAvailableUnits());
            }
            for(int i=0;i<payments.size();i++){
                s=payments.get(i);
                out.println("PAYMENT,"+s.getTenantName()+","+s.getPropertyAddress()+
                    ","+ s.getAmountPaid()+","+s.getPaymentDate()+","+s.getIsLate());
            }       
            for(int i=0;i<tenants.size();i++){
                t=tenants.get(i);
                out.println("TENANT,"+t.getName()+","+t.getEmail()+","+ 
                    t.getPhone()+","+t.getAddress()+","+t.getUnit()+","+
                    t.getLeaseStart()+","+t.getLeaseEnd());
            }
        }
        catch (IOException e){
            System.out.println("Error saving data.");
        }
    }

    /**
     * This method loads the existing properties from the file
     * 
     * @param filename
     */
    public boolean loadData(String filename){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String currLine;
            while((currLine = reader.readLine())!=null){
                String[] parts = currLine.split(",");
                if (parts.length==0) continue;
                if (parts[0].equals("PROPERTY")){
                    String address = parts[1];
                    double rent=Double.parseDouble(parts[2]);
                    int units = Integer.parseInt(parts[3]);
                    int availableUnits = Integer.parseInt(parts[4]);
                    properties.add(new Property(address, rent, units,availableUnits));
                }
                else if(parts[0].equals("PAYMENT")){
                    String tenantName = parts[1];
                    String propertyAddress=parts[2];
                    double amountPaid=Double.parseDouble(parts[3]);
                    String paymentDate=parts[4];
                    boolean isLate = Boolean.parseBoolean(parts[5]);
                    payments.add(new Payment(tenantName,propertyAddress,
                                            amountPaid,paymentDate,isLate));
                }
                else if(parts[0].equals("TENANT")){
                    String name = parts[1];
                    String email=parts[2];
                    String phone=parts[3];
                    String address=parts[4];
                    String unit=parts[5];
                    String leaseStart=parts[6];
                    String leaseEnd=parts[7];
                    tenants.add(new Tenant(name,email,phone,address,unit,leaseStart,leaseEnd));
                }
                else{
                    System.out.println("Unknown record: "+parts[0]);
                }
            }
            reader.close();
            System.out.println("Data successfully loaded from "+filename);
            return true;
        }
        catch(IOException e) {
            System.out.println("Error loading data: "+e.getMessage()+". Make sure to include the entire filename.");
            return false;
        }
    }

    
}
