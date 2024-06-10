import java.util.List;

public class Contributor extends AbstractUser {

	protected Contributor(int id, String name, String surname, String userName, String email, String phoneNumber) {
		super(id, name, surname, userName, email, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	List<Action> getActions() {
		return actions;
	}

	@Override
	void updateActions() {
		actions.add(Action.CreatePOI);
		actions.add(Action.CreateTour);
		actions.add(Action.CreateContent);
		
	}
}
