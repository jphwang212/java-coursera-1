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
    public int countGenes(String dna){
        int count = 0;
        for(int i = 0; i < dna.length() - 3; i++){
            String startCodon = dna.substring(i, i + 3);
            if(startCodon.equals("ATG")){
                String codon = findGene(dna.substring(i));
                if(codon.length() > 0){
                    count++;
                }
                i += 2;
            }
        }
        return count;
    }
    public void testCountGenes(){
        String dna1 = "ATGTAAAGTTGGTAA";
        String dna2 = "ATGAATTGAATGTAG";
        String dna3 = "ATG";
        int test1 = countGenes(dna1);
        int test2 = countGenes(dna2);
        int test3 = countGenes(dna3);
        System.out.println("Test 1 = " + test1);
        System.out.println("Test 2 = " + test2);
        System.out.println("Test 3 = " + test3);
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
        Part3 p3 = new Part3();
        p3.testCountGenes();
    }
}
