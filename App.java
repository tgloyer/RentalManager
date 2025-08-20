import java.util.Scanner;
import services.RentalService;
import models.Property;
import models.Tenant;
import models.Payment;
import java.io.*;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        RentalService rentalService = new RentalService();
        
        System.out.println("Welcome to the Rental Manager!");
        System.out.println("Enter filename (rental_data.txt) to load existing "+ 
                            "portfolio or type NEW for new portfolio: ");
        String filename = scan.nextLine().trim().toLowerCase();
        if(filename.equalsIgnoreCase("NEW")){
            System.out.println("What would you like to name your portfolio?");
            filename=scan.nextLine().trim()+".txt";
            rentalService.saveData(filename);
            System.out.println("New portfolio created: "+filename);
        }
        else{
            boolean success = rentalService.loadData(filename);
            if(!success){
                while(!success){
                    System.out.println("You can create a new file by typing NEW or try entering another file name (rental_data.txt).");
                    filename=scan.nextLine().trim();
                    if(filename.equalsIgnoreCase("NEW")){
                        System.out.println("What would you like to name your portfolio?");
                        filename=scan.nextLine().trim()+".txt";
                        rentalService.saveData(filename);
                        System.out.println("New portfolio created: "+filename);
                        break;
                    }
                    success= rentalService.loadData(filename);
                }
            }
        }
        boolean running = true;
        while (running){
            System.out.println("1. Add/Remove Property");
            System.out.println("2. List Properties");
            System.out.println("3. Add/Remove Tenant");
            System.out.println("4. List Tenants");
            System.out.println("5. Record/Remove Rent Payment");
            System.out.println("6. List Payments");
            System.out.println("7. Show Late Payments");
            System.out.println("8. Total Rent Collected");
            System.out.println("0. Exit");
            System.out.print("Enter the number for action: ");

            int action = scan.nextInt();
            scan.nextLine();

            //exit
            if(action==0){
                System.out.println("Thank you have a nice day.");
                break;
            }
            //Add/remove Property
            if(action== 1){
                System.out.println("1. Add Property");
                System.out.println("2. Remove Property");
                System.out.println("0. Go back to main menu");
                System.out.print("Enter the number for action: ");
                int secondaryAction = scan.nextInt();
                scan.nextLine();
                //add property
                if(secondaryAction==1){
                    System.out.print("Enter address to add: ");
                    String address = scan.nextLine();
                    System.out.print("Enter rent amount: ");
                    double rent = scan.nextDouble();
                    System.out.print("Enter number of units: ");
                    int units = scan.nextInt();
                    System.out.print("Enter number of available units: ");
                    int availableUnits = scan.nextInt();
                    scan.nextLine();
                    Property property = new Property(address, rent, units, availableUnits);
                    rentalService.addProperty(property);
                    rentalService.saveData(filename);
                    System.out.println("Data saved to "+filename);
                    System.out.println("Is there anything else you would like to do? YES/NO");
                    String choice = scan.nextLine();
                    if(choice.equalsIgnoreCase("NO")){
                        break;
                    }
                }
                //remove property
                else if(secondaryAction==2){
                    System.out.print("Enter address to remove: ");
                    String address = scan.nextLine();
                    rentalService.removeAddress(address);
                    rentalService.saveData(filename);
                    System.out.println("Is there anything else you would like to do? YES/NO");
                    String choice = scan.nextLine();
                    if(choice.equalsIgnoreCase("NO")){
                        break;
                    }
                }
                else{
                    continue;
                }
            }

            //List Properties
            if(action==2){
                rentalService.listProperties();
                System.out.println("Is there anything else you would like to do? YES/NO");
                String choice = scan.nextLine();
                if(choice.equalsIgnoreCase("NO")){
                    break;
                }
            }

            //Add/remove Tenant
            if(action==3){
                System.out.println("1. Add Tenant");
                System.out.println("2. Remove Tenant");
                System.out.println("0. Go back to main menu");
                System.out.print("Enter the number for action: ");
                int secondaryAction = scan.nextInt();
                scan.nextLine();
                //add tenant
                if(secondaryAction==1){
                    System.out.print("Enter tenant name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter email: ");
                    String email = scan.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scan.nextLine();
                    System.out.print("Enter address: ");
                    String address = scan.nextLine();
                    System.out.print("Enter unit number: ");
                    String unit = scan.nextLine();
                    System.out.print("Enter lease start date: ");
                    String leaseStart = scan.nextLine();
                    System.out.print("Enter lease end date: ");
                    String leaseEnd = scan.nextLine();
                    Tenant tenant = new Tenant(name,email,phone,address,unit,leaseStart,leaseEnd);
                    if(rentalService.addTenant(tenant)){
                        rentalService.saveData(filename);
                        System.out.println("Data saved to "+filename);
                    }
                    System.out.println("Is there anything else you would like to do? YES/NO");
                    String choice = scan.nextLine();
                    if(choice.equalsIgnoreCase("NO")){
                        break;
                    }
                }
                //remove tenant
                else if(secondaryAction==2){
                    System.out.print("Enter address to remove tenant from: ");
                    String address = scan.nextLine();
                    System.out.print("Enter unit number to remove tenant from: ");
                    String unit=scan.nextLine();
                    System.out.print("Enter tenant name: ");
                    String name=scan.nextLine();
                    rentalService.removeTenant(address, unit, name);
                    rentalService.saveData(filename);
                    System.out.println("Is there anything else you would like to do? YES/NO");
                    String choice = scan.nextLine();
                    if(choice.equalsIgnoreCase("NO")){
                        break;
                    }
                }
                else{
                    continue;
                }
            }

            //List Tenants
            if(action==4){
                rentalService.listTenants();
                System.out.println("Is there anything else you would like to do? YES/NO");
                String choice = scan.nextLine();
                if(choice.equalsIgnoreCase("NO")){
                    break;
                }
            }

            //Record/remove rent payment 
            if(action==5){
                System.out.println("1. Record Payment");
                System.out.println("2. Remove Payment");
                System.out.println("0. Go back to main menu");
                System.out.print("Enter the number for action: ");
                int secondaryAction = scan.nextInt();
                scan.nextLine();
                //record payment
                if(secondaryAction==1){
                    System.out.print("Enter tenant name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter property address: ");
                    String address = scan.nextLine();
                    System.out.print("Enter amount paid: ");
                    double amount = scan.nextDouble();
                    scan.nextLine();
                    System.out.print("Enter payment date: ");
                    String date = scan.nextLine();
                    System.out.println("Was the payment late? YES/NO");
                    String answer=scan.nextLine();
                    boolean isLate=false;
                    if(answer.trim().equalsIgnoreCase("YES")){
                        isLate=true;
                    }
                    Payment payment = new Payment(name,address,amount,date,isLate);
                    if(rentalService.addPayment(payment)){
                        rentalService.saveData(filename);
                        System.out.println("Data saved to "+filename);
                    }
                    System.out.println("Is there anything else you would like to do? YES/NO");
                    String choice = scan.nextLine();
                    if(choice.equalsIgnoreCase("NO")){
                        break;
                    }
                }
                //remove payment
                else if(secondaryAction==2){
                    System.out.print("Enter name the payment is under: ");
                    String name = scan.nextLine();
                    System.out.print("Enter address of payment: ");
                    String address=scan.nextLine();
                    System.out.print("Enter payment date: ");
                    String date=scan.nextLine();
                    rentalService.removePayment(name, address, date);
                    rentalService.saveData(filename);
                    System.out.println("Is there anything else you would like to do? YES/NO");
                    String choice = scan.nextLine();
                    if(choice.equalsIgnoreCase("NO")){
                        break;
                    }
                }
                else{
                    continue;
                }
            }

            //List payments
            if(action==6){
                rentalService.listPayments();
                System.out.println("Is there anything else you would like to do? YES/NO");
                String choice = scan.nextLine();
                if(choice.equalsIgnoreCase("NO")){
                    break;
                }
            }

            //List late payments
            if(action==7){
                rentalService.listLatePayments();
                System.out.println("Is there anything else you would like to do? YES/NO");
                String choice = scan.nextLine();
                if(choice.equalsIgnoreCase("NO")){
                    break;
                }
            }

            //List late payments
            if(action==8){
                System.out.print("Enter address to see total rent collected:");
                String address = scan.nextLine();
                rentalService.totalPayments(address);
                System.out.println("Is there anything else you would like to do? YES/NO");
                String choice = scan.nextLine();
                if(choice.equalsIgnoreCase("NO")){
                    break;
                }
            }

        }
        scan.close();
    }
}
