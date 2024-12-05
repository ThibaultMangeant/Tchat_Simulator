import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.*;

public class GerantDeClient implements Runnable
{
	private Socket toServer;
	private ServeurSimple server;

	private BufferedReader in;
	private PrintWriter    out;

	private String pseudo;

	private String quelquesEspaces;

	private static int position = 8;


	public GerantDeClient(Socket s, ServeurSimple server)
	{
		this.toServer = s;
		this.server = server;
		this.pseudo = "";
		this.quelquesEspaces = "                                                  ";

		try
		{
			this.in = new BufferedReader( new InputStreamReader( this.toServer.getInputStream() ) );
			this.out = new PrintWriter(this.toServer.getOutputStream(), true);
		}
		catch(Exception e)
		{ e.printStackTrace(); }
	}

	public void run()
	{
		this.out.print  ( quelquesEspaces );
		this.out.print  ("\033[5;50H");
		this.out.println("Bienvenue sur Tchat Simulator by Mangeant Thibault, Dujardin Thao");

		try
		{
			String m1 = "";

			this.out.println( "Entrez votre pseudo : " );
			this.pseudo = this.in.readLine();

			System.out.println("NOUVEAU CLIENT : " + this.pseudo);

			while ( true && m1 != null)
			{
				m1 = this.in.readLine();
				if ( m1 == null )
					System.out.println(this.pseudo + " s'est déconnecté");
				else
					this.recevoirDeSonClient(m1);
			}
		}
		catch(Exception e)
		{ e.printStackTrace(); }
	}

	public synchronized void ecrireASonClient(String message)
	{
		this.out.print  ("\033["+ GerantDeClient.position + ";0H");
		this.out.print  ( quelquesEspaces );
		this.out.print  ("\033["+ GerantDeClient.position + ";50H");
		this.out.println( message );
		GerantDeClient.position++;
	}

	public void recevoirDeSonClient(String message)
	{
		int ind1;
		int ind2;

		ind1 = message.indexOf(' ');

		if (ind1 == -1)
		{
			if (message.startsWith("/msgpriv"))
			{
				this.out.println("Usage : /msgpriv <pseudo> <message>");
			}
			else
			{
				System.out.println("Client " + this.pseudo + " écrit : " + message);
				this.server.envoyerATousLesClients(this.pseudo + " : " + message, this);
			}
		}
		else if (message.substring(0, ind1).equals("/msgpriv"))
		{
			ind2 = message.substring(ind1 + 1).indexOf(' ') + ind1 + 1;
	
			if (ind2 <= ind1)
				this.out.println("Usage : /msgpriv <pseudo> <message>");
			else
				this.server.envoyerMessagePrive( message.substring(ind1 + 1, ind2),this.pseudo + " : " + message.substring(ind2 + 1) );
		}
		else
		{
			System.out.println("Client " + this.pseudo + " écrit : " + message);
			this.server.envoyerATousLesClients(this.pseudo + " : " + message, this);
		}
	}

	public String getPseudo()
	{
		return this.pseudo;
	}
}
