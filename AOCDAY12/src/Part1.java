import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY12\\src\\input.txt");
        Scanner scanner = new Scanner(myFile);


        while (scanner.hasNext()) {
            String[] arr = scanner.nextLine().split("\\s+");
            String sequence = arr[0];
            String[] tokens = arr[1].split(",");

            List<Integer> array = new ArrayList<>(tokens.length);
            for(int i = 0; i < tokens.length; i++) {
                array.add(Integer.parseInt(tokens[i]));
            }

            Map<Integer, List<String>> map = new HashMap<>();
            List<int[]> lmrmGs = lmrmGeneral(array);
            Map<Integer, Integer> RMBs = getRMB(array, sequence);


            int j = array.size() - 1;

            while(j >= 0) {
                if(j == array.size() - 1) {
                    List<String> perms = calc(array, sequence);
                    map.put(j, perms);
                }
                else {
                    List<String> values = new ArrayList<>();
                    int[] lmrmCurr = lmrmGs.get(j);
                    int rmbCurr = RMBs.get(j);

                    while(lmrmCurr[1] <= rmbCurr) {
                        for (int i = 1; i < map.get(j + 1).size(); i++) {
                            String temp = map.get(j + 1).get(i);
                            StringBuilder tempSB = new StringBuilder(temp);

                            tempSB.setCharAt(lmrmCurr[0], '.');
                            tempSB.setCharAt(lmrmCurr[1] + 1, '#');
                            values.add(tempSB.toString());
                            lmrmCurr[0]++;
                            lmrmCurr[1]++;
                        }
                        map.put(j, values);
                    }
                }
                j--;
            }

            for(int key : map.keySet()) {
                for(String s : map.get(key)) {
                    System.out.println(s);
                }
            }
        }












////        List<Integer> list = new ArrayList<>();
////        list.add(3);
////        list.add(2);
////        list.add(1);
////
////        String seq = "??????????";
//
//        List<String> result = calc(list, seq);





    }







    // lmrm default seq (all left)
    public static List<int[]> lmrmGeneral(List<Integer> list) {
        List<int[]> lmRm = new ArrayList<>();
        lmRm.add(new int[]{0, list.get(0) - 1});

        for (int i = 1; i < list.size(); i++) {
            int r = lmRm.get(i - 1)[1] + 1 + list.get(i);
            int l = r + 1 - list.get(i);
            lmRm.add(new int[]{l, r});
        }
        return lmRm;
    }

    // rmb for all groups
    public static Map<Integer, Integer> getRMB(List<Integer> list, String seq) {
        Map<Integer, Integer> map = new HashMap<>();

        int n = seq.length();
        int totalSum = 0;

        for (int l : list) {
            totalSum += l;
        }

        int sumSoFar = 0;
        int i = 0;
        while (i < list.size()) {
            if (i == list.size() - 1) {
                map.put(i, seq.length() - 1);
                return map;
            }
            sumSoFar += list.get(i);
            int rmb = n - (totalSum - sumSoFar + (list.size() - i));
            map.put(i, rmb);
            i++;
        }
        return map;
    }

        // (3 2 1)   (4, 7, 9) ###.##.#..
    public static List<String> calc(List<Integer> arr, String seq) {
        List<String> perms = new ArrayList<>();

        int n = seq.length();

        List<int[]> lmrm = lmrmGeneral(arr);
        Map<Integer, Integer> rmbs = getRMB(arr, seq);
        int lastGroupRmb = rmbs.get(arr.size() - 1);


        while(lmrm.get((arr.size() - 1))[1] <= lastGroupRmb) {
            StringBuilder temp = new StringBuilder();
            int j = 0;
            for (int i = 0; i < arr.size(); i++) {
                int l = lmrm.get(i)[0];
                int r = lmrm.get(i)[1];

                while (j < l) {
                    temp.append('.');
                    j++;
                }
                while (j >= l & j <= r) {
                    temp.append('#');
                    j++;
                }
                if (j < n) {
                    temp.append('.');
                }
                j++;
            }
            while (j < seq.length()) {
                temp.append('.');
                j++;
            }
            perms.add(temp.toString());

            lmrm.get((arr.size() - 1))[0]++;
            lmrm.get((arr.size() - 1))[1]++;
        }
        return perms;
    }
}

