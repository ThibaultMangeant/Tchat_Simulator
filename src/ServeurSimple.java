import java.net.*;

import iut.algo.Clavier;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.List;
import java.util.ArrayList;

public class ServeurSimple
{
	private ServerSocket   serverSocket;

	private Thread tgdc;

	private BufferedReader in;
	private PrintWriter	out;

	private List<GerantDeClient> lstClients;

	public ServeurSimple()
	{
		try
		{
			this.serverSocket = new ServerSocket(2005);

			this.lstClients = new ArrayList<GerantDeClient>();

			while (true)
			{
				// attendre patiemment le client
				Socket toClient = this.serverSocket.accept();

				this.in  = new BufferedReader( new InputStreamReader(toClient.getInputStream()));
				this.out = new PrintWriter(toClient.getOutputStream(), true);
	
				// créer un GerantDeClient pour traiter ce nouveau client
				GerantDeClient gdc = new GerantDeClient(toClient, this);
				this.lstClients.add(gdc);

				// mettre ce gérant de client dans une Thread
				tgdc = new Thread(gdc);

				// lancer la thread qui gérera ce client
				tgdc.start();

				// Vérification du pseudo
				for (int cpt = 0 ; cpt < this.lstClients.size() && gdc.getPseudo() != null ; cpt++)
				{
					if (this.lstClients.get(cpt).getPseudo().equals( gdc.getPseudo() ) && this.lstClients.get(cpt) != gdc)
					{
						gdc.ecrireASonClient("Pseudo déjà pris");
						tgdc.interrupt();
					}
				}
			}
		}
		catch(Exception e) { e.printStackTrace(); }
	}

	public void envoyerATousLesClients( String message, GerantDeClient expediteur )
	{
		for ( GerantDeClient gerant : this.lstClients )
		{
			if (gerant != expediteur)
				gerant.ecrireASonClient(message);
		}
	}

	public void envoyerMessagePrive( String destinataire, String message )
	{
		for ( GerantDeClient gerant : this.lstClients )
		{
			if (gerant.getPseudo().equals(destinataire))
				gerant.ecrireASonClient("Message privé de " + message);
		}
	}
}
