/**
 * La clase modela un sencillo pod�metro que registra informaci�n
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
     * Inicializa el pod�metro con la marca indicada por el par�metro.
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
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura / 100;
        sexo = queSexo;
        if (sexo == HOMBRE){
            longitudZancada = (Math.ceil(altura * ZANCADA_HOMBRE)) / 100;
        }
        else {
            longitudZancada = (Math.floor(altura * ZANCADA_MUJER)) / 100;
        }
        
    }

     /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFin � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
                            int horaFin) {
        switch (dia) {
            case 6:{
                totalPasosSabado += pasos;
                totalDistanciaFinSemana += pasos;
                break;
            } 
            case 7:{
                totalPasosDomingo += pasos;
                totalDistanciaFinSemana += pasos;
                break;
            } 
            default : {
                totalPasosLaborables += pasos;
                totalDistanciaSemana += pasos;
                break;
            }
        }
        // Se asume que hasta las 5 de la ma�ana es de noche
        if (horaInicio >= 2100 && horaInicio <= 0500 ){
            caminatasNoche ++;
        }
        tiempo += horaFin - horaInicio;
    }
    
     /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        
        System.out.println("Configuracion de podometro");
        System.out.println("**************************");
        System.out.println("");
        System.out.println("Altura: " + altura + "mtos" );
        if (sexo == HOMBRE){
            System.out.println("Sexo: HOMBRE");
        }
        else {
            System.out.println("Sexo: MUJER");
        }
        
        System.out.println("Longitud zancada: " + longitudZancada + "mtos");
        System.out.println("");
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        System.out.println("Estadisticas");
        System.out.println("*************************");
        System.out.println("Distancia recorrida toda la semana: " 
                            + totalDistanciaSemana);
        System.out.println("Distancia recorrida toda la semana: " 
                            + totalDistanciaFinSemana);
        System.out.println("");
        System.out.println("N� pasos dias laborales: " + totalPasosLaborables);
        System.out.println("N� pasos SABADO: " + totalPasosSabado);
        System.out.println("N� pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("");
        System.out.println("N� caminatas ralizadas a partir de las 21h: " 
                            + caminatasNoche);
        System.out.println("");
        System.out.println("Tiempo total caminado en la semana: " + tiempo);
        System.out.println("Dia/s con mas pasos caminados: " 
                            + diaMayorNumeroPasos());
        

    }

   

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
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
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
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
