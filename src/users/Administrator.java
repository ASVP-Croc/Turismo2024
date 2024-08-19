package users;
public class Administrator extends AbstractAuthenticatedUser {

	protected Administrator(int id, String name, String surname, String userName, String email, String phoneNumber) {
		super(id, name, surname, userName, email, phoneNumber);
		super.setRole(Role.Administrator);
		// TODO Auto-generated constructor stub
	}

}
