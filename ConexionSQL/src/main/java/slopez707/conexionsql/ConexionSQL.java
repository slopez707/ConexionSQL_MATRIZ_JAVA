/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package slopez707.conexionsql;

import java.sql.*;

/**
 *
 * @author soryl
 */
public class ConexionSQL {

    public static void main(String[] args) {    
            
        Connection conexion_bd = null; //objeto para crear la conexión a la BD
        Statement st = null; //objeto para crear la conexión
        
        String url = "jdbc:postgresql://localhost/SORAYALG"; // cadena de conf para conetar a la BD
        /*            puente de conn/ sevidor bd / nombre de bd*/
        
        //credenciales para la conexión a la BD
        String user = "soraya_java"; // usuario
        String pwd="pruebas"; // contraseña
        
        try{ 
            conexion_bd = DriverManager.getConnection(url,user,pwd); //conexión con las credenciales y la conf al motor de bd
            //st = conexion_bd.createStatement(); //ejecución a la conexión
            // ResultSet objeto para la ejecucion de la sentncia SQL y al almacenar en rs el resultado
            st = conexion_bd.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet resultado_sql = st.executeQuery("SELECT * FROM articulos ORDER BY id");        
           
            resultado_sql.last();
            int num_filas = resultado_sql.getRow(); // Contar el número de filas
            resultado_sql.beforeFirst(); // recorrido a las filas
            
            String[][] list_articulos = new String[num_filas][5]; // declaracion de una array/matriz con tamaña cantidad de registrosX5 
                     
            int x = 0;
            while(resultado_sql.next()){ //iterando los datos de la consulta              
                float precio =  resultado_sql.getFloat("precio");
                float descuento = 0.15f;
                float tot = precio*descuento;
                list_articulos[x][0] = String.valueOf(resultado_sql.getInt("id"));  // convertir el dato int a string con el metodo sig: String.valueOf 
                list_articulos[x][1] = resultado_sql.getString("nombre"); 
                //list_articulos[x][2] = String.valueOf(resultado_sql.getInt("existencia"));
                list_articulos[x][2] = String.valueOf(resultado_sql.getFloat("precio"));
               
                list_articulos[x][3] = String.valueOf(tot); 
                if("Dama".equals(resultado_sql.getString("nombre"))){
                    
                }
               x++;
            }
           
            //System.out.println(list_articulos);
            for (String[] fila : list_articulos) {
                System.out.println("Id: " + fila[0] 
                        + ", Nombre: " + fila[1].trim() //trim sirve para eliminar espacios 
                        + ", Precio: " + fila[2] 
                        + ", Descuento: " + fila[3] 
                        );
            }
            
            resultado_sql.close();
            st.close();
            conexion_bd.close();
         }catch(SQLException e){
             System.out.println("ERROR: "+e.getMessage());
         }
        
    }


}
