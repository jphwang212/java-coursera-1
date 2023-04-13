import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.*;

import java.io.File;

public class ParseWeather {
    public String fileWithColdestTemperature() {
        DirectoryResource dir = new DirectoryResource();
        CSVRecord coldest = null;
        String coldestFile = "";
        for (File f : dir.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord record = coldestHourInFile(fr.getCSVParser());
            if (coldest == null) {
                coldest = record;
                coldestFile = f.getName();
            } else {
                float currentTemp = Float.parseFloat(record.get("TemperatureF"));
                float coldestTemp = Float.parseFloat(coldest.get("TemperatureF"));
                if (currentTemp < coldestTemp && currentTemp != -9999) {
                    coldest = record;
                    coldestFile = f.getName();
                }

            }
        }
        return coldestFile;
    }
    public void testFileWithColdestTemp(){
        String file = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + file);
        FileResource fr = new FileResource("nc_weather/2014/" + file);
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + record.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord rec : parser){
            System.out.println("Printing something");
            System.out.println(rec.get("DateUTC") + ": " + rec.get("TemperatureF"));
        }
    }
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord record = null;
        for(CSVRecord rec : parser){
            if(record == null){
                record = rec;
            } else {
                float currentTemp = Float.parseFloat(rec.get("TemperatureF"));
                float coldest = Float.parseFloat(record.get("TemperatureF"));
                if(currentTemp < coldest && currentTemp != -9999){
                    record = rec;
                }
            }
        }
        return record;
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println(record.toString());
    }
    public static void main(String[] args){
        ParseWeather inst = new ParseWeather();
//        inst.testColdestHourInFile();
        inst.testFileWithColdestTemp();
    }
}
