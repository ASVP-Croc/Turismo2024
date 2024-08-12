package managers;

import elements.Request;
import users.Action;

public class NotificationsManager {
	
	private static boolean sendRequest (Request request, String text, Integer id){
			return true;//AccountsManager.execute(request, new Notification(text), id);
		}

	public static boolean execute(Request request, String text, Integer id) {
		if(request.getAction()==Action.Post) {
			return sendRequest(request, "La tua richiesta è andata a buon fine e l'elemento è stato pubblicato sulla piattaforma!", id);
		} else if(request.getAction()==Action.Delete) {
			return sendRequest(request, "OPS! Il tuo elemento non ha passato la validazione per il seguente motivo: "+text, id);
		}
		return false;
	}

	public static boolean execute(Request request, String text) {
		return true;// AccountsManager.execute(request,new Notification(text));
	}
}
