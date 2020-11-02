
import java.util.ArrayList;

public class Anses extends LectorArchivos {


    public static ArrayList<Ciudadano> listaCiudadanos() {
        ArrayList<String> data = createList("src\\archivos");
        boolean admin;
        boolean block;
        ArrayList<Ciudadano> ciudadanos = new ArrayList<>();

        for (String datum : data) {
            String[] datasplt = datum.split("/", 6);
                admin = "true".equals(datasplt[3]);
                block = "true".equals(datasplt[4]);
                ciudadanos.add(new Ciudadano(datasplt[0], datasplt[1], datasplt[2], admin, block, datasplt[5]));
        }
        return ciudadanos;
    }//crear lista de ciudadanos a partir de ListaS(String)

    public static void nuevoCiu(String data,ArrayList<Ciudadano>ciudadanos) {
        ArrayList<Ciudadano> ciudadanosA = listaAnses();
        String cuilnum = data.substring(0, 11);
        String celnum = data.substring(11);
        int x = 1;
        for (Ciudadano ciudadano : ciudadanos) {
            if (cuilnum.equals(ciudadano.cuil)) {
                System.out.println("Usted ya esta registrado/a,");
                x = 0;
            }
        }
        if (x == 1) {
            for (Ciudadano ciudadano : ciudadanosA) {
                if (cuilnum.equals(ciudadano.cuil) && celnum.equals(ciudadano.cel)) {
                    RegistrarCiudadanoAuxiliar(ciudadano);
                }
            }
        }
    }//registrar nuevo ciudadano

    private static void RegistrarCiudadanoAuxiliar(Ciudadano a) {
        String name = a.nombre;
        String cuil = a.cuil;
        String cel = a.cel;
        String admin;
        String block;
        String ubicacion=a.ubicacion;
        if (a.admin){
            admin ="true";
        }else {
            admin = "false";
        }
        if(a.block) {
            block = "true";
        }else {
            block = "false";
        }
        String toAdd="\n"+name + "/" + cuil + "/" + cel + "/" + admin + "/" + block+"/"+ubicacion;

        añadir("src\\archivos",toAdd);

    }//agrega un nuevo ciudadano a la lista de registrados

    private static ArrayList<Ciudadano> listaAnses(){
        ArrayList<String> data;
        data = createList("src\\dataSet");
        boolean admin;
        boolean block;
        ArrayList<Ciudadano>ciudadanos=new ArrayList<>();

        for (String datum : data) {
            String[] datasplt = datum.split("/", 6);

            admin = "true".equals(datasplt[3]);
            block = "true".equals(datasplt[4]);
            ciudadanos.add(new Ciudadano(datasplt[0], datasplt[1], datasplt[2], admin, block, datasplt[5]));
        }
        return ciudadanos;
    }// Convierte el arreglo de String en ciudadanos(base anses)

}
