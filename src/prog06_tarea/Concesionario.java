/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog06_tarea;


/**
 * Esta clase representará un concesionario de venta de coches de segunda mano.
 * El concesionario será capaz de gestionar un máximo de 50 coches.
 * @author F.J. BOGA
 */
public class Concesionario {
    //Variables miembro
    final int maxNumeroVehiculos = 50;//Maximo numero de vehiculos admitidos
    Vehiculo[] aVehiculos ; //estructura contenedora de vehiculos
    int indiceControlInserccionVehiculos; // Indica el indice apuntador de llenado de nuestro array
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    /**
     * Constructor de la clase Concesionario
     */
    public Concesionario() {
       //instanciacion del array contenedor de vehiculos
       aVehiculos = new Vehiculo[maxNumeroVehiculos];
       indiceControlInserccionVehiculos=0;
    }
    

    
    /**
     * Busca el vehículo en el concesionario.
     * @param vehiculoBuscado la cadena de la matricula del vehiculo a buscar.
     * @return String la cadena del vehiculo encontrado o null si no existe.
     */
    public String buscaVehiculo(String vehiculoBuscado){
        String sVehiculo=null; 
        for(Vehiculo veh : this.aVehiculos){
            //Si este es el vehiculo buscado
            //preparamos la cadena solicitada
            //(Se ha modificado la clase vehiculo para que devuelva un array
            //de todas las propiedades del vehiculo).
            if(veh==null) {
                //descartamos cualquier posicion de array aun no seteada
            } else if(veh.getMatrícula().equals(vehiculoBuscado)){
                //formateamos la cadena de salida con los valores separados por comas
                sVehiculo = this.preparaCadenaDatos(veh.getFieldsVehiculo());
            }
        }
        return sVehiculo;
    }
    
    /**
    * insertarVehiculo: trata de insertar un vehiculo en el concesionario.
    * @param datos es un array de Strings con todos los datos de un vehiculo.
    * @return int 0 si se hizo con éxito, -1 si el concesionario está lleno y -2 si la matrícula ya existe.
    */
    public int insertarVehiculo(String[] datos){
        int result;
        if(this.indiceControlInserccionVehiculos==this.maxNumeroVehiculos-1){
            result=-1;
        }else if(compruebaMatricula(datos[1])){//datos[1] contiene la matricula
            result=-2;
        }else{
            Vehiculo nuevoVeh = new Vehiculo(datos);
            this.aVehiculos[indiceControlInserccionVehiculos]=nuevoVeh;
            indiceControlInserccionVehiculos++;
            result=0; 
        }   
        return result;
    }
    /**
     * Lista por pantalla los datos de todos los vehículos del concesionario
     * mostrando marca, matrícula, precio, kilómetros y descripción por cada vehículo.
     */
    public void listaVehículos(){
        //Recorremos todos los vehiculos en el concesionario

        say("***********************************************************");
        say("*      Listado de vehiculos en este Concesionario         *");
        say("***********************************************************");
        int contVehiculos=0;
            for(Vehiculo veh: this.aVehiculos){
                if(veh==null){
                    
                }else{
                    say("* Vehiculo nº : " + contVehiculos);
                    say("* Marca     : " + veh.getMarca());
                    say("* Matricula : " + veh.getMatrícula());
                    say("* Precio    : " + veh.getPrecio());
                    say("* Kilometros: " + veh.getNumkilometros());
                    say("* Descrip.  : " + veh.getDescrip());
                    say("*******************************************************");
                    contVehiculos++;
                }
            }
    }

    /**
     * busca el vehículo con cuya matrícula coincida y actualiza sus kilómetros.
     * @param matricula la matricula del vehiculo a actualizar kms.
     * @param kilometers el valor de kilómetros a actualizar.
     * @return boolean true si se hizo con éxito y false en caso contrario.
     */
    public boolean actualizaKms (String matricula, String kilometers){
        boolean result=false;
        // recorro todos los objetos vehiculo existentes
        for(var veh : this.aVehiculos){
            if(veh==null){// No todas las celdas del array tienen un vehiculo instanciado
                
            }else{
                if(veh.getMatrícula().equals(matricula)){//Existe esta matricula.
                    veh.setNumKilometros(kilometers);//Actualizo Kms de este vehiculo.
                    result=true; //informo de ok actualizados
                }
            }
        }

        return result;
        
    }
    /**
     * Comprueba matriculas repetidas es un algoritmo que comprueba
     * si la matricula aportada como parametro, esta asignada ya a
     * algun vehiculo.
     * @param matricula es la matricula a checkear
     * @return true si existe esa matricula ya en le concesionario, false si no.
     */
    private boolean compruebaMatricula(String matricula){
        boolean matriculaExiste=false;
        // recorro todos los objetos vehiculo existentes
        for(var veh : this.aVehiculos){
            if(veh==null){// No todas las celdas del array tienen un vehiculo instanciado
                
            }else{
                if(veh.getMatrícula().equals(matricula)) matriculaExiste=true; 
            }
        }
        return matriculaExiste;
    }
    /**
     * un algoritmo para entregar una cadena con los datos separados por comas
     * @param String[] el array con los datos del vehiculo.
     * @return String la cadena con todos los datos del vehiculo.
     */
    private String preparaCadenaDatos(String[] datos){
        String sOutput ; //la cadena a formatear de salida con los datos
        sOutput="";
      
        // controlcoma sirve para no colocar "coma"  en en el ultimo campo
        // de datos
        int controlComa = 0;
        for (String dato : datos){
            sOutput += dato;
            if(controlComa<datos.length-1)sOutput += ",";
            controlComa++;
        }

        return sOutput;
    }
    
    /**
     * El metodo say es una standarizacion de los mensajes de salida
     * @param mensaje El texto a mostrar en el interface de salida
    */
    private void say(String mensaje){
        System.out.println("* " + mensaje);
    }
    }
