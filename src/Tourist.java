import java.util.List;

public class Tourist extends AbstractUser {

	protected Tourist() {
		super(1, null, null, null, null, null);
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
