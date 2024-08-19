package users;
public class Animator extends AbstractAuthenticatedUser {

	public Animator(int id, String name, String surname, String userName, String email, String phoneNumber) {
		super(id, name, surname, userName, email, phoneNumber);
		super.setRole(Role.Animator);
		// TODO Auto-generated constructor stub
	}

}
