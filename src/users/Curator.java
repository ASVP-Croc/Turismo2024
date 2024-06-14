package users;
public class Curator extends AbstractUser {

	public Curator(int id, String name, String surname, String userName, String email, String phoneNumber) {
		super(id, name, surname, userName, email, phoneNumber);
		super.setRole(Role.Curator);
		// TODO Auto-generated constructor stub
	}

}
