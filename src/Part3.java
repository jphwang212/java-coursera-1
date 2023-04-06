public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIdx = dna.indexOf(stopCodon, startIndex);
        if(stopIdx == -1 || ((stopIdx - startIndex) % 3 != 0)){
            return dna.length();
        }
        return stopIdx;
    }
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        String[] stopCodons = new String[]{"TAA", "TAG", "TGA"};
        int smallest = dna.length();
        for(String codon : stopCodons){
            int stopIndex = findStopCodon(dna, startIndex + 3, codon);
            if(stopIndex == -1){
                continue;
            }
            if(stopIndex < smallest){
                smallest = stopIndex;
            }
        }
        if(smallest == dna.length()){
            return "";
        }

        String gene = dna.substring(startIndex, smallest + 3);
        return gene;
    }
    public void testFindGene(){
        String dna1 = "TAATAGTGA";          // test 1: No ATG
        String dna2 = "ATGTTATGGAAT";       // test 2: No stop codon
        String dna3 = "ATGAGAAGTTAA";       // test 3: Passes with 1 stop codon
        String dna4 = "ATGAGTTAAGGATGATAG"; // test 4: Passes with multiple stops
        String dna5 = "ATG";                // test 5: Random
        System.out.println("Dna 1: " + findGene(dna1));
        System.out.println("Dna 2: " + findGene(dna2));
        System.out.println("Dna 3: " + findGene(dna3));
        System.out.println("Dna 4: " + findGene(dna4));
        System.out.println("Dna 5: " + findGene(dna5));
    }
    public static void main(String[] args){

    }
}
