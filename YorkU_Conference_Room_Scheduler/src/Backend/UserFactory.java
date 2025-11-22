package Backend;
public class UserFactory {

	public User createUser(String email, String password, String type, String orgId) {
		
		if (type.equals("Student"))
			return new Student(email, password, orgId);
		else if (type.equals("Staff"))
			return new Staff(email, password, orgId);
		else if (type.equals("Faculty"))
			return new Faculty(email, password, orgId);
		else if (type.equals("External Partner"))
			return new ExternalPartner(email, password, orgId);
		else
			throw new NullPointerException("Type can't be null");
		
	}
	
}
