package com.br.lp3.RMI;

import com.br.lp3.entities.Artista;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Stateless
public class ArtistaManager extends UnicastRemoteObject implements ArtistaManagerLocal {

    Registry registro;
    GenericoDAO_Remoto servico;

    public ArtistaManager() throws RemoteException, NotBoundException {
        registro = LocateRegistry.getRegistry("localhost", 1099);
        servico = (GenericoDAO_Remoto) registro.lookup("ArtistaDAO");
    }

    @Override
    public void create(Artista artista) throws RemoteException {
        servico.create(artista);
    }

    @Override
    public List<Artista> read() throws RemoteException {
        return (List<Artista>) servico.read();
    }

    @Override
    public void update(Artista artista) throws RemoteException {
        servico.update(artista);
    }

    @Override
    public void delete(int id) throws RemoteException {
        servico.delete(id);
    }
}
