package sv.edu.udb.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sv.edu.udb.crud.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /*@Query("SELECT * FROM user p WHERE O.NAME =?") */
    /* Metodo personalizado de validacion de nombre  crear consulta propias*/
    Optional<User> findUserByName(String name);
}
