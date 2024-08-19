package users;
public class AuthenticatedTourist extends AbstractAuthenticatedUser {

	public AuthenticatedTourist(int id, String name, String surname, String userName, String email,
			String phoneNumber) {
		super(id, name, surname, userName, email, phoneNumber);
		super.setRole(Role.AuthenticatedTourist);
		// TODO Auto-generated constructor stub
	}

}
