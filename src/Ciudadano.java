import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ciudadano {

    String nombre;
    String cuil;
    String cel;
    boolean admin;
    boolean block;
    String ubicacion;

    public Ciudadano(String nombre, String cuil, String cel, boolean admin, boolean block,String ubicacion) {
        this.nombre = nombre;
        this.cuil = cuil;
        this.cel = cel;
        this.admin = admin;
        this.block = block;
        this.ubicacion=ubicacion;
    }

    public static int checkData(String cuilnum, String celnum, ArrayList<Ciudadano> ciudadanos) {
        Pantalla pantalla=new Pantalla();
        for (int i = 0; i < ciudadanos.size(); i++) {
            if ((ciudadanos.get(i)).cuil.equals(cuilnum) && (ciudadanos.get(i)).cel.equals(celnum)) {
                if (ciudadanos.get(i).admin) {
                    System.out.println("Bienvenido/a," + ciudadanos.get(i).nombre);
                    int decision = pantalla.inicioAdmin();
                    if (decision == 1) {
                        return 2;
                    } else {
                        return 1;
                    }
                } else if (ciudadanos.get(i).block) {
                    System.out.println("Usted se encuentra temporalmente bloqueado/a");
                    return -1;
                } else {
                    System.out.println("Bienvenido," + ciudadanos.get(i).nombre);
                    return 1;
                }
            }
        }
        return -1;

    }

    public static void bloquear(String dato, ArrayList<Ciudadano> ciudadanos) {
        if (dato.length() == 11) {
            for (int i = 0; i < ciudadanos.size(); i++) {
                if ((ciudadanos.get(i)).cuil.equals(dato)) {
                    if (!ciudadanos.get(i).admin) {
                        ciudadanos.get(i).block=true;
                    }
                }
            }
            save(ciudadanos);
        } else if (dato.length() == 10) {
            for (int i = 0; i < ciudadanos.size(); i++) {
                if ((ciudadanos.get(i)).cel.equals(dato)) {
                    if (!ciudadanos.get(i).admin) {
                        ciudadanos.get(i).block=true;
                    }
                }
            }
            save(ciudadanos);
        }
    }

    public static void desbloquear(String dato, ArrayList<Ciudadano>ciudadanos){
        if (dato.length() == 11) {
            for (int i = 0; i < ciudadanos.size(); i++) {
                if ((ciudadanos.get(i)).cuil.equals(dato)) {
                    if (!ciudadanos.get(i).admin) {
                        ciudadanos.get(i).block=false;
                    }
                }
            }
            save(ciudadanos);

        } else if (dato.length() == 10) {
            for (int i = 0; i < ciudadanos.size(); i++) {
                if ((ciudadanos.get(i)).cel.equals(dato)) {
                    if (!ciudadanos.get(i).admin) {
                        ciudadanos.get(i).block=false;
                    }
                }
            }
            save(ciudadanos);
        }
    }

    public static void addSintom(String sintoma){
        ArrayList<String> sintomas=Covid19.sintomas();
        sintomas.add(sintoma);
        Covid19.saveSintom(sintomas);
    }

    public static void removeSintom(int sintoma){
        ArrayList<String> sintomas=Covid19.sintomas();
        try {
            sintomas.remove(sintoma - 1);
            Covid19.saveSintom(sintomas);
        }catch(IndexOutOfBoundsException e){

        }
    }

    public static Ciudadano getCiu(String cuil){
        ArrayList<Ciudadano>ciudadanos=Anses.listaCiudadanos();
        for (int i = 0; i < ciudadanos.size(); i++) {
            if(ciudadanos.get(i).cuil.equals(cuil)){
                return ciudadanos.get(i);
            }
        }
        return null;
    }

    public static void save(ArrayList<Ciudadano>ciudadanos){
        try {
            FileWriter fileWriter = new FileWriter("src\\archivos");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String admin;
            String block;
            for (int i = 0; i < ciudadanos.size(); i++) {
                if(ciudadanos.get(i).admin){
                    admin="true";
                }else{
                    admin="false";
                }
                if (ciudadanos.get(i).block){
                    block="true";
                }else{
                    block="false";
                }
                String nombre=ciudadanos.get(i).nombre;
                String cuil=ciudadanos.get(i).cuil;
                String cel=ciudadanos.get(i).cel;
                String ubicacion=ciudadanos.get(i).ubicacion;

                bufferedWriter.write(nombre+"/"+cuil+"/"+cel+"/"+admin+"/"+block +"/"+ubicacion+ "\n");
            }
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
