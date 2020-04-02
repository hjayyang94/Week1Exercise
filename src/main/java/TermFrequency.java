import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class TermFrequency
{
    private static String filePath;
    private static List<String> stopWords;
    private static String stopPath = "stop_words.txt";

    public static void main( String[] args )
    {

        filePath = args[0];


        HashMap<String, Integer> map = new HashMap();

        generateStopWords();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = reader.readLine()) != null){
                String[] arrOfStr = line.trim().split("[^A-Za-z]");

                for (int i = 0; i < arrOfStr.length; i++){
                    String word = arrOfStr[i].toLowerCase();
                    if (word.length() > 1 && !stopWords.contains(word)){
                        addWord(map, word);
                    }
                }
            }

            List<Map.Entry<String,Integer>> sortedWords = sortByValue(map);

            printResults(sortedWords);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    private static void addWord(HashMap<String, Integer> map, String word){

        if (!map.containsKey(word)){
            map.put(word,1);
        }
        else{
            int currentAmount = map.get(word);
            map.replace(word, currentAmount+1);
        }
    }

    private static List<Map.Entry<String,Integer>> sortByValue(HashMap<String, Integer> hashmap){
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hashmap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        return list;
    }

    private static void printResults(List<Map.Entry<String, Integer>> results){

        StringBuilder out = new StringBuilder("-----------Results----------");
        int counter = 0;
        for (Map.Entry<String, Integer> entry : results){
            out.append("\n"+entry.getKey()+":"+entry.getValue());
            counter++;
            if(counter > 24){ break; }
        };

        System.out.println(out.toString());
    }

    private static void generateStopWords(){
        try{
            BufferedReader readStop = new BufferedReader(new FileReader(stopPath));
            String line;
            while ((line = readStop.readLine()) != null) {
                stopWords = Arrays.asList(line.split(","));
            }

        }
        catch(Exception e){
            System.out.println("Could not load Stop Words");
        }
    }
}