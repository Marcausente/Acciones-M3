import java.util.Scanner;
import java.util.Random;
public class programa {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);{
            Random random = new Random();
            int menu = 0, evento = 0, balance = 2000, auxiliar = 0, cantidadIndividual = 0;
            String[] compañias = {"Microsoft", "Nintendo", "Intel", "Apple", "Samsung", "Sony", "Nvidia", "Valve", "Huawei", "Google", "Cysco Systems", "Adobe", "Netflix", "Tencent", "Meta"}, cartera = new String[15];
            String red = "\u001B[91m";
            String green = "\u001B[92m";
            String resetColorCode = "\u001B[0m";
            boolean[] estado = new boolean[15];
            int[] cantidad = new int[15], precio = {460, 360, 280, 450, 290, 300, 500, 300, 290, 390, 190, 330, 490, 500, 300}, balanceIndividual = new int[15];

            System.out.println(green+"======================="+resetColorCode); //Print del titulo del programa en color verde, simplemente estetico
            System.out.println(green+" WELCOME TO STONKS.EXE "+resetColorCode);
            System.out.println(green+"======================="+resetColorCode);
            do { //Do general para el control de errores y el retorno al menu general

                System.out.println("Bienvenido al trader de Marc y Adrián, elige que quieres hacer:                         Balance: " +green+ balance+resetColorCode); //Printeo del dinero que se tiene
                System.out.println("1. Compra de acciones"); //Menu general del programa
                System.out.println("2. Venta de acciones");
                System.out.println("3. Prediccion");
                System.out.println("4. Cartera");
                System.out.println("5. Salir del programa");
                menu = input.nextInt();
                while(menu < 1 || menu > 5){
                    System.out.println("Opción incorrecta. Por favor, escoge una opción válida"); //Control de errores del switch del menú para la selección
                    menu = input.nextInt();
                }
                switch (menu){
                    case 1:
                        System.out.println("¿De que compañia quieres comprar acciones?"); //Este for te printea todas las compañias de las que dispone el programa
                        for (int i = 0; i < compañias.length; i++) {
                            auxiliar = i + 1;
                            System.out.println(auxiliar + ". " + compañias[i] + "     " + precio[i] + "€");
                        }
                        System.out.println("0. Salir");
                        menu = input.nextInt();
                        while (menu < 0 || menu > 15) { //Control de errores referente a la eleccion de empresa para su compra
                            System.out.println("Opción incorrecta. Por favor, escoge una opción válida");
                            menu = input.nextInt();
                        }
                        if (menu == 0) {
                        } else {
                            auxiliar = menu - 1; //En esta variable guardamos la posición exacta en el array donde esta guardada la elección del usuario.

                            System.out.println("¿Cuantas acciones de " + compañias[auxiliar] + " quieres comprar?"); //Printeo y Input sobre la compra de las acciones
                            auxiliar = input.nextInt();

                            if (balance < (auxiliar * precio[menu - 1])) { //Calculo para ver si el dinero que tienes en balance es suficiente para hacer la compra
                                System.out.println("No tienes suficiente para comprar la/s accion/es"); //En caso de que no te printea este mensaje
                            } else {
                                balance = balance - (auxiliar * precio[menu - 1]); //En caso de que si, te resta lo que cuesta del balance total
                                cartera[menu - 1] = compañias[menu - 1]; //Te guarda el nombre de la compañia que has comprado en la cartera
                                balanceIndividual[menu - 1] = auxiliar * precio[menu - 1]; //Guarda el precio individual de las acciones de la compañia
                                cantidad[menu - 1] = auxiliar; //Guarda el numero de acciones que tienes compradas

                            }
                        }
                        menu = 0;
                        break;

                    case 2:
                        auxiliar = 0;
                        for (int i=0; i<cartera.length; i++) { //Te comprueba si tienes alguna accion comprada anteriormente
                            if (cartera[i] != null) {
                                auxiliar = 1; //En caso de que te detecte una acción el auxiliar pasara a ser uno
                                break;
                            }
                        }
                        if (auxiliar == 0){ //En caso de que no tengas acciones no te deja hacer nada en venta y te devuelve al menu general
                            System.out.println("No tienes ninguna accion");
                        }else {
                            System.out.println("¿De que compañia quieres vender acciones?: ");
                            System.out.println("EMPRESA" + "                " + "CANTIDAD" + "              " + "VALOR INDIVIDUAL");
                            for (int i = 0; i < cartera.length; i++) { //Recorre el array donde tienes guardadas las empresas, printeandote las que tengas con su cantidad, valor y nombre
                                if (cartera[i] != null) {

                                    System.out.println(i + 1 + ". " + cartera[i] + "               " + cantidad[i] + "             " + balanceIndividual[i]);
                                }
                            }
                            System.out.println("0. Salir");
                            menu = input.nextInt();
                            if (menu < 0 || menu > 15) {  //Control de errores
                                System.out.println("Opción incorrecta, escoge una opción válida");
                                menu = input.nextInt();
                            }
                            auxiliar = menu - 1;
                            if (menu == 0) {

                            } else {
                                if (cantidad[auxiliar] != 1) {//Comprueba si tienes más de una acción, para preguntarte cuantas quieres vender
                                    auxiliar = cantidad[(menu - 1)];
                                    System.out.println("¿Cuantas acciones quieres vender?"); //Te pregunta las acciones que quieres vender
                                    cantidadIndividual = input.nextInt();
                                    while (cantidadIndividual <= 0 || cantidadIndividual > auxiliar) { //Contro, de errores por si pones más acciones de las que tienes
                                        System.out.println("¿Cantidad incorrecta, escoge una cantidad correcta");
                                        cantidadIndividual = input.nextInt();
                                    }

                                    balance = balance + (balanceIndividual[(menu - 1)] / auxiliar * cantidadIndividual); //Mira cuantas acciones tienes y le suma al balance actual el precio que vas a vender.
                                    balanceIndividual[menu - 1] = balanceIndividual[menu - 1] - (balanceIndividual[menu - 1] / auxiliar * cantidadIndividual); //Mira valor total de las acciones antes de vender y le resta al balance individual lo que has vendido
                                    cantidad[menu - 1] = cantidad[menu - 1] - cantidadIndividual; //Mira cuantas acciones quieres vender y se la resta al numero de acciones que tienes
                                    if (cantidad[menu - 1] == 0) { //Mira cuantas acciones quieres vender y se la resta al numero de acciones que tienes
                                        cartera[menu - 1] = null;
                                    }
                                } else {
                                    balance = balance + (balanceIndividual[(menu - 1)]); //El calculo de arriba pero simplificado en caso de que solo vendas una accion individual
                                    balanceIndividual[menu - 1] = 0;
                                    cantidad[menu - 1] = 0;
                                    cartera[menu - 1] = null;
                                }

                            }
                        }
                        menu = 0;
                        break;

                    case 3:
                        System.out.println("Menu generador de eventos:"); //Acceso al menu generador de eventos
                        System.out.println("1. Generar un evento nuevo");
                        System.out.println("2. Salir");
                        menu = input.nextInt();
                        switch (menu){
                            case 1:
                                evento = random.nextInt(10); //Randomizador de eventos, elige una variable del 0 al 9
                                switch (evento){ //Depende del resultado del random anterior saca uno de los siguientes eventos
                                    case 0:
                                        System.out.println("Han puesto una bomba en varias oficinas de Silicon Valley, las siguientes acciones pierden valor: ");
                                        System.out.println("Netflix: "+red+"-80"+resetColorCode); //Print de las empresas que sufren el evento, te printea las restas en rojo
                                        System.out.println("Apple: "+red+"-100"+resetColorCode);
                                        System.out.println("Intel: "+red+"-20"+resetColorCode);
                                        System.out.println("Nvidia: "+red+"-50"+resetColorCode);
                                        System.out.println("Meta: "+red+"-60"+resetColorCode);
                                        System.out.println("Adobe "+red+"-10"+resetColorCode);
                                        System.out.println("Google "+red+"-20"+resetColorCode);
                                        precio[12] = precio[12]-80; //Resta en este caso del resultado del evento a la porpia variable que se ve afectada, en este caso Netflix
                                        if (cartera[12] != null){ //Baja el precio de la accion de la cartera en caso de que lo tengas
                                            balanceIndividual[12] = balanceIndividual[12]-80;
                                        }
                                        precio[3] = precio[3]-100; //Realiza lo mismo que antes para todas las acciones afectadas
                                        if (cartera[3] != null){
                                            balanceIndividual[3] = balanceIndividual[3]-100;
                                        }
                                        precio[2] = precio[2]-20;
                                        if (cartera[2] != null){
                                            balanceIndividual[2] = balanceIndividual[2]-20;
                                        }
                                        precio[6] = precio[6]-50;
                                        if (cartera[6] != null){
                                            balanceIndividual[6] = balanceIndividual[6]-50;
                                        }
                                        precio[14] = precio[14]-60;
                                        if (cartera[14] != null){
                                            balanceIndividual[14] = balanceIndividual[14]-60;
                                        }
                                        precio[11] = precio[11]-10;
                                        if (cartera[11] != null){
                                            balanceIndividual[11] = balanceIndividual[11]-10;
                                        }
                                        precio[9] = precio[9]-20;
                                        if (cartera[9] != null){
                                            balanceIndividual[9] = balanceIndividual[9]-20;
                                        }
                                        break;
                                    case 1:
                                        System.out.println("El mercado asiatico entra en alza tras la subida de impuestos de Occidente"); //Segundo evento
                                        System.out.println("Las siguientes empresas ganan valor: ");
                                        System.out.println("Sony "+green+"+20"+resetColorCode); //Print de las empresas que sufren el evento, te printea las restas en rojo
                                        System.out.println("Nintendo: "+green+"+50"+resetColorCode);
                                        System.out.println("Tencent: "+green+"+40"+resetColorCode);
                                        System.out.println("Samsung: "+green+"+30"+resetColorCode);
                                        precio[5] = precio[5]+20;
                                        if (cartera[11] != null){
                                            balanceIndividual[11] = balanceIndividual[11]-10;
                                        }
                                        precio[1] = precio[1]+50;
                                        if (cartera[11] != null){
                                            balanceIndividual[11] = balanceIndividual[11]-10;
                                        }
                                        precio[13] = precio[13]+40;
                                        if (cartera[11] != null){
                                            balanceIndividual[11] = balanceIndividual[11]-10;
                                        }
                                        precio[4] = precio[4]+30;
                                        if (cartera[11] != null){
                                            balanceIndividual[11] = balanceIndividual[11]-10;
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Los microprocesadores suben de precio, las siguientes empresas pierden valor: "); //Tercer evento
                                        System.out.println("Nvidia"+red+" -20"+resetColorCode);
                                        System.out.println("Intel"+red+" -50"+resetColorCode);
                                        precio[6] = precio[6]-20;
                                        if (cartera[6] != null){
                                            balanceIndividual[6] = balanceIndividual[6]-20;
                                        }
                                        precio[2] = precio[2]-50;
                                        if (cartera[2] != null){
                                            balanceIndividual[2] = balanceIndividual[2]-50;
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Se encuentra una gran veta de materiales de fabricacion de componentes, las siguientes empresas ganan valor: ");
                                        System.out.println("Nvidia: "+green+"+30"+resetColorCode); //Cuarto evento
                                        System.out.println("Intel: "+green+" +60"+resetColorCode);
                                        System.out.println("Cisco: "+green+"+30"+resetColorCode);
                                        precio[6] = precio[6]+30;
                                        if (cartera[6] != null){
                                            balanceIndividual[6] = balanceIndividual[6]+30;
                                        }
                                        precio[2] = precio[2]+60;
                                        if (cartera[2] != null){
                                            balanceIndividual[2] = balanceIndividual[2]+60;
                                        }
                                        precio[10]= precio[10]+30;
                                        if (cartera[10] != null){
                                            balanceIndividual[10] = balanceIndividual[10]+30;
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Ha salido una nueva generación de tarjetas graficas, el mercado de videojuegos entra en alza: ");
                                        System.out.println("Microsoft: "+green+"+10"+resetColorCode); //Quinto evento
                                        System.out.println("Nvidia: "+green+"+60"+resetColorCode);
                                        System.out.println("Valve:"+green+" +30"+resetColorCode);
                                        precio[0] = precio[0]+10;
                                        if (cartera[0] != null){
                                            balanceIndividual[0] = balanceIndividual[0]+10;
                                            estado[12] = true;
                                        }
                                        precio[6] = precio[6]+60;
                                        if (cartera[6] != null){
                                            balanceIndividual[6] = balanceIndividual[6]+60;
                                            estado[6] = true;
                                        }
                                        precio[7] = precio[7]+30;
                                        if (cartera[7] != null){
                                            balanceIndividual[7] = balanceIndividual[7]+30;
                                            estado[7] = true;
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Los servicios de streaming alzan en cuanto a popularidad, las siguientes empresas ganan valor: ");
                                        System.out.println("Netflix:"+green+" +50"+resetColorCode); //Sexto evento
                                        System.out.println("Microsoft:"+green+" +10"+resetColorCode);
                                        precio[12] = precio[12]+50;
                                        if (cartera[12] != null){
                                            balanceIndividual[12] = balanceIndividual[12]+50;
                                            estado[12] = true;
                                        }
                                        precio[0] = precio[0]+10;
                                        if (cartera[0] != null){
                                            balanceIndividual[0] = balanceIndividual[0]+10;
                                            estado[0] = true;
                                        }
                                        break;
                                    case 6:
                                        System.out.println("El sector de videojuegos se estanca y los precios siguien subiendo, las siguientes empresas pierden: ");
                                        System.out.println("Valve"+red+" -40"+resetColorCode); //Septimo evento
                                        System.out.println("Nintendo:"+red+" -20"+resetColorCode);
                                        System.out.println("Sony:"+red+" -20"+resetColorCode);
                                        System.out.println("Microsoft"+red+" -10"+resetColorCode);
                                        precio[7] = precio[7]-40;
                                        if (cartera[7] != null){
                                            balanceIndividual[7] = balanceIndividual[7]-40;
                                            estado[7] = false;
                                        }
                                        precio[1] = precio[1]-20;
                                        if (cartera[1] != null){
                                            balanceIndividual[1] = balanceIndividual[1]-20;
                                            estado[1] = false;
                                        }
                                        precio[5] = precio[5]-20;
                                        if (cartera[5] != null){
                                            balanceIndividual[5] = balanceIndividual[5]-20;
                                            estado[5] = false;
                                        }
                                        precio[0] = precio[0]-10;
                                        if (cartera[0] != null){
                                            balanceIndividual[0] = balanceIndividual[0]-10;
                                            estado[0] = false;
                                        }
                                        break;
                                    case 7:
                                        System.out.println("La competencia de software de ofimatica se dispara, las siguientes empresas pierden potencial: ");
                                        System.out.println("Adobe:"+red+" -20"+resetColorCode); //Octavo evento
                                        System.out.println("Google:"+red+" -30"+resetColorCode);
                                        System.out.println("Microsoft:"+red+" -20"+resetColorCode);
                                        precio[2] = precio[11]-20;
                                        if (cartera[2] != null){
                                            balanceIndividual[2] = balanceIndividual[2]-20;
                                            estado[2] = false;
                                        }
                                        precio[9] = precio[9]-30;
                                        if (cartera[9] != null){
                                            balanceIndividual[9] = balanceIndividual[9]-30;
                                            estado[9] = false;
                                        }
                                        precio[0] = precio[0]-20;
                                        if (cartera[0] != null){
                                            balanceIndividual[0] = balanceIndividual[0]-20;
                                            estado[0] = false;
                                        }
                                        break;
                                    case 8:
                                        System.out.println("Los chips de fabricacion de los telefonos moviles se disparan en preico, las siguientes empresas pierden valor: ");
                                        System.out.println("Google:"+red+" -10"+resetColorCode); //Noveno evento
                                        System.out.println("Apple:"+red+" -60"+resetColorCode);
                                        System.out.println("Samsung:"+red+" -60"+resetColorCode);
                                        precio[9] = precio[9]-10;
                                        if (cartera[9] != null){
                                            balanceIndividual[9] = balanceIndividual[9]-10;
                                            estado[9] = false;
                                        }
                                        precio[3] = precio[3]-60;
                                        if (cartera[3] != null){
                                            balanceIndividual[3] = balanceIndividual[3]-60;
                                            estado[3] = false;
                                        }
                                        precio[4] = precio[4]-60;
                                        if (cartera[4] != null){
                                            balanceIndividual[4] = balanceIndividual[4]-60;
                                            estado[4] = false;
                                        }
                                        break;
                                    case 9:
                                        System.out.println("Se detecta una vulnerabilidad muy grave que afecta a muchos sistemas Operativos, las siguientes empresas pierden valor: ");
                                        System.out.println("Microsoft"+red+" -50"+resetColorCode); //Decimo evento
                                        System.out.println("Apple"+red+" -30"+resetColorCode);
                                        System.out.println("Google"+red+" -20"+resetColorCode);
                                        precio[0] = precio[0]-50;
                                        if (cartera[0] != null){
                                            balanceIndividual[0] = balanceIndividual[0]-50;
                                            estado[0] = false;
                                        }
                                        precio[3] = precio[3]-30;
                                        if (cartera[3] != null){
                                            balanceIndividual[3] = balanceIndividual[3]-30;
                                            estado[3] = false;
                                        }
                                        precio[9] = precio[9]-20;
                                        if (cartera[9] != null){
                                            balanceIndividual[9] = balanceIndividual[9]-20;
                                            estado[9] = false;
                                        }
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("Saliedo..."); //Salir del menu otra vez al menu principal, segunda elección del menu principal
                                break;
                            default: //Control de errores
                                System.out.println("Error, elige un numero valido");
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("Bienvenido a tu cartera");
                        System.out.println("Balance Total: " + green+balance+resetColorCode); //Printeo del balance total
                        auxiliar = 0;
                        for (int i=0; i<cartera.length; i++) { //Recorre el array de cartera para ver si has comprado algo
                            if (cartera[i] != null) {
                                auxiliar = 1; //En caso de que tengas algo comprado te iguala la variable a uno
                                break;
                            }
                        }
                        if (auxiliar == 0) { //En caso de que no tengas nada printea el siguiente mensaje y te devuelve al menu
                            System.out.println("Actualmente no tienes ninguna accion");
                        }else{ //En caso de que si tengas algo hace lo siguiente:
                            System.out.println("EMPRESA" + "                " + "CANTIDAD" + "              " + "VALOR INDIVIDUAL");
                            for (int i = 0; i < cartera.length; i++) {  //Recorre la variable de cartera para ver que tienes comrpado exactamente
                                if (cartera[i] != null) { //Si es null no te la printea
                                    System.out.println(i+1 + ". " + cartera[i] + "               " + cantidad[i] + "             " + balanceIndividual[i]); //Si tienes esa variable te printea los datos sobre ella
                                }
                            }
                        }

                        break;
                    case 5: //Salida del programa
                        System.out.println("¡Gracias por utilizar nuestro Trader");
                        System.out.println("Saliendo...");
                        break;
                }
            }while (menu!=5); //Fin del do inicial referente al menú principal

        }
    }
}
