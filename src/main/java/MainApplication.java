
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files


public class MainApplication {
    URL resource = getClass().getClassLoader().getResource("links.txt");

    public static void main (String  [] agrs) throws Exception{
       List<String> s = new MainApplication().readFile();
        s.stream().forEach(x -> {
            try{
                Document homeDocument = Jsoup.connect(x).get();
                Elements links = homeDocument.select("a");
                for (Element link:links){
                    if(link.attr("href").contains("http")){
                    System.out.println(link.attr("href"));
                    }

                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        });



    }

    public List<String> readFile(){
        List<String> news = new ArrayList<>();
        try {

            File myObj = new File(resource.getFile());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                news.add(data);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return  news;
    }
}
