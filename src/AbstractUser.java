
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUser {
	private final int id;

	private final String name;

	private final String surname;

	private String userName;

	private final String email;

	private final String phoneNumber;

	protected List<Action> actions;

	protected AbstractUser(int id, String name, String surname, String userName, String email, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.actions = new ArrayList<>();
	}

	abstract List<Action> getActions();
	
	abstract void updateActions();

}