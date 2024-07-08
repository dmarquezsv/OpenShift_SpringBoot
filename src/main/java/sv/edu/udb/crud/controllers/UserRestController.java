package sv.edu.udb.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.crud.model.User;
import sv.edu.udb.crud.service.UserService;

import java.util.List;
import java.util.Objects;

/**
 * Controlador REST para manipular operaciones CRUD de usuarios.
 */
@RestController
@RequestMapping(path = "api/v1/users")
public class UserRestController {

    private  final UserService userService;

    /**
     * Constructor de la clase UserRestController.
     *
     * @param userService Servicio para la gestión de usuarios.
     */

    @Autowired
    public  UserRestController(UserService userService){
        this.userService = userService;
    }

    /**
     * Método GET para obtener todos los usuarios.
     *
     * @return Lista de usuarios.
     */
    @GetMapping
    public  List<User> getUsers(){
        return this.userService.getUsers();
    }

    /**
     * Método POST para crear un nuevo usuario.
     *
     * @param user Nuevo usuario a crear.
     * @return Respuesta con el resultado de la operación.
     */

    @PostMapping
    public ResponseEntity<Object> createUsers(@RequestBody User user){
       return this.userService.newUser(user);
    }

    /**
     * Método PUT para actualizar un usuario existente.
     *
     * @param user Usuario a actualizar.
     * @return Respuesta con el resultado de la operación.
     */
    @PutMapping
    public ResponseEntity<Object> updateUsers(@RequestBody User user){
        return this.userService.newUser(user);
    }

    /**
     * Método DELETE para eliminar un usuario por su ID.
     *
     * @param id ID del usuario a eliminar.
     * @return Respuesta con el resultado de la operación.
     */
    @DeleteMapping(path = "{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") int id){
        return this.userService.deleteUser(id);
    }


}
