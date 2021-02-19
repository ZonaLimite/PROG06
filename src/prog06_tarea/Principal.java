/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog06_tarea;

import java.text.ParseException;
import java.util.Scanner;
import utilidades.UtilChecks;

/**
 * Clase Principal. Motor app.
 * @author F.J. BOGA
 */
public class Principal {
    //VARIABLES MIEMBRO//
    Concesionario conces; // variable miembro concesionario
    
    /**
     * Metodo main ambito estatico
     * solo instancia la clase llamando al metodo de arranque.
     * @param args no se utilizan
     */
    public static void main(String args[]){
        Principal p = new Principal();
        p.startup();
    }
    
    /**
     * Constructor clase Principal.
     */
    public Principal(){
  
    }
    
    /**
    * Metodo Principal.Motor app.
    * @param args No es utilizado
    */
    private void startup(){
        boolean seguir=true;//variable control bucle.
        int opcion;//variable recogida opcion de usuario en menu principal.
        conces = new Concesionario(); //Instanciacion del Concesionario
        
        //bucle principal
        while(seguir){
            opcion=showMenu();
            
            switch(opcion){

                case 1 -> {
                    //validamos datos,solicitamos inserccion y esperamos result
                    switch(conces.insertarVehiculo(validarDatosVehiculo())){
                        case 0 :
                            //Vehiculo admitido
                            say("¡ Vehiculo registrado correcto !");
                            say("  Vehiculos en concesionario: "+ conces.indiceControlInserccionVehiculos);
                            break;
                        case -1:
                            //Concesionario lleno
                            say("No hay mas espacio para vehiculos");
                            break;
                        case -2:
                            //Matricula ya dada de alta
                            say("Esta matricula ya esta registrada");
                            break;
                    }
                    break;
                }
                //Vuelca la informacion de todos los vehiculos
                //desde una rutina propia de la clase Concesionario.
                case 2 -> {
                    conces.listaVehículos();
                    break;
                }
                //Vuelca la informacion de un  vehiculo, si existe
                case 3 -> {
                    vuelcaVehiculo();
                }
                //Actualizar kms
                case 4 -> {
                    say(modificarKms());
                }
                case 5 -> seguir=false;
            }
        }
        conces=null;
    }
    /**
    * modificarKms : Se solicitará al usuario por teclado una matrícula y un número de kilómetros
    * Si el vehículo con esa matrícula existe, se actualizará su número de kms al valor introducido.
    * @return String ,devuelve un string con el resultado de la operacion.
    */
    private String modificarKms(){
        String result;
        Scanner sc = new Scanner(System.in);
        System.out.print("* Introduzca matricula a buscar? :");
        String sMatricula = sc.nextLine();
        System.out.print("* Introduzca nuevo valor Kms ? :");        
        String sKms = sc.nextLine();
        if(conces.actualizaKms(sMatricula, sKms)){
             result ="* Vehiculo actualizado. (Kms " + sKms+ ")";
        }else{
            result ="* Error. No existe vehículo con la matrícula introducida ...";
        }
        return result;
    }
    /**
     * VuelcaVehiculo obtiene la matricula del usuario y busca y muestra el vehiculo
     * o un mensaje de que no existe
     */
    private void vuelcaVehiculo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("* Introduzca matricula a buscar? :");
        String sMatricula = sc.nextLine();
        String resp = conces.buscaVehiculo(sMatricula);
        if (resp==null){
            say("* No existe vehículo con la matrícula introducida ...");
        }else{
            String[] datosVeh = resp.split(",");
            say("******** Vehiculo encontrado : ************************");
            say("* Marca     : " + datosVeh[0]);
            say("* Matricula : " + datosVeh[1]);
            say("* Precio    : " + datosVeh[5]);
            say("*******************************************************");
        }
    }
       
    /**
    * Muestra el menu principal y devuelve la opcion elegida
    * @return short
    */    
    public static int showMenu() {
        int opcion=0;
        System.out.println("******************************");
        System.out.println("*  1. Nuevo vehiculo         *");
        System.out.println("*  2. Listar Vehiculos       *");
        System.out.println("*  3. Buscar un vehiculo     *");
        System.out.println("*  4. Modificar kms vehiculo *");
        System.out.println("*  5. Salir                  *");
        System.out.println("******************************");
        System.out.print  ("*  Introduzca Opcion?: ");
        Scanner sc = new Scanner(System.in);
       
        try{
            opcion = sc.nextInt(); 
        }catch(Exception e){
            Principal.showMenu();
       }
       return opcion;
    }
    /**
     * El metodo say es una standarizacion de los mensajes de salida
     * @param mensaje El texto a mostrar en el interface de salida
     */    private void say(String mensaje){
                System.out.println("* " + mensaje);
    }

    /**
     * Metodo para validar los datos de nuevos vehiculos en el concesionario
     * @return String[] un array con todos los datos validados.
     */
    private String[] validarDatosVehiculo(){
        //Guia solicitud de parametros para constructor de objeto vehiculo.
        String paramName[] = {"marca del vehiculo",
                              "matricula",
                              "Numero de km's [ > 0]",
                              "Fecha de matriculacion. Formato[aaaa-mm-dd]",
                              "Descripcion vehiculo",
                              "Precio (P.V.P)",
                              "Nombre Apellido1 Apellido2",
                              "Nº DNI (con letra)"};

        //Array recogida de valores del usuario.
        //El orden de los parametros que se recogen es el esperado
        //en el metodo de instanciacion de un nuevo vehiculo.
        // Vehiculo.setFieldsVehiculo(String args[])       
        String paramValues[]= new String[paramName.length];

        Scanner sc = new Scanner(System.in);
        int i=0; //indice de array recogida valores
        for(String param : paramName){
            boolean valorValido;
            do{
                valorValido=true;
                try {
                    System.out.println("Introduzca valor para parametro ["+param+"]:");
                    String valorParam = sc.nextLine();
                    UtilChecks.validarParametro(param,valorParam);
                    paramValues[i]=valorParam;
                } catch (ParseException ex) {
                    System.out.println("Error:  "+ex.getMessage());
                    valorValido=false;
                }
            }while(!valorValido);
            i++;
        }
        return paramValues;
    }
    
}