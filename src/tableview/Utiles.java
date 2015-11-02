/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;

/**
 *
 * @author PC
 */
public class Utiles {

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
    
    
    public static JTable RellenarTabla(JTable tabla, Persona[] grupo){
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

    
    
    
    
    
    public static ModeloTabla CargaBaseDatos(ModeloTabla modelo , JTable tabla) {

        try {
//        DefaultTableModel modelo = new DefaultTableModel();
            java.sql.Connection conexion = null;
            String url = "jdbc:mysql://localhost:3306/Control";
            String user = "root";
            String password = "";
            int row = 0;
            int column = 0;
            int cont = 0;
            Object[] fila = new Object[3];
            conexion = DriverManager.getConnection(url, user, password);
            Statement sentencia = conexion.createStatement();
            ResultSet result = sentencia.executeQuery("SELECT * FROM Control.usuarios ");
            while (result.next()) {

                System.out.println("" + result.getInt("Password"));
                //Creamos un Objeto con tantos parámetros como datos retorne cada fila 
                // de la consulta
                fila[0] = result.getInt("idUsuarios"); //Lo que hay entre comillas son los campos de la base de datos
                fila[1] = result.getString("Nombre");
                fila[2] = result.getInt("Password");
//                tabla.setValueAt(fila[cont], row, column);
             
                modelo.addRow(fila);

             
            }

        } catch (Exception e) {
        }
//        tabla.updateUI();
        return modelo;

    }

    public static void BorrarTabla(ModeloTabla modelo){
    
    
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    
    
    }
    
    
    
    
    
    
    
}
