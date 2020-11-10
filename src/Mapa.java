import java.util.ArrayList;

public class Mapa {

    ArrayList<String> ubicaciones;

    public Mapa() {
        ubicaciones=new ArrayList<>();
        getLocations();
    }

    private void getLocations(){
        ArrayList<String> positivos=Covid19.positivos();
        for (int i = 0; i < positivos.size(); i++) {
            String[] datasplt=positivos.get(i).split("/",3);
            ubicaciones.add(datasplt[2]);
        }
    }
}
