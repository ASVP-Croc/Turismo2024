package users;
public class AuthorizedContributor extends Contributor {

	public AuthorizedContributor(int id, String name, String surname, String userName, String email,
			String phoneNumber) {
		super(id, name, surname, userName, email, phoneNumber);
		super.setRole(Role.AuthorizedContributor);
		// TODO Auto-generated constructor stub
	}

}
