package conexionmysqlcorba;

import java.sql.*;
import Terceros.*;
import javax.swing.JOptionPane;

public class Tercero extends TerceroPOA {
    Conexion objConec = new Conexion();

    @Override
    public boolean insertarTercero(String nombre, String apellido, String telefono) {
        boolean resultado = false;
        try {
            String sql = "insert into terceros (nombres,apellidos,telefono) values ('"+nombre+"','"+apellido+"','"+telefono+"')";
            objConec.conectar();
            Statement st = objConec.conex.createStatement();
            int valor = st.executeUpdate(sql);
            if(valor>0){
                resultado = true;
            }
            //Se cierran las conexiones
            objConec.conex.close();
            st.close();
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al insertar. "+e.getMessage());
        }        
        return resultado;
    }

    @Override
    public boolean actualizarTercero(int id, String nombre, String apellido, String telefono) {
        boolean resultado = false;
        try {
            String sql = "update terceros set nombres = '"+nombre+"', apellidos = '"+apellido+"', telefono = '"+telefono+
                    "' where id = '"+id+"' ";
            Statement st = objConec.conex.createStatement();
            int valor = st.executeUpdate(sql);
            if(valor>0){
                resultado = true;
            }
            //Se cierran las conexiones.
            objConec.conex.close();
            st.close();
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al actualizar. "+e.getMessage());
        }        
        return resultado;
    }

    @Override
    public boolean eliminarTercero(int id) {
        boolean resultado = false;
        try {
            String sql = "Delete from terceros where id = "+id;
            Statement st = objConec.conex.createStatement();
            int valor = st.executeUpdate(sql);
            if(valor>0){
                resultado = true;
            }
            //Se cierran las conexiones.
            objConec.conex.close();
            st.close();
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al eliminar. "+e.getMessage());
        }        
        return resultado;
    }

    @Override
    public String consultarTercero(int id) {
        String resultado = "";
        try {
            String sqlConsultar = "Select * from terceros where id = "+ id;
            objConec.conectar();
            Statement st = objConec.conex.createStatement();
            ResultSet rs = st.executeQuery(sqlConsultar);
            while(rs.next()){
                resultado += rs.getString(2) + " - "
                        + rs.getString(3) + " - "
                        + rs.getString(4);
            }
            //Se cierran las conexiones.
            rs.close();
            objConec.conex.close();
        } catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
        }
        return resultado;
    }

    @Override
    public void shoutdown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ResultSet cargarTercero(){
        ResultSet resultado = null;
        try {
            String sql = "Select nombres, apellidos, telefono from terceros";
            objConec.conectar();
            Statement st = objConec.conex.createStatement();
            resultado = st.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
        }
        
        return resultado;
    }
}
