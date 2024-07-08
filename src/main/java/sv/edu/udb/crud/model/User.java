package sv.edu.udb.crud.model;

import jakarta.persistence.*;

/* Clase que representa la entidad User en la base de datos */
@Entity
@Table(name = "`user`") /* Define el nombre de la tabla en la base de datos */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* Define la estrategia de generación de claves primarias */
    private int id;

    @Column(unique = true) /* Indica que el campo debe ser único en la tabla */
    private String name; /* Nombre del usuario */

    private String date; /* Fecha de creación o nacimiento del usuario */
    private String email; /* Correo electrónico del usuario */
    private String password; /* Contraseña del usuario */

    /* Constructor por defecto */
    public User() {}

    /* Constructor con todos los campos */
    public User(int id, String name, String date, String email, String password) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.email = email;
        this.password = password;
    }

    /* Constructor sin el campo id (utilizado al insertar registros nuevos) */
    public User(String name, String date, String email, String password) {
        this.name = name;
        this.date = date;
        this.email = email;
        this.password = password;
    }

    /* Métodos getter y setter para todos los campos */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
