
public class NotificationManager {
	
	public void sendMessages (AbstractUser user){
		if (user instanceof Contributor || user instanceof AuthenticatedTourist) {
			System.out.println("Elemento/Contenuto creato con successo!");
		} else if (user instanceof AuthorizedContributor || user instanceof Curator) {
			System.out.println("Elemento/Contenuto creato con successo!");
			} else System.out.println("Elemento/Contenuto creato con successo!");
		}
}
