import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("src/input.txt");
            Scanner scanner = new Scanner(file);

            Map<Integer, String> map = new HashMap<>();
            map.put(1, "one");
            map.put(2, "two");
            map.put(3, "three");
            map.put(4, "four");
            map.put(5, "five");
            map.put(6, "six");
            map.put(7, "seven");
            map.put(8, "eight");
            map.put(9, "nine");

            int output = 0;
            List<Integer> nums = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                StringBuilder sb = new StringBuilder();
                StringBuilder lText = new StringBuilder();
                StringBuilder rText = new StringBuilder();
                int n = data.length(), l = 0, r = n - 1;

                while(l < n) {
                    char lc = data.charAt(l);
                    if (Character.isDigit(lc)) {
                        sb.append(lc);
                        break;
                    }

                    lText.append(lc);

                    String dataTemp = lText.toString();
                    int leftNum = 0;

                    for(int i = 1; i <= 9; i++) {
                        if(dataTemp.contains(map.get(i))) {
                            leftNum = i;
                            sb.append(leftNum);
                            break;
                        }
                    }

                    if (leftNum != 0) {
                        break;
                    }

                    l++;
                }

                while(r >= 0) {
                    char rc = data.charAt(r);
                    if (Character.isDigit(rc)) {
                        sb.append(rc);
                        break;
                    }

                    rText.insert(0, rc);

                    String dataTemp = rText.toString();
                    int rightNum = 0;

                    for(int i = 1; i <= 9; i++) {
                        if(dataTemp.contains(map.get(i))) {
                            rightNum = i;
                            sb.append(rightNum);
                            break;
                        }
                    }

                    if (rightNum != 0) {
                        break;
                    }
                    r--;
                }

                nums.add(Integer.parseInt(sb.toString()));

            }

            for (int i : nums) {
                output += i;
            }

            System.out.println(output);


            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }




    }
}