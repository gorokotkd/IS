package packProyecto;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class conecta {

    public static void main(String[] args){

        try {

            //1- CREAR CONEXION
    //        Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost/proyectoisbueno?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");

            //2-STATEMENT
     //       Statement miStatement = miConexion.createStatement();

            //3- EJECUTAR SQL
            /**miStatement.execute("load data local infile 'D:\\IngenierIa_Software\\Proyecto\\movie-titles.csv' into table peliculas fields terminated by"
            		+ "';' enclosed by '\"' lines terminated by '\r\n' (idpeli, nombre)");**/
               // FileChooser f = new FileChooser();
          /*      String c = f.leerFichero().getAbsolutePath();
                String resul = c.replace("\\", "/");
                System.out.println(resul);*/
          //  }
            
        }catch (Exception e){
            System.out.println("NO CONECTA");
            e.printStackTrace();
        }
    }
}
