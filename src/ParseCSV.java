import edu.duke.*;
import org.apache.commons.csv.*;
public class ParseCSV {
    String countryInfo(CSVParser parser, String country){
        String output = "";
        for(CSVRecord record : parser){
            String info = record.get("Country");
            if(info.equals(country)){
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                output = country + ": ";
                output += (exports + ": ");
                output += value;
                return output;
            }
        }
        return "NOT FOUND";
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
//            System.out.println("Value = " + value);
            if(value.length() > amount.length()){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    public static void main(String[] args){
        ParseCSV test = new ParseCSV();
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
//        String countryInfo = test.countryInfo(parser, "Nauru");
//        System.out.println("Info: " + countryInfo);
//        parser = fr.getCSVParser();
        test.listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        int num = test.numberOfExporters(parser, "cocoa");
        System.out.println("Number of exporters = " + num);
        parser = fr.getCSVParser();
        test.bigExporters(parser, "$999,999,999,999d");
    }
}
