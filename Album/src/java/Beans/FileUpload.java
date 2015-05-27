/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Picture;
import Validator.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Raf
 */
@ManagedBean
@RequestScoped
public class FileUpload implements Serializable{
	
    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    @EJB
    private Session.PictureFacade ejbFacade;
    
     @EJB
    private Session.UserFacade ejbFacadeUser;
     
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    Map<String, Object> sessionMap = externalContext.getSessionMap();
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            System.out.println("Done");
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            try {
                copyFile(file.getFileName(), file.getInputstream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
   public void copyFile(String fileName, InputStream in) {
           try {
               
               ServletContext servletContext=(ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
              String destination=servletContext.getContextPath();
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination+File.separator+"resources"+File.separator+"img"+File.separator+fileName));
              
                int read = 0;
                byte[] bytes = new byte[1024];
              
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }              
                in.close();
                out.flush();
                out.close();
                String id = 1+"";//sessionMap.get("user").toString();
                System.out.println("New file created!"+fileName);
                Picture pic = new Picture();
                pic.setDateHour(new Date());
                pic.setUrlPicture(destination+fileName);
                Entities.User u = (Entities.User ) sessionMap.get("user");
                pic.setUseridUser(u);
                ejbFacade.create(pic);
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
   
   public void test()
   {
//       ServletContext servletContext=(ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
//        String logo=servletContext.getRealPath("")+File.separator+"resources"+File.separator+"img"+File.separator+"2.gif";
//        pdf.addHeader("Listado de puros", HeaderFooter.date());
//        pdf.add(Image.getInstance(logo));
   }
}
