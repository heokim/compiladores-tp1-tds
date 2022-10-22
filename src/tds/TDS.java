package tds;

/**
 * @author Andres Heo Kim, CI.: 5.574.180
 */
public class TDS {

    /*
        Entradas Validas
        "150,35,479,7"
        "17,55"
        "2000"
        "0"
        "0000001,0002,003"
    
        Entradas con Error
        ",12"
        "a"
        "2a"
        "1,a24"
        "200,20,4a1"
     */
    public static String entrada = "0000001,0002,003";

    // Entrada para habilitar mensaje de logs
    public static final boolean PRINT_INFO_LOGS = false; 

    // Puntero para ir consuimendo la entrada
    static int LINEA = 0;

    // Metodo del Traductor
    /**
     * Metodo del match, encargado de machear la entrada y controlar que este
     * correcto, en caso contrario lanza error
     *
     * @param terminal entrada que se quiere envaluar con el caracter que esta
     * sobre el input
     */
    static void match(char terminal) {
        if(PRINT_INFO_LOGS) System.out.println("\nMatch();  terminal='" 
                + terminal + "', input = '" + input() + "'");
        if (terminal != input()) {
            error();
        }
        LINEA++;
    }

    static char input() {
        if (LINEA < entrada.length()) {
            if(PRINT_INFO_LOGS) System.out.print(" " + entrada.charAt(LINEA));
            return entrada.charAt(LINEA);
        } else {
            if(PRINT_INFO_LOGS) System.out.print(" $");
            return '$';
        }
    }

    /**
     * Metodo para imprimir mendaje de "Error de Sintaxis"
     */
    static void error() {
        System.err.println("Error de Sintaxis en la linea " + LINEA 
                + " sobre el caracter: '" + input() + "'");
        System.exit(0);
    }

    // Clase Auxiliar para retornar 2 variables
    public static class Valores {

        String numero;
        int contador;

        @Override
        public String toString() {
            return "n: " + this.numero + ", c:" + this.contador;
        }
    }


    // Metodo Main del Traductor Dirigito por la Sintaxis
    public static void main(String[] args) {
        TDS();
    }

    /*
        BNF: TDS -> LISTA
        PRIMERO(BNF) = {0 ... 9}
        Regla Semantica:
            LINEA < entrada.length() ? error() : print(LISTA.numero/LISTA.contador, LISTA.contador)
    */
    static void TDS() {
        entrada = entrada.replaceAll(" ", "");
        if(PRINT_INFO_LOGS) System.out.println("entrada sin espacios: " + entrada);
        
        Valores retorno = LISTA();
        if(PRINT_INFO_LOGS) System.out.println("Procesado hasta la linea: " + LINEA);

        if (LINEA < entrada.length()) {
            error();
        } else {
            if(PRINT_INFO_LOGS) System.out.println("Lista(): " + retorno);
            int promedio = Integer.parseInt(retorno.numero) / retorno.contador;
            System.out.println("TDS();    promedio: " + promedio + "  cant. precios leidos: " + retorno.contador);
        }
    }

    /*  
        BNF: LISTA -> PRECIO R1
        PRIMERO(LISTA) = {0 ... 9}
        Regla Semantica:
            LISTA.numero = PRECIO.numero1 + R1.numero2 != "" ? R1.numero2 : 0
            LISTA.contador = 1 + R1.contador
     */
    static Valores LISTA() {
        Valores lista = new Valores();
        String numero1, numero2;
        int contador;

        if (input() == '0' || input() == '1' || input() == '2' || input() == '3' || input() == '4' ||
                input() == '5' || input() == '6' || input() == '7' || input() == '8' || input() == '9') {
            numero1 = PRECIO();
            Valores r1 = R1();
            numero2 = r1.numero;
            contador = r1.contador;
            lista.numero = String.valueOf(Integer.parseInt(numero1) + (numero2 != "" ? Integer.parseInt(numero2) : 0));
            lista.contador = 1 + contador;
            return lista;
        } else {
            error();
            return null;
        }
    }

    /*
        BNF: R1 -> , LISTA 
        PRIMERO(R1) = {,} U {e} 
        Regla Semantica:
            R1 -> , LISTA    R1.numero = LISTA.numero &&  R1.contador = LISTA.contador
            R1 -> e          R1.numero = "" && R1.contador = 0
     */
    static Valores R1() {
        Valores r1 = new Valores();
        if (input() == ',') {
            match(',');
            Valores lista = LISTA();
            if(PRINT_INFO_LOGS) System.out.println("Lista(): " + lista);
            r1.numero = lista.numero;
            r1.contador = lista.contador;
            return r1;
        } else {
            if(PRINT_INFO_LOGS) System.out.println("Fin de Recurcion en R1");
            r1.numero = "";
            r1.contador = 0;
            return r1;
        }
    }

    /*
        BNF: PRECIO -> DIGITO R2
        PRIMERO(PRECIO) = {0 ... 9}
        Regla Semantica:
            PRECIO.numero = DIGITO.numero1 || R2.numero2
     */
    static String PRECIO() {
        String numero, numero1, numero2;
        if (input() == '0' || input() == '1' || input() == '2' || input() == '3' || input() == '4' ||
                input() == '5' || input() == '6' || input() == '7' || input() == '8' || input() == '9') {
            numero1 = DIGITO();
            numero2 = R2();
            numero = numero1.concat(numero2);
            return numero;
        } else {
            error();
            return null;
        }
    }

    /*
        BNF: R2 -> PRECIO | e
        PRIMERO(R2) = {0 ... 9, e}
        Regla Semantica:
            R2 -> PRECIO    R2.numero = PRECIO.numero
            R2 -> e         R2.numero = ""
     */
    static String R2() {
        String numero;
        if (input() == '0' || input() == '1' || input() == '2' || input() == '3' || input() == '4' ||
                input() == '5' || input() == '6' || input() == '7' || input() == '8' || input() == '9') {
            numero = PRECIO();
            return numero;
        } else {
            if(PRINT_INFO_LOGS) System.out.println("Fin de Recurcion en R2");
            numero = "";
            return numero;
        }
    }

    /*
        BNF: DIGITO -> 0 | 1 | ... | 9 
        PRIMERO(DIGITO) = {0 ... 9}
        Regla Semantica: DIGITO.numero = 0, ..., 9
     */
    static String DIGITO() {
        if (input() == '0') {
            match('0');
            return "0";
        } else if (input() == '1') {
            match('1');
            return "1";
        } else if (input() == '2') {
            match('2');
            return "2";
        } else if (input() == '3') {
            match('3');
            return "3";
        } else if (input() == '4') {
            match('4');
            return "4";
        } else if (input() == '5') {
            match('5');
            return "5";
        } else if (input() == '6') {
            match('6');
            return "6";
        } else if (input() == '7') {
            match('7');
            return "7";
        } else if (input() == '8') {
            match('8');
            return "8";
        } else {
            match('9');
            return "9";
        }
    }
}
