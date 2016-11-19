
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 *
 * @author Fahad Satti
 */
public class ToyStopInventoryManagementSystem {
    ToyStopService tsService = new ToyStopService();
    public void init(){
        
        tsService.initEmployees();
        tsService.initStores();
        tsService.initToys();
        System.out.println("Init complete");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        ToyStopInventoryManagementSystem tsims = new ToyStopInventoryManagementSystem();
        tsims.init();
        
        //load previous data
        try {
			tsims.loadData(tsims);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        tsims.showMenu();
        //tsims.printAll();
        
        Scanner in = new Scanner(System.in);
        int choice= in.nextInt();
        
        if(choice==1){
        	tsims.printAll();
        	}
        
        else if(choice==2){
        	tsims.tsService.addStore();
        	}
        
        else if(choice==3){
        	tsims.tsService.addEmployee();
        	}
        
        else if(choice==0){
        	try {
				tsims.saveData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	}
        
        //tsims.printAll();
        
    }
    
    public  void saveData()throws IOException {
    	
        System.out.println("After Deserialization: " + tsService);
        FileOutputStream fileOut = new FileOutputStream("employee.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(tsService);
        
        out.close();
        fileOut.close();
        System.out.printf("Serialized data is saved in employee.ser");
   }
    
    public void loadData(ToyStopInventoryManagementSystem tsims) throws ClassNotFoundException{
         try  
        {
        	 
           FileInputStream fis = new FileInputStream("employee.ser");
           ObjectInputStream in = new ObjectInputStream(fis);
           tsService = (ToyStopService)in.readObject();
           System.out.println("After deserialization: " + tsService);
           in.close();
           fis.close();
        } 
        catch (IOException i){
            

        
        }catch (ClassNotFoundException i){
            tsims.init();
        }
    }

    private void showMenu() {
        System.out.println("Welcome to Toy Stop Inventory Management System");
        System.out.println("Enter 1 to show all data");
        System.out.println("Enter 2 to add a new Store");
        System.out.println("Enter 3 to add a new Employee");
        System.out.println("Enter 4 to add a new Toy");
        System.out.println("Enter 0 to save state");
        
    }

    private void printAll() {
        System.out.println(this.tsService.stores);
        
    }
    
}
