import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY4\\src\\input.txt");
        Scanner scanner = new Scanner(myObj);

        List<List<Integer>> allCardData = new ArrayList<>();

        int m = 0;

        while(scanner.hasNext()) {
            String data = scanner.nextLine();
            int n = data.length();

            Set<Integer> winningNums = new HashSet<>();
            List<Integer> myNums = new ArrayList<>();

            String winningNumsString = data.substring(10, 39).trim();
            String[] numsArrayString = winningNumsString.split("\\s+");
            for(String s : numsArrayString) {
                winningNums.add(Integer.parseInt(s));
            }

            String myNumsString = data.substring(42, n).trim();
            String[] myNumsArrayString = myNumsString.split("\\s+");
            for(String s : myNumsArrayString) {
                myNums.add(Integer.parseInt(s));
            }

            int countWinNums = 0;
            for(int i : myNums) {
                if(winningNums.contains(i)){
                    countWinNums++;
                }
            }

            Integer[] cardData = {m, 1, countWinNums};
            allCardData.add(Arrays.asList(cardData));
            m++;
        }

        for(int i = 0; i < allCardData.size() - 1; i++) {
            int winNum = allCardData.get(i).get(2);
            int copies = allCardData.get(i).get(1);
            for(int k = 0; k < copies; k++) {
                int j = 1;
                while (j <= winNum && i + j < allCardData.size()) {
                    int temp = allCardData.get(i + j).get(1);
                    allCardData.get(i + j).set(1, temp + 1);
                    j++;
                }
            }
        }

        int result = 0;

        for(List<Integer> list : allCardData) {
            result += list.get(1);
        }
        System.out.println(result);
    }
}

