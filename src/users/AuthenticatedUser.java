package users;

public class AuthenticatedUser extends AbstractAuthenticatedUser {

	public AuthenticatedUser(String name, String surname, String userName, String email,String phoneNumber) {
		super(name, surname, userName, email, phoneNumber);
	}

}
