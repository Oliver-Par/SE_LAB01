import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Net {

    private static String[] erreichbar = {
            "Ping wird ausgeführt für ",
            " mit 32 Bytes Daten:\n",
            "  Antwort von ",
            ": Bytes=32 Zeit=",
            "ms TTL=255\n",
            "\nPing-Statistik für ",
            ":\n   Pakete: Gesendet = 4, Empfangen = 4, Verloren = 0\n"+
                    "   (0% Verlust),\n"+
                    " Ca. Zeitangaben in Millisek:\n"+
                    "    Minimum = ",
            "ms, Maximum = ",
            "ms, Mittelwert = ",
            "ms"};

    private static String[] nichtErreichbar = {
            "Ping wird ausgeführt für ",
            " mit 32 Bytes Daten: \n"+
                    "  Zeitüberschreitung der Anforderung. \n"+
                    "  Zeitüberschreitung der Anforderung. \n"+
                    "  Zeitüberschreitung der Anforderung. \n"+
                    "  Zeitüberschreitung der Anforderung. \n\n"+
                    "Ping-Statistik für ",
            ":\n"+
                    "Pakete: Gesendet = 4, Empfangen = 0, Verloren = 4\n"+
                    "(100% Verlust)"};

    private static String nichtErreichbarText(String adresse){
        String antwort ="";
        for (int i=0;i<nichtErreichbar.length;i++ ) {
            antwort+=nichtErreichbar[i];
            if(i<2){
                antwort+=adresse;
            }
        }
        return antwort;
    }

    private static String erreichbarText(String adresse){
        String antwort ="";
        int pingzeit = pingZeit();
        antwort += erreichbar[0]
                +  adresse
                +  erreichbar[1];
        for(int i=0;i<4;i++)
            antwort += erreichbar[2]
                    +  adresse
                    +  erreichbar[3]
                    +  pingzeit
                    +  erreichbar[4];
        antwort += erreichbar[5]
                +  adresse;
        for(int i=6;i<9;i++)
            antwort += erreichbar[i]
                    +  pingzeit;
        antwort += erreichbar[9];
        return antwort;
    }

    private static boolean istValide(String[] adressteil){
        for(String teil:adressteil)
            if(Integer.parseInt(teil)<=0 || Integer.parseInt(teil) >255){
                return false;
            }
        return true;
    }

    private static boolean antwortet(String[] adressteil){
        return Integer.parseInt(adressteil[adressteil.length-1])%7==0;
    }


    private static int pingZeit(){
        return (int)(Math.random()*24+1);
    }

    public static String ping (int adresse){
        return ping("192.68.2."+adresse);
    }


    public static String ping(String adresse){
        String antwort ="";
        String[] adressteil = adresse.split("\\.");
        if(!istValide(adressteil)||adressteil.length<4){
            antwort = "Fehlerhafte Eingabe";
        }else{
            if(antwortet(adressteil)){
                antwort = erreichbarText(adresse);
            }else{
                antwort = nichtErreichbarText(adresse);
            }
        }
        return antwort;
    }


    public static void main(String[] args) {
        Set<String> a = new HashSet<>();
        int x = a.size();
    }

}

