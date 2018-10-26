package br.com.lp3;

import br.com.lp3.dao.impl.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AlbumDAO", new RemoteAlbumDAO());
            registry.rebind("ArtistaDAO", new RemoteArtistaDAO());
            registry.rebind("GeneroJogoDAO", new RemoteGeneroJogoDAO());
            registry.rebind("MusicaDAO", new RemoteMusicaDAO());
            registry.rebind("RelacaoDAO", new RemoteRelacaoDAO());
            registry.rebind("UsuarioDAO", new RemoteUsuarioDAO());
            System.out.println("RMI server started");
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }

}
