import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class MiniProject {
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource();
        int totalBirths = 0;
        for(CSVRecord rec : fr.getCSVParser()){
            if(rec.get(1).equals(gender)){
                if(rec.get(0).equals(name)){
                    break;
                } else {
                    totalBirths += (Integer.parseInt(rec.get(2)));
                }
            }
        }
        return totalBirths;
    }
    public double getAverageRank(String name, String gender){
        DirectoryResource dir = new DirectoryResource();
        int numFiles = 0;
        int sumRanks = 0;
        for(File f : dir.selectedFiles()){
            FileResource fr = new FileResource(f);
            int currentRank = getRank(fr, name, gender);
            if(currentRank > 0){
                numFiles++;
                sumRanks += currentRank;
            }

        }
        if(sumRanks > 0){
            return (double)sumRanks/numFiles;
        }
        return -1.0;
    }
    public int yearOfHighestRank(String name, String gender){
        int highestRank = Integer.MAX_VALUE;
        int year = -1;
        int currentYear = 1880;
        DirectoryResource dir = new DirectoryResource();
        for(File f : dir.selectedFiles()){
            FileResource fr = new FileResource(f);
            int currentRank = getRank(fr, name, gender);
            if(currentRank == -1){
                currentYear++;
                continue;
            }
            if(currentRank < highestRank){
                highestRank = currentRank;
                year = currentYear;
            }
            currentYear++;
        }
        return year;
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        FileResource fr = new FileResource();
        int rank = getRank(fr, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if born in " + newYear);
    }
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource();
        int count = 1;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                if(count == rank){
                    return rec.get(0);
                }
                count++;
            }
        }
        return "NO NAME";
    }
    public int getRank(FileResource fr, String name, String gender){
        int rank = 1;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                if(rec.get(0).equals(name)){
                    return rank;
                }
                rank++;
            }

        }
        return -1;
    }
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int femaleBirths = 0;
        int maleBirths = 0;
        int femaleNames = 0;
        int maleNames = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M")){
                maleNames++;
                maleBirths += numBorn;
            } else {
                femaleNames++;
                femaleBirths += numBorn;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Male births = " + maleBirths);
        System.out.println("Female births = " + femaleBirths);
        System.out.println("Number of female names = " + femaleNames);
        System.out.println("Number of male names = " + maleNames);
    }
    public void testMethods(){
//        FileResource fr = new FileResource();
        // 1. 2225
//        totalBirths(fr);
        // 2. 1421
//        totalBirths(fr);
        // 3. 251
//        int rank = getRank(fr, "Emily", "M");
//        System.out.println("Rank = " + rank);
        // 4. 54
//        int rank = getRank(fr, "Frank", "M");
//        System.out.println("Rank = " + rank);
        // 5. Mia
//        String name = getName(1980, 350, "F");
//        System.out.println("Name = " + name);
        // 6. Forrest
//        String name = getName(1982, 450, "M");
//        System.out.println("Name = " + name);
//         7. Addison
//        whatIsNameInYear("Susan", 1972, 2014, "F");
        // 8. Leonel
//        whatIsNameInYear("Owen", 1974, 2014, "M");
        // 9. 1914
//        int year = yearOfHighestRank("Genevieve", "F");
//        System.out.println("Year = " + year);
        // 10. 1960
//        int year = yearOfHighestRank("Mich", "M");
//        System.out.println("Year = " + year);
        // 11. 173.51
//        double avg = getAverageRank("Susan", "F");
//        System.out.println("Average = " + avg);
        // 12. 10.76
//        double avg = getAverageRank("Robert", "M");
//        System.out.println("Average = " + avg);
        // 13. 323200
//        int totalNum = getTotalBirthsRankedHigher(1990, "Emily", "F");
//        System.out.println("Total numbers of name ranked higher: " + totalNum);
        // 14. 1498074
//        int totalNum = getTotalBirthsRankedHigher(1990, "Drew", "M");
//        System.out.println("Total numbers of name ranked higher: " + totalNum);
    }
    public static void main(String[] args){
        MiniProject inst = new MiniProject();
        inst.testMethods();
    }
}
