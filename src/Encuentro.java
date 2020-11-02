import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class Encuentro {

    String cuil;
    String cuilCont;
    LocalDate fecha;

    public Encuentro(String cuil, String cuilCont, LocalDate fecha) {
        this.cuil = cuil;
        this.cuilCont = cuilCont;
        this.fecha = fecha;
    }

    public void send() {
        String toAdd = this.cuil + "/" + this.cuilCont + "/" + this.fecha + "\n";
        Anses.añadir("src\\Notificaciones", toAdd);
    }

    public static ArrayList<String> checkNot(String cuil){
        ArrayList<String> notificaciones=new ArrayList<>();
        try{
            FileReader fileReader=new FileReader("src\\Notificaciones");
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String line=bufferedReader.readLine();
            while(line != null) {
                String[] datasplt = line.split("/", 3);
                if ( datasplt[1].equals(cuil)) {
                    notificaciones.add(line);
                }
                line=bufferedReader.readLine();

            }
            bufferedReader.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return notificaciones;

    }

}