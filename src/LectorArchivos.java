import java.io.*;
import java.util.ArrayList;

public abstract class LectorArchivos {

    public LectorArchivos() {
    }

    public static ArrayList<String> createList(String file){
        ArrayList<String> list=new ArrayList<>();
        try{
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String line=bufferedReader.readLine();
            while(line!=null && !line.equals("")){
                list.add(line);
                line=bufferedReader.readLine();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void escribirLista(String file,ArrayList<String> lista){
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < lista.size(); i++) {
                if(lista.get(i)!=null) {
                    bufferedWriter.write(lista.get(i)+"\n");
                }
            }
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public static void añadir(String file,String toAdd){
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(toAdd);
            bufferedWriter.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
