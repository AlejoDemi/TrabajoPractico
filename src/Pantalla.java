import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Pantalla {

    public Pantalla(){}

    public int registro(){
        System.out.println("***************************************");
        System.out.println("Bienvenido!");
        int decision=Scanner.getInt("Registratme 1\nYa tengo cuenta 2\n");
        clean();
        return decision;
    }

    public  String nuevo(){
        System.out.println("***************************************");
        String cuil=Scanner.getString("Ingrese su CUIL\n");
        String cel=Scanner.getString("Ingrese su celular\n");
        clean();
        return cuil+cel;
    }

    public  String inicio(){
        System.out.println("***************************************");
        String cuil= Scanner.getString("Por favor, ingrese su CUIL\n");
        String cel= Scanner.getString("Por favor, ingrese su celular\n");
        clean();
        return cuil+cel;
    }

    public  int inicioAdmin(){
        System.out.println("***************************************");
        System.out.println("Usted esta registrado como administrador.");
        int decision=Scanner.getInt("Si desea entrar como tal ,ingrese 1.\nSi desea entrar como ciudadano, ingrese 2\n");
        clean();
        return decision;
    }

    public  int Ciudadano() {
        System.out.println("***************************************");
        System.out.print("1_Informar contacto con otro ciudadano\n" +
                "2_Informar un nuevo sintoma\n" +
                "3_Dar de baja un sintoma\n" +
                "4_Mis notificaciones\n" +
                "5_Salir\n");

        int decision = Scanner.getInt("Seleccione una accion.\n");
        clean();
        return decision;
    }

    public  String contactoCuil(){
        System.out.println("***************************************");
        String cuil=Scanner.getString("Ingrese el CUIL del ciudadano\n");
        return cuil;
    }

    public  LocalDate ContactoFecha(){
        int mes=Scanner.getInt("Ingrese el mes en el que efectuo el contacto\n");
        int dia=Scanner.getInt("Ingrese el dia en el que efectuo el contacto\n");
        clean();
       try {
           LocalDate fecha = LocalDate.of(2020, mes, dia);
           return fecha;
       }catch(DateTimeException e){
           return LocalDate.now();
       }

    }

    public  int sintomaSI(){
        ArrayList<String> sintomas=Covid19.sintomas();
        System.out.println("***************************************");
        System.out.println("Sintomas:");//Lista de sintomas
        for (int i = 0; i <sintomas.size() ; i++) {
            System.out.println((i+1)+"__"+sintomas.get(i));
        }
        int seleccion=Scanner.getInt("Seleccione el sintoma que padece\n");
        clean();
        return seleccion;
    }

    public  int sintomaNO(){
        ArrayList<String> sintomas=Covid19.sintomas();
        System.out.println("***************************************");
        System.out.println("Sintomas:");//Lista de sintomas
        for (int i = 0; i <sintomas.size() ; i++) {
            System.out.println((i+1)+"__"+sintomas.get(i));
        }
        int seleccion=Scanner.getInt("Seleccione el sintoma que ya no padece\n");
        clean();
        return seleccion;
    }

    public  int pantallaAdmin(){
        System.out.println("***************************************");
        System.out.print("1_Bloquear acceso a un ciudadano\n" +
                "2_Desbloquear acceso de un ciudadano\n" +
                "3_Añadir un nuevo sintoma a la lista\n" +
                "4_Eliminar sintoma existente\n" +
                "5_Datos\n" +
                "6_Salir\n");
        int decision = Scanner.getInt("Seleccione una accion.\n");
        clean();
        return decision;
    }

    public  String block(){
        System.out.println("***************************************");
        String ciudadano=Scanner.getString("Ingrese el CUIL o celular del ciudadano a bloquear\n");
        clean();
        return  ciudadano;
    }

    public  String desblock(){
        System.out.println("***************************************");
        String ciudadano=Scanner.getString("Ingrese el CUIL o celular del ciudadano a desbloquear\n");
        clean();
        return  ciudadano;
    }

    public  String newSintoma(){
        ArrayList<String> sintomas=Covid19.sintomas();
        System.out.println("***************************************");
        System.out.println("Sintomas:");//Lista de sintomas
        for (int i = 0; i <sintomas.size() ; i++) {
            System.out.println((i+1)+"__"+sintomas.get(i));
        }
        String sintoma=Scanner.getString("Ingrese el nombre del nuevo sintoma\n");
        clean();
        return  sintoma;
    }

    public  int removeSintoma(){
        ArrayList<String> sintomas=Covid19.sintomas();
        System.out.println("***************************************");
        System.out.println("Sintomas:");//Lista de sintomas
        for (int i = 0; i <sintomas.size() ; i++) {
            System.out.println((i+1)+"__"+sintomas.get(i));
        }
        int sintoma=Scanner.getInt("Ingrese el numero del sintoma a eliminar\n");
        clean();
        return  sintoma;
    }

    public void datos(){
        Brote brote=new Brote();
        brote.tablaBrote();
    }

    public  void clean(){
        for(int i=0;i<30;i++){
            System.out.println();
        }
    }


    }



