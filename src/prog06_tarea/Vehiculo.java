/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog06_tarea;

import java.time.LocalDate;
import java.time.Period;


/**
 *   La clase definicion de vehiculo.
 * 
 *   @author F.J. BOGA
 */
public final class Vehiculo {
///////////////////////////////////////////////////////////////////////////
// Variables miembro                                                      /  
///////////////////////////////////////////////////////////////////////////    
private final int nCampos = 8; //numero de campos de datos expuestos. 

private String marca;//La marca del coche
private String matrícula; //La matricula del coche
private String descrip; //descripcion vehiculo
private String nombreProp; //Nombre propietario del vehiculo
private String dni; //D.N.I del propietario (letra calculada con algoritmo)
private int precio; //Precio del vehiculo
private int numkilómetros; //numero de kilometros del vehiculo
private LocalDate fechaMat; // fecha de matriculacion del vehiculo

 /**************************************
 *  Metodos Getter y Setter.           *
 *  Algunos de ellos adaptan           *
 *  los String proporcionados          *
 *  como parametro a los valores tipo  *
 *  ajustados a cada campo.            *
 ***************************************/
    public String getMarca() {
        return marca;
    }

    public String getMatrícula() {
        return matrícula;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getNombreProp() {
        return nombreProp;
    }

    public String getDni() {
        return dni;
    }

    public int getPrecio() {
        return precio;
    }

    public int getNumkilometros() {
        return numkilómetros;
    }

    public LocalDate getFechaMat() {
        return fechaMat;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setMatrícula(String matrícula) {
        this.matrícula = matrícula;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public void setNombreProp(String nombreProp) {
        this.nombreProp = nombreProp;
    }

    public void setDni(String dni) {
        this.dni=dni;
    }

    public void setPrecio(String precio) {
        this.precio = Integer.parseInt(precio);
    }

    public void setNumKilometros(String númkilómetros) {
        this.numkilómetros = Integer.parseInt(númkilómetros);
    }

    public void setFechaMat(String fechaMat) {
        this.fechaMat = LocalDate.parse(fechaMat);
    }

    /**
    *  Constructor de la clase Vehiculo
    * @param args[] Se adjuntan todos los valores de los campos en un
    *        array de tipos String.
    */    
    public Vehiculo (String args[]){
            setFieldsVehiculo(args);
    }
    
   /**
    * Este metodo setea las variables miembro de la clase
    * @param args[] Se adjuntan todos los valores de los campos en tipos String
    */    
    public void setFieldsVehiculo(String args[]){
        setMarca(args[0]);
        setMatrícula(args[1]);
        setNumKilometros(args[2]);
        setFechaMat(args[3]);
        setDescrip(args[4]);
        setPrecio(args[5]);
        setNombreProp(args[6]);
        setDni(args[7]);  
    }
    /**
     * 
     * @return String[] el array de  Strings con los datos del vehiculo 
     */
    public String[] getFieldsVehiculo(){
       String[] datosVeh = new String[this.nCampos];
       
       datosVeh[0]=getMarca();
       datosVeh[1]=getMatrícula();
       datosVeh[2]=getNumkilometros()+""; //Moldeamos implicito a cadena con ""
       datosVeh[3]=getFechaMat().toString();// Para devolver la cadena fecha LocalDate
       datosVeh[4]=getDescrip();
       datosVeh[5]=getPrecio()+""; //Moldeamos implicito a cadena con ""
       datosVeh[6]=getNombreProp();
       datosVeh[7]=getDni();
       return datosVeh;
    }
    
    

    /**
    *  Retorna un entero, en base años, del periodo desde la compra a hoy.
    * @return int
    */
    public int get_Anios(){
          LocalDate startLocalDate = this.getFechaMat();
          LocalDate endLocalDate = LocalDate.now(); 
          return Period.between(startLocalDate, endLocalDate).getYears();
    }
}
