import java.time.LocalDate;
import java.util.ArrayList;

public class Covid19 extends LectorArchivos {

    String cuil;
    int sintoma;

    public Covid19(String cuil, int sintoma){
        this.cuil=cuil;
        this.sintoma=sintoma;
    }

    public static ArrayList<String> positivos(){
        return  createList("src\\PositiveCovid");
    }

    public static ArrayList<String> sintomas(){
        return createList("src\\Sintomas");
    }

    public void afectado(){
        ArrayList<String>sintomas=sintomas();
        String toAdd=this.cuil + "/" + sintomas.get(this.sintoma-1) + "\n";
        añadir("src\\Afectados",toAdd);
        checkCovid();
    }

    public void desafectado(){
        ArrayList<String> afectados=createList("src\\Afectados");
        ArrayList<String> sintomas=sintomas();
        String eleccion=sintomas.get(this.sintoma-1);

        for (int i = 0; i < afectados.size(); i++) {
            String[] datasplt = afectados.get(i).split("/", 2);
            if (datasplt[0].equals(this.cuil) && datasplt[1].equals(eleccion))
                afectados.set(i,null);
        }

        escribirLista("src\\Afectados",afectados);
    }

    private void checkCovid() {
        ArrayList<String> afectados =createList("src\\Afectados");
        int x = 0;
        for (int i = 0; i < afectados.size(); i++) {
            String[] datasplt = afectados.get(i).split("/", 2);
            if (datasplt[0].equals(this.cuil)){
                    x++;
                }

            }
        if(x>1){
            String ubicacion=Ciudadano.getCiu(cuil).ubicacion;
            String toAdd=cuil+"/"+ LocalDate.now()+"/"+ubicacion+"\n";
            añadir("src\\PositiveCovid",toAdd);
            cleanAfectado();
            System.out.println("Es muy probable que usted padezca Covid-19, tome las precauciones necesarias.\n" +
                    "Encontraras mas informacion en el siguiente link:\n" +
                    "https://www.who.int/es/emergencies/diseases/novel-coronavirus-2019/advice-for-public/q-a-coronaviruses#:~:text=sintomas");
        }
    }

    private  void cleanAfectado(){
        ArrayList<String> afectados=createList("src\\Afectados");

        for (int i = 0; i <afectados.size() ; i++) {
            String[] datasplt = afectados.get(i).split("/", 2);
            if (datasplt[0].equals(this.cuil)){
            afectados.set(i,null);
            }
        }
        escribirLista("src\\Afectados",afectados);

    }

    public static void saveSintom(ArrayList<String> sintomas){
        escribirLista("src\\Sintomas",sintomas);
    }

}
