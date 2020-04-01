import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class TermFrequency
{
    private static String filePath;

    public static void main( String[] args )
    {

        filePath = args[0];
        HashMap<String, Integer> dict = new HashMap();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = reader.readLine()) != null){
                String[] arrOfStr = line.split("[, ?.@]+");

                for (int i = 0; i < arrOfStr.length; i++){
                    String word = arrOfStr[i];
                    addWord(dict, word);
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }


        System.out.println(filePath);
    }

    private static void addWord(HashMap<String, Integer> dict, String word){
        if (!dict.containsKey(word)){
            dict.put(word,1);
        }
        else{
            int currentAmount = dict.get(word);
            dict.replace(word, currentAmount+1);
        }
    }
}