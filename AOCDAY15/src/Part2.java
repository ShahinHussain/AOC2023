import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY15\\src\\input.txt");
        Scanner sc = new Scanner(myFile);
        String[] input = sc.nextLine().split(",");

        Map<Integer, List<List<String>>> map = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            List<List<String>> list = new ArrayList<>();
            map.put(i, list);
        }

        for (int i = 0; i < input.length; i++) {
            fillBoxes(input[i], map);
        }

        long output = 0;

        for (int i : map.keySet()) {
            List<List<String>> values = map.get(i);
            for (int j = 0; j < map.get(i).size(); j++) {
                List<String> value = values.get(j);
                output += (long) (i + 1) * (j + 1) * Integer.parseInt(value.get(1));
            }
        }
        System.out.println(output);
    }

    public static int calc(String str) {
        int n = str.length();
        int i = 0;
        int out = 0;

        StringBuilder sb = new StringBuilder();
        sb.append(str, 0, i);
        while (i < n) {
            sb.append(str.charAt(i));
            out = ((out + (int) str.charAt(i)) * 17) % 256;
            i++;
            if (i == n) {
                break;
            } else {
                sb.append(str.charAt(i));
            }
        }
        return out;
    }

    public static void fillBoxes(String str, Map<Integer, List<List<String>>> map) {
        String[] vals = str.split("\\W");
        List<String> valsList = Arrays.asList(vals);
        String[] symStr = str.split("\\w");
        String sym = symStr[symStr.length - 1];
        int boxNum = calc(vals[0]);

        List<List<String>> boxContent = map.get(boxNum);
        boolean added = false;
        for (int i = 0; i < boxContent.size(); i++) {
            List<String> list = boxContent.get(i);
            if (list.get(0).equals(valsList.get(0))) {
                if (sym.equals("=")) {
                    list.set(1, valsList.get(1));
                    added = true;
                }
                if (sym.equals("-")) {
                    boxContent.remove(list);
                }
            }
        }
        if (!added && sym.equals("=")) {
            boxContent.add(valsList);
        }
    }
}
