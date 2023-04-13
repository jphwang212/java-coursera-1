import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.*;

import java.io.File;

public class ParseWeather {
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0.0;
        double sumLength = 0.0;
        for(CSVRecord record : parser){
            int humidity = Integer.parseInt(record.get("Humidity"));
            double temp = Double.parseDouble(record.get("TemperatureF"));
//            System.out.println(temp);
            if(humidity >= (double)value){
                sum += temp;
                sumLength += 1.0;
            }
        }
        return (sum / sumLength);
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(avg == 0.0 || Double.isNaN(avg)){
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + avg);
        }
    }
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord record = null;
        DirectoryResource dir = new DirectoryResource();
        for(File f : dir.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord rec = lowestHumidityInFile(parser);
            if(record == null){
                record = rec;
            } else {
                float currentHumidity = Float.parseFloat(rec.get("Humidity"));
                float lowestHumidity = Float.parseFloat(record.get("Humidity"));
                if(currentHumidity < lowestHumidity){
                    record = rec;
                }
            }
        }
        return record;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord record = null;
        for(CSVRecord rec : parser){
            if(record == null){
                record = rec;
            } else {
                String strHumidity = rec.get("Humidity");
                System.out.println(strHumidity);
                if(strHumidity.equals("N/A")){
                    continue;
                }
                int currentHumidity = Integer.parseInt(rec.get("Humidity"));
                int lowestHumidity = Integer.parseInt(record.get("Humidity"));
                if(currentHumidity < lowestHumidity){
                    record = rec;
                }
            }
        }
        return record;
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + "at " + csv.get("DateUTC"));
    }
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
        parser = fr.getCSVParser();
        System.out.println("Coldest temperature on that day was " + record.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord rec : parser){
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
        System.out.println(record.get("DateUTC"));
    }
    public static void main(String[] args){
        ParseWeather inst = new ParseWeather();
//        inst.testColdestHourInFile();
//        inst.testFileWithColdestTemp();
//        inst.testLowestHumidityInFile();
//        inst.testLowestHumidityInManyFiles();
        inst.testAverageTemperatureWithHighHumidityInFile();
    }
}
