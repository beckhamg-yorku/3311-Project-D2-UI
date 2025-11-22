package Backend;

public class Admin extends Administrator {

	protected Admin(String email, String password) {
		super(email, password);
	}

	@Override
	public String getAccountType() {
		return "Admin";
	}

}
