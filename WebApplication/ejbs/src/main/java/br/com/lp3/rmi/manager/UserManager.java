package br.com.lp3.rmi.manager;

import br.com.lp3.entities.SystemUser;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

@Local
public interface UserManager extends Remote {

    Optional<SystemUser> get(long id);

    List<SystemUser> getAll();

    void save(SystemUser systemUser);

    void update(SystemUser systemUser);

    void delete(SystemUser systemUser);

}
