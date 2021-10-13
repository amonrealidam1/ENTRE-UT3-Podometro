/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @author    - Aimar Monreal - 
 */
public class Podometro {
    
    final char HOMBRE = 'H';
    final char MUJER = 'M';
    final double ZANCADA_HOMBRE = 0.45;
    final double ZANCADA_MUJER = 0.41;
    final int SABADO = 6;
    final int DOMINGO = 7;
    String marca;
    double altura;
    char sexo;
    double longitudZancada;
    int totalPasosLaborables;
    int totalPasosSabado;
    int totalPasosDomingo;
    double totalDistanciaSemana;
    double totalDistanciaFinSemana;
    int tiempo;
    int caminatasNoche;
    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
    marca = queMarca;
    altura = 0;
    sexo = MUJER;
    longitudZancada = 0;
    totalPasosLaborables = 0;
    totalPasosSabado = 0;
    totalPasosDomingo = 0;
    totalDistanciaSemana = 0;
    totalDistanciaFinSemana = 0;
    tiempo = 0;
    caminatasNoche = 0; 
        
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {

         return marca;

    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if (sexo == HOMBRE){
            longitudZancada = (Math.ceil(altura * ZANCADA_HOMBRE));
        }
        else {
            longitudZancada = (Math.floor(altura * ZANCADA_MUJER));
        }
        
    }

     /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFin – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
                            int horaFin) {                       
        // Se asume que hasta las 5 de la mañana es de noche y a partir de 
        // cuentan como caminatas de dia
        
        if (horaInicio >= 2100 || horaInicio <= 0500 ){
            caminatasNoche ++;
        }
        
        switch (dia) {
            case SABADO:{
                totalPasosSabado += pasos;
                totalDistanciaFinSemana += (pasos * longitudZancada);
                break;
            } 
            case DOMINGO:{
                totalPasosDomingo += pasos;
                totalDistanciaFinSemana += (pasos * longitudZancada);
                break;
            } 
            default : {
                totalPasosLaborables += pasos;
                totalDistanciaSemana += (pasos * longitudZancada);
                break;
            }
        }
        
        horaInicio = (horaInicio / 100) * 60 + horaInicio % 100;
        horaFin = (horaFin / 100) * 60 + horaFin % 100;
        tiempo += horaFin - horaInicio;
    }
    
     /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        
        System.out.println("Configuracion de podometro");
        System.out.println("**************************");
        System.out.println("");
        System.out.println("Altura: " + (altura / 100)  + "mtos" );
        if (sexo == HOMBRE){
            System.out.println("Sexo: HOMBRE");
        }
        else {
            System.out.println("Sexo: MUJER");
        }
        
        System.out.println("Longitud zancada: " 
                        + (longitudZancada / 100) + "mtos");
        
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("");
        System.out.println("Estadisticas");
        System.out.println("*************************");
        System.out.println("Distancia recorrida toda la semana: " 
                            + ((totalDistanciaSemana + 
                            totalDistanciaFinSemana) / 100000)+ "Km");
        System.out.println("Distancia recorrida fin de semana: " 
                            + (totalDistanciaFinSemana / 100000) + "Km");
        System.out.println("");
        System.out.println("Nº pasos dias laborales: " + totalPasosLaborables);
        System.out.println("Nº pasos SABADO: " + totalPasosSabado);
        System.out.println("Nº pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("");
        System.out.println("Nº caminatas ralizadas a partir de las 21h: " 
                            + caminatasNoche);
        System.out.println("");
        System.out.println("Tiempo total caminado en la semana: " 
                            + tiempo / 60 + "h y " + (tiempo % 60 ) + "m.");
        

    }

   

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String aux = "";
        if (totalPasosLaborables >= totalPasosSabado && totalPasosLaborables 
        >= totalPasosDomingo){
            aux += "LABORABLES " ;
        }
        if (totalPasosSabado >= totalPasosLaborables && totalPasosSabado 
        >= totalPasosDomingo){
            aux += "SABADO " ;
        }
        if (totalPasosDomingo >= totalPasosLaborables && totalPasosDomingo 
        >= totalPasosSabado){
            aux += "DOMINGO " ;
        }
        return aux;
        
    }
    
    
    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

}
