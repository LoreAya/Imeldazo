package modelo;

import Entities.Picture;
import Entities.Post;
import Validator.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 *
 * @author Salvador_Solis
 */
@ManagedBean
@RequestScoped
public class Validaciones {
    /**
     * Creates a new instance of Validaciones
     */
//    @EJB
//    private Session.PictureFacade ejbFacade;
//    private List<Picture> items = null;
//    
    @EJB
    private Session.PostFacade ejbFacadePost;
    private List<Post> itemsPost = null;
    
    @Size (min = 3, max = 30 , message = "Ingrese el nombre del familiar")
    private String nombre;
    @NotNull
    @Size (min = 3, max = 50 , message = "Ingrese ambos apellidos")
    private String apellidos;
    @Min (value = 0,message = "Ingresa la edad numerica valida")
    private int edad;
    @Size (min = 3, max = 250 , message = "Ingrese la direccion completa")
    private String direccion;
    @Pattern (regexp = "^\\d{3}\\d{3}\\d{4}$",message = "Ingrese el numero de la forma 999-999-9999")
    private String teléfono;
    @Pattern (regexp = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]", message = "Ingresa un email correcto")
    private String email;
    @NotNull
    @Size (min = 1, max = 30 , message = "Ingrese el usuario de la cuenta")
    private String usuario;
    @NotNull
    @Size (min = 6, max = 30 , message = "Ingrese una contraseña minimo 6 caracteres")
    private String contraseña;
    @NotNull
    @Size (min = 1, max = 30 , message = "Ingrese el parentesco familiar")
    private String parentesco;

    private Date fecha=new Date(); //Fecha para SubirPost
    
    private List<String> images;
     
    @PostConstruct
    public void init() 
    {
        images = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {
            images.add("nature" + i + ".jpg");
        }
    }
    
    
    public Post initPosts() 
    {
        itemsPost = ejbFacadePost.findAll();
        return (Post)itemsPost.get(0);
    }
 
    public List<String> getImages() {
        return images;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTeléfono() {
        return teléfono;
    }

    public void setTeléfono(String teléfono) {
        this.teléfono = teléfono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getParentesco() {
        return parentesco;
    }

    /**
     * Creates a new instance of validaciones
     * @param parentesco
     */
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
    
      public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    public Validaciones() {
    }
    
    
}
