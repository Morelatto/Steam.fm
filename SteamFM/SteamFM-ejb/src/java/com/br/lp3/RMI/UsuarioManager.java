package com.br.lp3.RMI;

import com.br.lp3.entities.Usuario;
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
public class UsuarioManager extends UnicastRemoteObject implements UsuarioManagerLocal {

    Registry registro;
    GenericoDAO_Remoto servico;

    public UsuarioManager() throws RemoteException, NotBoundException {
        registro = LocateRegistry.getRegistry("localhost", 1099);
        servico = (GenericoDAO_Remoto) registro.lookup("UsuarioDAO");
    }

    @Override
    public void create(Usuario usuario) throws RemoteException {
        servico.create(usuario);
    }

    @Override
    public List<Usuario> read() throws RemoteException {
        return (List<Usuario>) servico.read();
    }

    @Override
    public void update(Usuario usuario) throws RemoteException {
        servico.update(usuario);
    }

    @Override
    public void delete(int id) throws RemoteException {
        servico.delete(id);
    }
}
