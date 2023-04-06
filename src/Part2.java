public class Part2 {
    public int howMany(String stringA, String stringB){
        int start = 0;
        int count = 0;
        while(true){
            int startIdx = stringB.indexOf(stringA, start);
            if(startIdx == -1 || (startIdx + stringA.length() > stringB.length())){
                break;
            }
//            String sub = stringb.substring(startIdx, startIdx + stringa.length());
            count++;
            start = start + startIdx + stringA.length();
        }
        return count;
    }
    public void testHowMany(){
        String example = "ATGATTATTGTATTAAATGTAG";
        String example1 = "ATG";
        String example2 = "TAA";
        String example3 = "TAG";
        String example4 = "TGA";
        int test1 = howMany(example1, example);
        int test2 = howMany(example2, example);
        int test3 = howMany(example3, example);
        int test4 = howMany(example4, example);
        System.out.println("Test 1: " + test1);
        System.out.println("Test 2: " + test2);
        System.out.println("Test 3: " + test3);
        System.out.println("Test 4: " + test4);
    }
    public static void main(String[] args) {
        Part2 testInstance = new Part2();
        testInstance.testHowMany();
    }
}
