public class Faculty extends User{

	private String facultyId;
	private final double HOURLY_RATE = 30.0;

	public Faculty(String email, String password, String facultyId) {
		super(email, password);
		this.setFacultyId(facultyId);
		this.setVerified(true);
	}

	@Override
	public double getHourlyRate() {
		return HOURLY_RATE;
	}

	@Override
	public String getAccountType() {
		return "Faculty";
	}

	@Override
	public boolean requiresVerfication() {
		return false;
	}

	protected void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}

	@Override
	public String getOrgID() {
		return facultyId;
	}

}
