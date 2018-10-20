package br.com.lp3.rmi;

import br.com.lp3.entities.Usuario;

import javax.ejb.Stateless;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
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
    public void create(Usuario usuario) {
        servico.create(usuario);
    }

    @Override
    public List<Usuario> read() {
        return (List<Usuario>) servico.read();
    }

    @Override
    public void update(Usuario usuario) {
        servico.update(usuario);
    }

    @Override
    public void delete(int id) {
        servico.delete(id);
    }
}
