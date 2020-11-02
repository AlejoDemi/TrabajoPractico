import java.util.ArrayList;

public class Brote {


    String brotePosible;
    ArrayList<String>brote;

    public Brote() {
        brote=new ArrayList<>();
    }

    public void tablaBrote(){
        checkGrado2();
        Checkbrote();
        if (brote.size()==0) {
            System.out.println("No se han detectado brotes");
        }else{
            System.out.println("Brote detectado. Estos son los CUILS de los involucrados");
            System.out.println();
            for (int i = 0; i < brote.size(); i++) {
                System.out.println((i+1)+"_"+brote.get(i));
            }
        }

    }

    private void checkGrado2(){
        ArrayList<String> positivos=Covid19.positivos();
        ArrayList<String> contactos=Contacto.contactosLista();
        if (positivos.size()>4){
            for (int i = 0; i < positivos.size(); i++) {
                for (int j = 0; j < contactos.size(); j++) {
                    for (int k = j+1; k < contactos.size(); k++) {
                        String[]cuilPositivos=positivos.get(i).split("/",3);
                        String[]cuilContactos=contactos.get(j).split("/",3);
                        String[]cuilContactos2=contactos.get(k).split("/",3);
                        if(cuilPositivos[0].equals(cuilContactos[0]) && cuilPositivos[0].equals(cuilContactos2[1])){
                            brotePosible=cuilPositivos[0];
                            brote.add(brotePosible);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void Checkbrote(){
        ArrayList<String> positivos=Covid19.positivos();
        ArrayList<String> contactos=Contacto.contactosLista();
            for (int j = 0; j < contactos.size(); j++) {
                for (int k = 0; k < positivos.size(); k++) {
                    String[]cuilPositivos=positivos.get(k).split("/",3);
                    String[]cuilContactos=contactos.get(j).split("/",3);
                    if (brotePosible.equals(cuilContactos[0])){
                        if (cuilContactos[1].equals(cuilPositivos[0])){
                            if(!addIfPossible(cuilContactos[1])){
                                brote.add(cuilContactos[1]);
                            }
                        }
                    }else if(brotePosible.equals(cuilContactos[1])){
                        if (cuilContactos[0].equals(cuilPositivos[0])){
                            if(!addIfPossible(cuilContactos[0])){
                                brote.add(cuilContactos[1]);
                            }
                        }
                    }
                }
            }
        }

    public boolean addIfPossible(String cuil){
        for (int i = 0; i < brote.size(); i++) {
            if (brote.get(i).equals(cuil)){
                return true;
            }
        }
        return false;
    }
}
