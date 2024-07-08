package sv.edu.udb.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sv.edu.udb.crud.model.User;
import sv.edu.udb.crud.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    // Almacena datos que se devuelven en las respuestas HTTP
    HashMap<String, Object> data;

    // Repositorio de usuarios para realizar operaciones de base de datos
    private final UserRepository userRepository;

    // Constructor que utiliza inyección de dependencias para inicializar el repositorio de usuarios
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Método para listar todos los usuarios
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    // Método para crear un nuevo usuario
    public ResponseEntity<Object> newUser(User user) {
        // Busca si ya existe un usuario con el mismo nombre
        Optional<User> res = userRepository.findUserByName(user.getName());
        data = new HashMap<>();

        //Verificar si ya existe un usuario con el mismo nombre
        if(res.isPresent()){
            data.put("error", true);
            data.put("message", "Ya existe un usuario con ese nombre");
            return new ResponseEntity<>(
                    data,
                    HttpStatus.CONFLICT
            );
        }


        // Mensaje de éxito al guardar el usuario
        data.put("message", "Se guardado con éxito");

        // Guarda el nuevo usuario en la base de datos
        userRepository.save(user);
        data.put("data", user);
        return new ResponseEntity<>(
                data,
                HttpStatus.CREATED
        );
    }

    // Método para eliminar un usuario por su ID
    public ResponseEntity<Object> deleteUser(int id){
        data = new HashMap<>();
        // Verifica si el usuario existe por su ID
        boolean status = this.userRepository.existsById(id);

        // Si el usuario no existe, devuelve un error
        if(!status){
            data.put("error", true);
            data.put("message", "No existe un usuario con ese ID");
            return new ResponseEntity<>(
                    data,
                    HttpStatus.CONFLICT
            );
        }

        // Elimina el usuario por su ID
        userRepository.deleteById(id);
        data.put("message", "Usuario eliminado");
        return new ResponseEntity<>(
                data,
                HttpStatus.ACCEPTED
        );
    }
}
