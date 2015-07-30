/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplopruebas;

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author andresg.londono
 */
public class EjemploPruebas {
    
   public static String texto;
   public static int prueba, respuesta;
   int lineas;
   int metodos = 0;
   int clases = 0;
   boolean bandera=false;
   String texto1;
  

    /**
     * @param args the command line arguments
     */
    public static void main ( String[] args) {
        // TODO code application logic here
        
      EjemploPruebas pro=new EjemploPruebas();
      texto = pro.leerGrafico();
      while(respuesta ==0)
      {
      respuesta = JOptionPane.showConfirmDialog(null, "¿otro archivo?", "Desea Evaluar", JOptionPane.YES_NO_OPTION);
                        
                        if (respuesta == 0)
                        {
                            texto = pro.leerGrafico();
                        }
                        else  
                        JOptionPane.showMessageDialog(null, "Gracias, presione Aceptar para salir");
      }
      
    }
    
    public String leerGrafico ( )
    {
        File f;
        String confirmation;
        javax.swing.JFileChooser j= new javax.swing.JFileChooser();
        j.showOpenDialog(j);
             try{
                String path= j.getSelectedFile().getAbsolutePath();
                String lectura="";
                f = new File(path);
                if((f.toString().endsWith(".txt"))||(f.toString().endsWith(".java"))){}else{
                        javax.swing.JOptionPane.showMessageDialog(j, "Formato de archivo invalido");
                        System.exit(0);
        }
                    try{
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);
                        String aux;
                        while((aux = br.readLine())!=null)
                        {
                        //System.out.println("linea: "+ aux);
                        lectura = lectura+aux+"\n";
                        confirmation=trataLinea(aux);
                        }
                        mostrarResultado(lineas,metodos,clases);

                        }catch(IOException e){}
                        return lectura;
                        }catch(NullPointerException e){
                        javax.swing.JOptionPane.showMessageDialog(j, "Has seleccionado cerrar programa, saliendo...");
                        System.exit(0);
                        }
                  
                        return null;
                       
     }
    
    public String trataLinea ( String linea) {

        String tempo;
        String retorno = "";
        lineas = lineas + 1;
        StringTokenizer tokens = new StringTokenizer(linea);
        int nDatos;
        nDatos = tokens.countTokens();
        if (nDatos > 0) {
            while (tokens.hasMoreTokens()) {
                tempo = tokens.nextToken();
                if (tempo.equals("public") || (tempo.equals("private"))
                        || (tempo.equals("protected"))) {
                    while (tokens.hasMoreTokens()) {
                        tempo = tokens.nextToken();
                        if (tempo.equals("static")) {
                            while (tokens.hasMoreTokens()) {
                                tempo = tokens.nextToken();
                                if (tempo.equals("void") || (tempo.equals("int"))
                                        || (tempo.equals("double")) || (tempo.equals("boolean"))
                                        || (tempo.equals("String"))) {
                                    while (tokens.hasMoreTokens()) {
                                        tempo = tokens.nextToken();
                                        while (tokens.hasMoreTokens()) {
                                            tempo = tokens.nextToken();
                                            if (tempo.equals("(")) {
                                                metodos = metodos + 1;
                                                break;

                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (tempo.equals("void") || (tempo.equals("int"))
                                    || (tempo.equals("double")) || (tempo.equals("boolean"))
                                    || (tempo.equals("String"))) {
                                while (tokens.hasMoreTokens()) {
                                    tempo = tokens.nextToken();
                                    while (tokens.hasMoreTokens()) {
                                        tempo = tokens.nextToken();
                                        if (tempo.equals("(")) {
                                            metodos = metodos + 1;
                                        }
                                        if (tempo.equals("class")) {
                                            clases = clases + 1;
                                        }
                                    }
                                }
                            }
                            if (tempo.equals("class")) {
                                clases = clases + 1;
                            }
                        }
                    }
                }
                if (tempo.equals("{") && nDatos == 1) {
                    lineas = lineas - 1;
                    break;
                }
                if (tempo.equals("}") && nDatos == 1) {
                    lineas = lineas - 1;
                    break;
                }
                if (tempo.equals("for") || tempo.equals("for(")) {
                    lineas = lineas + 2;
                    break;
                }
                if (tempo.equals("import") || (tempo.equals("//"))
                        || (tempo.equals("package"))) {
                    lineas = lineas - 1;
                    break;
                }
                if (tempo.equals("class")) {
                    clases = clases + 1;
                    break;
                }
                if (tempo.equals("/*")) {
                    bandera = true;
                    while (tokens.hasMoreTokens()) {
                        tempo = tokens.nextToken();
                        if (tempo.equals("*/")) {
                            bandera = false;
                            lineas = lineas - 1;
                            break;
                        }
                    }
                    break;
                }
                if (tempo.equals("*/")) {
                    lineas = lineas - 1;
                    bandera = false;
                }

            }
            if (bandera == true) {
                lineas = lineas - 1;
            }

        } else {
            lineas = lineas - 1;
        }

        return retorno;

    }

    public void mostrarResultado ( int lin, int met, int clas) {
        texto1 = "El numero de clases: " + clases + "\n" + "El numero de metodos: "
                + metodos + "\n" + "El numero de lineas: " + lineas + "\n";
        System.out.println("EL VALOR DE CLASES ES " + clases);
        System.out.println("EL VALOR DE LINEAS ES " + lineas);
        System.out.println("EL VALOR DE METODOS ES " + metodos);
        JOptionPane.showMessageDialog(null, texto1);
    }

    public void repetir ( ) {
    }
}
