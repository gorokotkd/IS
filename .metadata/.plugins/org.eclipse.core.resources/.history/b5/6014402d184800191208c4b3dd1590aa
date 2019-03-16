package packProyecto;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class conecta {

    public static void main(String[] args){

        try {

            //1- CREAR CONEXION
            Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoisbueno?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");

            //2-STATEMENT
            Statement miStatement = miConexion.createStatement();

            //3- EJECUTAR SQL
            /**miStatement.execute("load data local infile 'D:\\IngenierIa_Software\\Proyecto\\movie-titles.csv' into table peliculas fields terminated by"
            		+ "';' enclosed by '\"' lines terminated by '\r\n' (idpeli, nombre)");**/
           /* miStatement.executeUpdate("load data local infile 'D:/IngenierIa_Software/Proyecto/movie-titles.csv' into table peliculas fields terminated by"
            		+ "';' enclosed by '\"' lines terminated by '\r\n' (idpeli, nombre)");*/
            ResultSet miResultset = miStatement.executeQuery("SELECT * FROM peliculas");

            //4- LEER EL RESULTSET

            while (miResultset.next()){
                System.out.println(miResultset.getString("idpeli")+"-->"+miResultset.getString("nombre"));
            }
        }catch (Exception e){
            System.out.println("NO CONECTA");
            e.printStackTrace();
        }
    }
}
