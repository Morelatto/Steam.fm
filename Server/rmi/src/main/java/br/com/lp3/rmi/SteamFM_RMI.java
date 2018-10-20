package br.com.lp3.rmi;

import br.com.lp3.rmi.dao.remote.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class SteamFM_RMI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("AlbumDAO", new AlbumDAO_Remoto());
            registro.rebind("ArtistaDAO", new ArtistaDAO_Remoto());
            registro.rebind("GeneroJogoDAO", new GeneroJogoDAO_Remoto());
            registro.rebind("MusicaDAO", new MusicaDAO_Remoto());
            registro.rebind("RelacaoDAO", new RelacaoDAO_Remoto());
            registro.rebind("UsuarioDAO", new UsuarioDAO_Remoto());
            System.out.println("STEAMFM - Serviço registrado com sucesso.");
        } catch (RemoteException ex) {
        }
    }

}