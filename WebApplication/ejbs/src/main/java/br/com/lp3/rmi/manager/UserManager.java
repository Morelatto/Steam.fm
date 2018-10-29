package br.com.lp3.rmi.manager;

import br.com.lp3.entities.User;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

@Local
public interface UserManager extends Remote {

    Optional<User> get(long id);

    List<User> getAll();

    void save(User user);

    void update(User user);

    void delete(User user);

}
