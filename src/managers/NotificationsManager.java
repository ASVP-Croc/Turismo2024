package managers;
import elements.Notification;
import elements.Request;
import users.Action;
import users.AuthenticatedTourist;
import users.AuthorizedContributor;
import users.Contributor;
import users.Curator;
import users.Role;

public class NotificationsManager {
	private final AccountsManager accountManager;
	
	public NotificationsManager(AccountsManager accountManager) {
		this.accountManager = accountManager;
		
	}
	
	public boolean sendRequest (String text, Request request){
			return accountManager.execute(request, new Notification(text));
		}

	public boolean execute(String text,Request request) {
		if(request.getAction()==Action.Post) {
			return sendRequest("La tua richiesta è andata a buon fine e l'elemento è stato pubblicato sulla piattaforma!"+text, request);
		} else if(request.getAction()==Action.Delete) {
			return sendRequest("OPS! Il tuo elemento non ha passato la validazione per il seguente motivo: "+text, request);
		}
		return false;
	}
	
	public boolean execute(Request request) {
		 if(request.getUser().getRole()==Role.AuthenticatedTourist || request.getUser().getRole()==Role.Contributor) {
			 return sendRequest("E' stato creato un novo elemento da validare! Accedi all'area Validazioni per copletare l'operazione.", request);
		 } else return false;
	}
}
