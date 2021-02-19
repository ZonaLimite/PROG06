/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase con utiliades varias de checkeo cadenas y dnis
 * @author Francisco Boga
 */
public class UtilChecks {

 
    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
    
     /**
     * Metodo calcularLetraNIF :se hace public este metodo para disponer de el desde
     * fuera paquete.Require un int con el entero del DNI y devuelve la letra
     * correspondiente.
     * @author reutilizado Tema 04 PROG
     * @param dni el numero de dni a ckeckear.
     * @return char
     */
     public static char calcularLetraNIF(int dni) {
        char letra;
        // Cálculo de la letra NIF
        letra = LETRAS_DNI.charAt(dni % 23);
        // Devolución de la letra NIF
        return letra;
    }

    private static char extraerLetraNIF(String nif) {
        char letra = nif.charAt(nif.length() - 1);
        //return letra;
        return Character.toUpperCase(letra); //Devolvemos su equivalente en mayúscula para poder comparar con las letras de la cadena LETRAS_DNI
    }

    private static int extraerNumeroNIF(String nif) {
        int numero = Integer.parseInt(nif.substring(0, nif.length() - 1));
        return numero;
    }

/**
 * Metodo validarNIF_ExpReg constituye una interfaz standar de ejecucion de validaciones
 * mediante mascaras de  expresiones regulares, apoyada en las clases Patter y Matcher.
 * @param exp la cadena que constiuye la expresion de filtrado o validacion.
 * @param dni la cadena sobre la que se va a realizar la validacion.
 * @return boolean True si es valida, false en caso contrario.
 */
    public static boolean validar_ExpReg(String exp,String dni){
        boolean correcto=false;
        
        Pattern p=Pattern.compile(exp);

        Matcher m=p.matcher(dni);

        if (m.matches()) correcto=true;
        return correcto;
    }
    
 /**
 * Metodo validarNIF :se hace public este metodo para disponer de el desde
 * fuera paquete.Require un String con el NIF completo y devuelve true si
 * es valido.
 * @author reutilizado Tema 04 PROG
 * @param nif el nif a checkear.
 * @return boolean
 */
 public static boolean validarNIF(String nif){
        boolean valido = true;   // Suponemos el NIF válido mientras no se encuentre algún fallo
        char letra_calculada;
        char letra_leida;
        int dni_leido;

        if (nif == null) {  // El parámetro debe ser un objeto no vacío
            valido = false;

        } else if (nif.length() < 8 || nif.length() > 9) {    // La cadena debe estar entre 8(7+1) y 9(8+1) caracteres
            valido = false;

        } else {
            letra_leida = UtilChecks.extraerLetraNIF(nif);    // Extraemos la letra de NIF (letra)
            dni_leido = UtilChecks.extraerNumeroNIF(nif);  // Extraemos el número de DNI (int)
            letra_calculada = UtilChecks.calcularLetraNIF(dni_leido);  // Calculamos la letra de NIF a partir del número extraído
            if (letra_leida != letra_calculada) {   // Comparamos la letra extraída con la calculada
                valido = false;
            }
        }
        
        return valido;
    }

/**
* validacion de Nombre de usuario 40 max. caracteres ,Nombre y 2 Apellidos.
* @param value La cadena que contiene el nombre y los 2 apellidos.
* @return boolean True si cumple la validacion, false si no.
*/    
    public static boolean validarNombre(String value){
        boolean esValido=true;
        if(value.length()>40)return false;//mayor de 40 chars ,false
        
        //La verificacion va a consistir en buscar 2 espacios
        //Se supone que el usuario introduce el nombre,asi
        //(Nombre Apellido1 Apellido2)
        int index = value.indexOf(" ");//busco un espacio
        
        if(index == -1){// no hay ni un espacio, false
            return false;
        }else{
          //compruebo si hay un segundo espacio  
          if(value.indexOf(" ",index+1)==-1){ //si no lo hay, no hay 2 espacios
              return false;
          }
          //si llego hay 2 espacios 
        }
        return esValido;
    }
    
/**
* Logica de negocio de validacion de campos base 
* @param paramName el identificador del parametro de entrada a checkear.
* @param valueParam el valor que se ha introducido para el parametro a checkear.
* @exception ParseException Si no se cumple los criterios de validacion.
*/    
    public static void validarParametro(String paramName, String valueParam) throws ParseException{
        switch (paramName) {
            case "Fecha de matriculacion. Formato[aaaa-mm-dd]" :{
                try{
                    LocalDate.parse(valueParam);
                    if(LocalDate.parse(valueParam).isAfter(LocalDate.now())){
                        String message = "La fecha de matriculacion debe ser anterior a la fecha actual";
                        throw new ParseException(message,0);
                    }
                }catch(DateTimeParseException dtpe){
                    String message = "La fecha de matriculacion Debe cumplir el formato [aaaa-mm-dd]";
                    throw new ParseException(message,0);
                }
                break;
            }
            case "Nº DNI (con letra)" :{
                if(UtilChecks.validar_ExpReg("[0-9]{7,8}[A-Za-z]",valueParam)){
                } else {
                    String message = "DNi Incorrecto ...";
                    throw new ParseException(message,0);
                }
                break;
            }
            case "Numero de km's [ > 0]", "Precio (P.V.P)" :{
                try{
                    Integer.parseInt(valueParam);
                    if(paramName.equals("Numero de km's [ > 0]") && Integer.parseInt(valueParam) <=0){
                        String message = "El valor indicado tiene que ser mayor que 0";
                        throw new ParseException(message,0);
                    }
                }catch (NumberFormatException nfe){
                    String message = "valores no numericos ...";
                    throw new ParseException(message,0);
                }
                break;
            }
            case "matricula" :{
                if(UtilChecks.validar_ExpReg("[0-9]{4}[A-Z]{3}",valueParam)){
                } else {
                    String message = "Matricula incorrecta ...";
                    throw new ParseException(message,0);
                }
                break;
            }
            case "Nombre Apellido1 Apellido2": {
                if(UtilChecks.validarNombre(valueParam)){
                } else {
                    String message = "Debe introducir Nombre y 2 apellidos y un max. de 40 caracteres...";
                    throw new ParseException(message,0);
                }
                break;                
            }
            default :{
                
            }
        }
    }    
   
}

