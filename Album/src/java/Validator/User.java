/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Raf
 */
@ManagedBean(name="userVal")
@RequestScoped
public class User implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
    
    @EJB
    private Session.UserFacade ejbFacade;
    private List<User> items = null;
    
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    Map<String, Object> sessionMap = externalContext.getSessionMap();

    
    private String username;
    private String pass;
    /**
     * Creates a new instance of User
     */
    public User() {
    }
    
    public String validar()
    {                 
        List<Entities.User> users = ejbFacade.findAll();
        boolean find=false;
        for(Entities.User u :users)
        {
            if(u.getLogin().equals(username))
                if(u.getPassword().equals(pass))
                {
                    sessionMap.put("user", u);
                    if(u.getIdAccessLevel().getIdAccessLevel() == 1)
                         return "homeAdmin";
                    else
                        return "homeUser";  
                }
        }
        return "";
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the login
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param login the login to set
     */
    public void setPass(String login) {
        this.pass = login;
    }
    
}
