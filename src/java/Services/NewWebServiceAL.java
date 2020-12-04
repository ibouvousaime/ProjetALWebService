/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
/**
 *
 * @author wustenqualle
 */
@WebService(serviceName = "NewWebServiceAL")
public class NewWebServiceAL {
    private  final String user="newsite";
    private final String password="passer";
    private final String url ="jdbc:mysql://localhost:3306/journal";
    private String driver = "com.mysql.jdbc.Driver";
    private Connection connex;
    
    /**
     * This is a sample web service operation
     */
    public NewWebServiceAL() {
    
    }
    
    @WebMethod(operationName = "CreeUtilisateur")
    public boolean CreerUtilisateur(@WebParam(name = "name") String nom,
                        @WebParam(name = "prenom") String prenom,
                        @WebParam(name = "login") String login,
                        @WebParam(name = "password") String password,
                        @WebParam(name = "profil") String profil) {
        try {
            connex = DriverManager.getConnection(url, user, this.password);
            System.out.println("Connexion à la base de données réussie");
            
            String sql ="INSERT INTO utilisateurs(login, password, prenom, nom, profil) VALUES (?,?,?,?,?)";
            PreparedStatement stm = connex.prepareStatement(sql);
            stm.setString(1, login);
            stm.setString(2, password);
            stm.setString(3, prenom);
            stm.setString(4, nom);
            stm.setString(5, profil);
            stm.executeUpdate();//Inserer dans la table utilisateurs
            return true;
       } catch (Exception e) {
            e.printStackTrace();
            return false;
      }
    }
}
