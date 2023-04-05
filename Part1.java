import java.util.Collections;
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIdx = dna.indexOf(stopCodon, startIndex);
        if(stopIdx == -1 || ((stopIdx - startIndex) % 3 != 0)){
            return dna.length();
        }
        return stopIdx;
    }
    public void testFindStopCodon(){
        String dna1 = "TAATAGTGA";
        String dna2 = "ATGTTATGGAAT";
        String dna3 = "ATGATAAGTTAA";
        String dna4 = "ATGAGTTAAGGATGATAG";
        String dna5 = "ATG";
        int stopIndex = findStopCodon(dna1, );
        System.out.println("The stop index is: " + stopIndex);
    }
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int stopIndex1 = findStopCodon(dna, startIndex, "TAA");
        int stopIndex2 = findStopCodon(dna, startIndex, "TAG");
        int stopIndex3 = findStopCodon(dna, startIndex, "TGA");
        indexes.add(stopIndex1);
        indexes.add(stopIndex2);
        indexes.add(stopIndex3);
        indexes.removeIf(idx -> idx == -1);
        if(indexes.isEmpty()){
            return "";
        }
        String gene = dna.substring(startIndex, Collections.min(indexes));
        return gene;
    }
    public void testFindGene(){
        String dna1 = "TAATAGTGA";          // test 1: No ATG
        String dna2 = "ATGTTATGGAAT";       // test 2: No stop codon
        String dna3 = "ATGATAAGTTAA";       // test 3: Passes with 1 stop codon
        String dna4 = "ATGAGTTAAGGATGATAG"; // test 4: Passes with multiple stops
        String dna5 = "ATG";                // test 5: Random
    }
}
