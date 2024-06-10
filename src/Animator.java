import java.util.List;

public class Animator extends AbstractUser {

	protected Animator(int id, String name, String surname, String userName, String email, String phoneNumber) {
		super(id, name, surname, userName, email, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	List<Action> getActions() {
		// TODO Auto-generated method stub
		return actions;
	}

	@Override
	void updateActions() {
		// TODO Auto-generated method stub
		
	}

}
