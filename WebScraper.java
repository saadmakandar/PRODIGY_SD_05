import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WebScraper {

    public static void main(String[] args) {
        try {
            // Connect to the website
            String url = "http://quotes.toscrape.com/";
            Document doc = Jsoup.connect(url).get();

            // Extract quotes and authors
            Elements quoteElements = doc.select("div.quote");

            // Create a CSV file
            BufferedWriter writer = new BufferedWriter(new FileWriter("quotes.csv"));

            // Write CSV header
            writer.write("Quote,Author\n");

            // Loop through quote elements and extract data
            for (Element quoteElement : quoteElements) {
                String quote = quoteElement.select("span.text").text();
                String author = quoteElement.select("span small.author").text();

                // Write data to the CSV file
                writer.write("\"" + quote + "\",\"" + author + "\"\n");
            }

            // Close the CSV writer
            writer.close();

            System.out.println("Web scraping and CSV file creation completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
