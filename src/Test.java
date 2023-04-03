import java.util.Arrays;

public class Test {

    public static String testPing(int adress){
        String addresse = "194.94.2." + adress;
        String output = Net.ping(adress);
        System.out.println(istEreichbar(addresse));
        return output;
    }


    public static boolean istZeitueberschreitung(String nachricht){
        return nachricht.contains("Zeitüberschreitung");
    }

    public static boolean hatAntwortzeit(String nachricht){
        if(nachricht.contains("ms")){
            return true;
        }
        return false;
    }

   public static boolean istEreichbar(String adresse){
        String[] addressteil = adresse.split("\\.");
        String nachricht = Net.ping(Integer.parseInt(addressteil[addressteil.length-1]));
        return !nachricht.contains("100% Verlust");

    }

    public static int zaehleEreichbar(){
        int anzahl = 0;
        for(int i = 1; i <= 128; i++){
            String addresse = "194.94.2." + i;
            if(istEreichbar(addresse)){
                anzahl++;
            }
        }
        return anzahl;
    }

    public static String[] gibEreichbarAus(){
        String[] ereichbar = new String[128];
        int n = 0;
        for(int i = 1; i <= 128; i++){
            String addresse = "194.94.2." + i;
            if(istEreichbar(addresse)){
                ereichbar[n++] = addresse;
            }
        }

        return ereichbar;
    }

    public static void main(String[] args){
        int adress = 14;
        int adress2 = 20;

        System.out.println(Arrays.toString(gibEreichbarAus()));

    }
}
