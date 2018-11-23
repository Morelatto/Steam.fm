package br.com.lp3;

import br.com.lp3.dao.impl.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {

    private static final int RMI_SERVER_PORT = 1099;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(RMI_SERVER_PORT);
            registry.rebind("ArtistDAO", new RemoteArtistDAO());
            registry.rebind("AlbumDAO", new RemoteAlbumDAO());
            registry.rebind("SongDAO", new RemoteSongDAO());
            registry.rebind("GameGenreDAO", new RemoteGameGenreDAO());
            registry.rebind("GameGenreToMusicReleaseDAO", new RemoteGameGenreToMusicReleaseDAO());
            registry.rebind("SteamFmUserDAO", new RemoteSteamFmUserDAO());
            // TODO log
            System.out.println("RMI server started");
        } catch (RemoteException ex) {
            // TODO log
            System.out.println(ex);
        }
    }

}
