import java.io.FileWriter;
import java.util.UUID;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class UserCSV {
	
    private static UserCSV instance = new UserCSV();
	private final String PATH = "../Database.csv";
		
    private UserCSV(){ 
    	
    	try {
    		
			CsvWriter csvWrite = new CsvWriter(new FileWriter(PATH, true), ',');
			csvWrite.write("ID");
			csvWrite.write("Type");
			csvWrite.write("Org ID");
			csvWrite.write("Email");
			csvWrite.write("Password");
			csvWrite.write("Date Created");
			csvWrite.endRecord();
			csvWrite.close();
			
	    	this.write(ChiefEventCoordinator.getCEOInstance());

    	}
		catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    }
    
    public static UserCSV getInstance() {
    	return instance;
    }

    
    public void write(Accounts a) throws Exception {
    	
    	try {
			CsvWriter csvWrite = new CsvWriter(new FileWriter(PATH, true), ',');
			csvWrite.write(String.valueOf(a.getAccountId()));
			csvWrite.write(a.getAccountType());
			csvWrite.write((a instanceof User)? ((User) a).getOrgID():"-");
			csvWrite.write(a.getEmail());
			csvWrite.write(a.getPassword());
			csvWrite.write(String.valueOf(a.getCreatedDate()));
			csvWrite.endRecord();
			csvWrite.close();
    	}
		catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public Accounts find (UUID id) {
    	Accounts account;
        try {
            CsvReader csvRead = new CsvReader(PATH);
            csvRead.readHeaders(); // Load headers
            
            while (csvRead.readRecord()) {
                if (csvRead.get("ID").equals(String.valueOf(id))) {

                	if (csvRead.get("Type").equals("Admin")) {	
                		account = new Admin (csvRead.get("Email"), csvRead.get("Password")); 
                	}
                	else if (csvRead.get("Type").equals("Chief Event Coordinator")){
                		return ChiefEventCoordinator.getCEOInstance();
                	}
                	else {
                		UserFactory factory = new UserFactory();
                		account = factory.createUser(csvRead.get("Email"), csvRead.get("Password"), csvRead.get("Type"), csvRead.get("Org ID"));
                	}

                    csvRead.close();
                    return account;
                }
            }
            csvRead.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    	
    }
    
}
