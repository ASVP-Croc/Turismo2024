package users;
public class Contributor extends AbstractAuthenticatedUser {

	public Contributor(int id, String name, String surname, String userName, String email, String phoneNumber) {
		super(id, name, surname, userName, email, phoneNumber);
		super.setRole(Role.Contributor);
		// TODO Auto-generated constructor stub
	}
}
