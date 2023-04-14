import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

public class MiniProject {
//    public int getTotalBirthsRankedHigher(int year, String name, String gender){
//
//    }
//    public double getAverageRank(String name, String gender){
//
//    }
//    public int yearOfHighestRank(String name, String gender){
//
//    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if born in " + newYear);
    }
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource();
        int count = 1;
        for(CSVRecord rec : fr.getCSVParser()){
            if(rec.get(1).equals(gender)){
                if(count == rank){
                    return rec.get(0);
                }
                count++;
            }
        }
        return "NO NAME";
    }
    public int getRank(int year, String name, String gender){
        int rank = 0;
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                rank++;
                if(rec.get(0).equals(name)){
                    return rank;
                }
            }

        }
        return -1;
    }
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int femaleBirths = 0;
        int maleBirths = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M")){
                maleBirths += numBorn;
            } else {
                femaleBirths += numBorn;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Male births = " + maleBirths);
        System.out.println("Female births = " + femaleBirths);
    }
    public void testMethods(){
//        FileResource fr = new FileResource();
//        totalBirths(fr);
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }
    public static void main(String[] args){
        MiniProject inst = new MiniProject();
        inst.testMethods();
    }
}
