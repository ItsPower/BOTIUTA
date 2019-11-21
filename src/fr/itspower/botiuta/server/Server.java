package fr.itspower.botiuta.server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

public abstract class Server implements Runnable {

    private final int PORT;
    
	protected ServerSocket serverSocket = null;
	protected Thread runningThread = null;
    protected boolean isStopped = false;

    /**
	 * Construit le serveur h�ritable qui va �couter le port sp�cifi�
	 * 
	 * @param id - l'identifiant utilisateur
	 * @param groupe - le groupe si l'utilisateur en a d�j� choisi un
	 */
    protected Server(int port) {
    	this.PORT = port;
    }

    /**
     * Permet de saovir si le sokcet d'�coute est ferm�
     * @return boolean
     */
    protected synchronized boolean isStopped() {
        return this.isStopped;
    }
    
    /**
     * Ferme le socket d'�coute du serveur
     */
    protected synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new Error("erreur lors de la fermeture du serveur", e);
        }
    }
    
    /**
     * Ouvre le socket d'�coute du serveur sur le port sp�cifi�
     */
    protected void start() {
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch(BindException e) {
            throw new Error("le port "+PORT+" est deja utilise.", e);
        } catch(IOException e) {
            throw new Error("le port "+PORT+" ne peut etre ouvert.", e);
        }
    }

}
