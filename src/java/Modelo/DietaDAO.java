/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DietaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int d;

    // Método para listar todas las dietas
        public List listar() {
            String sql = "SELECT dieta.Id_dieta, dieta.nombre, dieta.tipo_dieta, dieta.duracion, dieta.suplemento, "
                    + "nutricionista.id_nutricionista, CONCAT(nutricionista.Nombre,' ',nutricionista.Apellido) AS nombre_nutricionista "
                    + "FROM dieta INNER JOIN nutricionista ON dieta.Id_nutricionista = nutricionista.id_nutricionista";
            List<Dieta> lista  = new ArrayList<>();
           

            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Dieta diet =new Dieta();
                    diet.setIdDieta(rs.getInt("id_dieta"));
                    diet.setNombre(rs.getString("nombre"));
                    diet.setTipoDieta(rs.getString("tipo_dieta"));
                    diet.setDuracion(rs.getString("duracion"));
                    diet.setSuplemento(rs.getString("suplemento"));
                    diet.setIdNutricionista(rs.getInt("id_nutricionista"));
                    diet.setNutricionista(rs.getString("nombre_nutricionista"));
                    lista.add(diet);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                
            }
            return lista;
        }
        // Método para agregar una nueva dieta
        public int agregar(Dieta diet) {
            String sql = "INSERT INTO dieta (nombre, tipo_dieta, duracion, suplemento, Id_nutricionista) VALUES (?, ?, ?, ?, ?)";
            int d=0;
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                
                ps.setString(1, diet.getNombre());
                ps.setString(2, diet.getTipoDieta());
                ps.setString(3, diet.getDuracion());
                ps.setString(4, diet.getSuplemento());
                ps.setInt (5, diet.getIdNutricionista());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
             return d;
            } 
           
         public Dieta listarId(int id) {
            Dieta die = new Dieta();
            String sql = "select * from dieta where Id_dieta = " + id;

            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    die.setNombre(rs.getString(2));
                    die.setTipoDieta(rs.getString(3));
                    die.setDuracion(rs.getString(4));
                    die.setSuplemento(rs.getString(5));
                    die.setNutricionista(rs.getString(6));      
                    
                }
                
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
             return die;
        } 
        public int actualizar(Dieta di) {
            
            String sql = "update dieta set nombre=?, tipo_dieta=?, duracion=?, suplemento=?,Id_nutricionista=? where Id_dieta=?";
            
            try {
                    con = cn.Conexion();
                    ps = con.prepareStatement(sql);
                    ps.setString(1, di.getNombre());
                    ps.setString(2, di.getTipoDieta());
                    ps.setString(3, di.getDuracion());
                    ps.setString(4, di.getSuplemento());
                    ps.setString(5, di.getNutricionista());
                    ps.setInt(6, di.getIdDieta());
                    ps.executeUpdate();      
  
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
             return d;
        } 
        public void eliminar(int id) {
        String sql = "delete from dieta where Id_dieta=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
    }
        
        
}



