package managers;
import elements.Request;
import users.AuthenticatedTourist;
import users.AuthorizedContributor;
import users.Contributor;
import users.Curator;

public class NotificationManager {
	
	public void sendMessage (Request request){
		if (request.getUser() instanceof Contributor || request.getUser() instanceof AuthenticatedTourist) {
			System.out.println("Il " + request.getAction()+ " è stato completato ed è in attesa di validazione.");
		} else if (request.getUser() instanceof AuthorizedContributor || request.getUser() instanceof Curator) {
			System.out.println("Il " + request.getAction() + " creato con successo e pubblicato sulla piattaforma");
			} else System.out.println("Elemento/Contenuto creato con successo!");
		}
}
