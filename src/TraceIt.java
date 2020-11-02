import java.time.LocalDate;
import java.util.ArrayList;

public class TraceIt {
    public static void main(String[] args) {
        Pantalla pantalla=new Pantalla();
        pantalla.clean();
        ArrayList<Ciudadano>ciudadanos=Anses.listaCiudadanos();

        while(true){

            int registro=pantalla.registro();
            String cuil;
            String cel;

            if(registro==1){
                String dato=pantalla.nuevo();
                Anses.nuevoCiu(dato,ciudadanos);
                ciudadanos=Anses.listaCiudadanos();
                 cuil=dato.substring(0,11);
                 cel = dato.substring(11);
            }else {
                String datos = pantalla.inicio();
                 cuil = datos.substring(0, 11);
                 cel = datos.substring(11);
            }

            int ciudadanonum = Ciudadano.checkData(cuil, cel, ciudadanos);
            if (ciudadanonum == 1) {
                int decision = pantalla.Ciudadano();

                while (decision != 5) {
                    switch (decision) {
                        case 1:
                            String contCuil = pantalla.contactoCuil();
                            LocalDate fecha = pantalla.ContactoFecha();
                            Encuentro encuentro=new Encuentro(cuil,contCuil,fecha);
                            encuentro.send();
                            decision = pantalla.Ciudadano();
                            break;
                        case 2:
                            int nuevoSin = pantalla.sintomaSI();
                            Covid19 paciente=new Covid19(cuil,nuevoSin);
                            paciente.afectado();
                            decision = pantalla.Ciudadano();
                            break;
                        case 3:
                            int viejoSin = pantalla.sintomaNO();
                            Covid19 paciente1=new Covid19(cuil,viejoSin);
                            paciente1.desafectado();
                            decision = pantalla.Ciudadano();
                            break;
                        case 4:
                            Contacto contacto=new Contacto(cuil);
                            contacto.checkContact();
                            decision = pantalla.Ciudadano();
                            break;
                        default:
                            decision = pantalla.Ciudadano();
                            break;
                    }
                }
            } else if (ciudadanonum == 2) {
                int decision = pantalla.pantallaAdmin();

                while (decision != 6) {
                    switch (decision) {
                        case 1:
                            String blockCiu = pantalla.block();
                            Ciudadano.bloquear(blockCiu, ciudadanos);
                            decision = pantalla.pantallaAdmin();
                            break;
                        case 2:
                            String desblockCiu = pantalla.desblock();
                            Ciudadano.desbloquear(desblockCiu,ciudadanos);
                            decision = pantalla.pantallaAdmin();
                            break;
                        case 3:
                            String sintomNuevo = pantalla.newSintoma();
                            Ciudadano.addSintom(sintomNuevo);
                            decision = pantalla.pantallaAdmin();
                            break;
                        case 4:
                            int sacarSintoma = pantalla.removeSintoma();
                            Ciudadano.removeSintom(sacarSintoma);
                            decision = pantalla.pantallaAdmin();
                            break;
                        case 5:
                            pantalla.datos();
                            decision = pantalla.pantallaAdmin();
                            break;
                        default:
                            decision = pantalla.Ciudadano();
                    }
                }
            }

        }
    }

}

