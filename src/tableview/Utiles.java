/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author PC Albert Arranz
 */
public class Utiles {
    //metodo para rellenar un array de objetos persona con 4 parametros  id,nombre,edad,ciudad
    public static Persona[] RellenarArray(Persona array[]) {

        String nombres[] = {"Mario", "Alberto", "Israel", "Diego"};
        int edades[] = {35, 33, 40, 41};
        String ciudades[] = {"Madrid", "Almeria", "Mexico", "Alcobendas"};
        int cont = 1;
        for (int i = 0; i < array.length; i++) {
            array[i] = new Persona(cont, nombres[i], edades[i], ciudades[i]);
            cont++;

        }

        return array;
    }
    
    //metodo para rellenar los datos en local y mostrarlos en la tabla
    public static JTable RellenarTablaLocal(JTable tabla, Persona[] grupo, ModeloTabla modelo){
                String[] columnas = {"id", "Nombre", "Edad","Ciudad"};
                modelo.setColumnIdentifiers(columnas);
                //se declara un int como contador , se ocupara de controlar las filas ,se inicializa en 0
                int nroFilas = 0;
                //bucle for para recorrer el array de objetos persona grupo
                for (int i = 0; i < grupo.length; i++) {
                    //se setean los datos del id del objeto persona del array grupo, se indica el contador 0,y la posicion en la columna
                    tabla.setValueAt(grupo[i].getId(), nroFilas, 0);
                    tabla.setValueAt(grupo[i].getNombre(), nroFilas, 1);
                    tabla.setValueAt(grupo[i].getEdad(), nroFilas, 2);
                    tabla.setValueAt(grupo[i].getciudad(), nroFilas, 3);
                    //se aumenta en 1 el contador para pasar por el numero de filas correspondiente
                    nroFilas++;   
    }
    return tabla;
    }

    //metodo para ordenar por orden alfabetico cualquier array de Strings u objetos con atrbutos string
    public static void ordenNombre(Persona lista[]) {

        //Usamos un bucle for doble
        for (int i = 0; i < (lista.length - 1); i++) {
            for (int j = i + 1; j < lista.length; j++) {
                if (lista[i].getNombre().compareToIgnoreCase(lista[j].getNombre()) > 0) {
                    //Intercambiamos valores
                    Persona variableauxiliar = lista[i];
                    lista[i] = lista[j];
                    lista[j] = variableauxiliar;

                }
            }
        }
    }

    //metodo para ordenar por orden de menor a mayor cualquier array de numeros
    public static void OrdenEdad(Persona lista[]) {

        //Usamos un bucle for dentro de otro for
        for (int i = 0; i < (lista.length - 1); i++) {
            for (int j = i + 1; j < lista.length; j++) {
                //si la edad del objeto actual(i) es mayor que la edad del objeto siguiente (j)
                if (lista[i].getEdad() > lista[j].getEdad()) {
                    //Intercambiamos valores a traves de una variable auxiliar de uso local
                    //guardamos el valor del objeto actual(i) en la variable auxiliar
                    Persona variableauxiliar = lista[i];
                    //cambiamos el objeto (i) por  el (j) 
                    lista[i] = lista[j];
                    //y a su vez le pasamos al objeto (j) el valor de la variable auxiliar 
                    lista[j] = variableauxiliar;

                }
            }
        }
    }

    
    
    public static ModeloTabla CargaBaseDatos(ModeloTabla modelo, JTable tabla ) {
        //se declara condicion para que no repinte la tabla con los mismos datos una y otra vez
        //,si el modelo contiene menos de 0 filas accede
        Connection conexion=null;
        if (modelo.getRowCount()<1){
        try {
             //String con la direccion de la bbdd en el servidor 
            String url = "jdbc:mysql://localhost:3306/control";
            //usuario en String
            String user = "root";
            //password en String
            String password = "";
            conexion = getConex(url,user,password);
             //se crea el objeto Statement para realizar una consulta la bbdd con los datos a traves de la conexion creada anteriormente 
            Statement sentencia = getConex(url, user, password).createStatement();
            //se crea objeto ResulSet para almacenar el valor obtenido por la consulta SQL realizada por el obj Statement
            ResultSet result = sentencia.executeQuery("SELECT * FROM archivos ");
             //declaramos objetos para guardar los resultados obtenidos para la tabla
            Object[] fila = new Object[3];
            
            //bucle while para mostrar datos mientras haya algun valor en el ResulSet si no los hay, no lo realizara
            while (result.next()) {
               
//                System.out.println(result.);
                //Creamos un Objeto con tantos parámetros como datos retorne cada fila 
                // de la consulta
                
                fila[0] = result.getInt("idLibro"); //pasamos como parametro el nombre en String de los campos de la base de datos
                fila[1] = result.getString("Autor");
                fila[2] = result.getString("Titulo");
//               
            
                modelo.addRow(fila);

             
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //para referescar la tabla despues de cada llamada al metodo , da problemas ya que desaparecen los demas componentes del panel
//         tabla.updateUI();
        
        
        }
        return modelo;
    }
    
    
    public static Connection getConex (String url,String user,String password){
         //se declara el objeto COnnection con la referencia conexion a null 
            java.sql.Connection conexion =null;
        
        try {
            //le asignamos parametros(url , user y password) para conectar al objeto conexion a traves del metodo Druvermanager.getConnection
            conexion = DriverManager.getConnection(url, user, password);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    
    
    
        return conexion;
    }
    //metodo para borrar toda las filas
    public static void BorrarTabla(ModeloTabla modelo){
    
    //bucle while para indicar que si el modelo tiene  filas por encima de 0 se borren todas 
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        try {
            
              Statement sentencia = getConex("jdbc:mysql://localhost:3306/Control", "root", "").createStatement();
                /* ejecuta la sentencia de borrado */
                int filasAfectadas;

                filasAfectadas = sentencia.executeUpdate("DELETE FROM Usuarios WHERE 1=1");
                System.out.println("Filas afectadas: " + filasAfectadas);
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    public static void rellenarTablaSql(ModeloTabla modelo){
    
        try {
            
             Statement sentencia = getConex("jdbc:mysql://localhost:3306/Control", "root", "").createStatement();
                /* ejecuta la sentencia de borrado */
                int filasAfectadas;

                filasAfectadas = sentencia.executeUpdate("INSERT  FROM Usuarios WHERE 1=1");
                System.out.println("Filas afectadas: " + filasAfectadas);
            
            
        } catch (Exception e) {
        }
    
    
    
    
    }
    
    
    
    
}
