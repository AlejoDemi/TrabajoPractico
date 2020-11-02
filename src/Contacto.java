import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Objects;

public class Contacto extends LectorArchivos {

    String cuilCont;

    public Contacto(String cuilCont){
        this.cuilCont=cuilCont;
    }

    public static ArrayList<String>contactosLista(){
        return createList("src\\Contacto");
    }

    public  void checkContact() {
        ArrayList<String> notificaciones = Encuentro.checkNot(this.cuilCont);
        for (String notificacione : notificaciones) {
            String[] datasplt = notificacione.split("/", 3);
            String cuil = datasplt[0];
            String fecha = datasplt[2];

            int decision = Scanner.getInt("Tuvo usted contacto con " + Objects.requireNonNull(Ciudadano.getCiu(cuil)).nombre + " de CUIL " + datasplt[0] +
                    " en la fecha: " + fecha + "?\n1-Si\n2-No\n");
            Pantalla pantalla = new Pantalla();
            pantalla.clean();
            if (decision == 1) {
                contactoConfirmado(this.cuilCont, cuil);
                checkContagio(this.cuilCont, cuil);
            } else {
                rechazo(cuil, this.cuilCont);
            }
        }

    }

    private static void contactoConfirmado(String cuilCont, String cuil){
        ArrayList<String>notificaciones=Encuentro.checkNot(cuilCont);
        String contacto="";
        for (int i = 0; i < notificaciones.size(); i++) {
            String[] datasplt = notificaciones.get(i).split("/", 3);
            if (datasplt[0].equals(cuil) && datasplt[1].equals(cuilCont)){
                contacto=notificaciones.get(i);
                notificaciones.set(i,null);
            }
        }
        escribirLista("src\\Notificaciones",notificaciones);
        String toAdd=contacto + "\n";
        añadir("src\\Contacto",toAdd);

    }

    private static void rechazo(String cuil,String cuilCont){
        ArrayList<String> notificaciones=Encuentro.checkNot(cuilCont);
        for (int i = 0; i < notificaciones.size(); i++) {
            String[] datasplt = notificaciones.get(i).split("/", 3);
            if (datasplt[0].equals(cuil) && datasplt[1].equals(cuilCont)){
                notificaciones.set(i,null);
            }
        }
        escribirLista("src\\Notificaciones",notificaciones);
        String toAdd=cuil + "\n";
        añadir("src\\Rechazos",toAdd);
        checkBlock(cuil);

    }

    private static void nuevoCaso(String cuil1,String cuil2){
        String covid="";
        try{
            FileReader fileReader=new FileReader("src\\PositiveCovid");
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String line=bufferedReader.readLine();
            while(line!= null){
                String[] datasplt = line.split("/", 2);
                    if (datasplt[0].equals(cuil1)) {
                        covid = cuil2;
                        break;
                    } else if (datasplt[0].equals(cuil2)) {
                        covid = cuil1;
                        break;
                    }
                line=bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        String ubicacion= Objects.requireNonNull(Ciudadano.getCiu(covid)).ubicacion;
        String toAdd=covid+"/"+ LocalDate.now()+"/"+ubicacion+"\n";
        añadir("src\\PositiveCovid",toAdd);
    }

    private static void checkContagio(String cuilCont,String cuil){
        String contacto="";
        String covid="";
        try{
            FileReader fileReader=new FileReader("src\\Contacto");
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String line=bufferedReader.readLine();
            while(line!= null){
                String[] datasplt =line.split("/", 3);
                    if (datasplt[0].equals(cuil) && datasplt[1].equals(cuilCont)) {
                        contacto = line;
                        break;
                    } else if (datasplt[0].equals(cuilCont) && datasplt[1].equals(cuil)) {
                        contacto = line;
                        break;
                    }
                line=bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        try{
            FileReader fileReader=new FileReader("src\\PositiveCovid");
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String line=bufferedReader.readLine();
            while(line!= null){
                String[] datasplt = line.split("/", 2);
                    if (datasplt[0].equals(cuil) || datasplt[0].equals(cuilCont)) {
                        covid = line;
                        break;
                    }

                line=bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
            String fechaCont = contacto.substring(24);
            String fechaCovid = covid.substring(12,22);
            LocalDate contactDate = LocalDate.parse(fechaCont);
            LocalDate covidDate = LocalDate.parse(fechaCovid);
            Period period1 = Period.between(contactDate, covidDate);
            Period period2 = Period.between(covidDate, contactDate);
            if (period1.getDays() >= 0 && period1.getDays() < 3) {
                nuevoCaso(cuilCont, cuil);
                System.out.println("Es muy probable que usted padezca Covid-19, tome las precauciones necesarias.\n" +
                        "Encontraras mas informacion en el siguiente link:\n" +
                        "https://www.who.int/es/emergencies/diseases/novel-coronavirus-2019/advice-for-public/q-a-coronaviruses#:~:text=sintomas");
            } else if (period2.getDays() >= 0 ) {
                nuevoCaso(cuilCont, cuil);
                System.out.println("Es muy probable que usted padezca Covid-19, tome las precauciones necesarias.\n" +
                        "Encontraras mas informacion en el siguiente link:\n" +
                        "https://www.who.int/es/emergencies/diseases/novel-coronavirus-2019/advice-for-public/q-a-coronaviruses#:~:text=sintomas");
            }


    }

    private static void checkBlock(String cuil){
        ArrayList<String> rechazos=createList("src\\Rechazos");
        int x=0;
        for (String rechazo : rechazos) {
            if (rechazo.equals(cuil)) {
                x++;
            }
        }
        if(x>4){
            for (int i = 0; i < rechazos.size(); i++) {
                if(rechazos.get(i).equals(cuil)){
                    rechazos.set(i,null);
                }
                Ciudadano.bloquear(cuil,Anses.listaCiudadanos());
            }
        }

    }

}

