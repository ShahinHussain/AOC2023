import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY7\\src\\input.txt");
        Scanner scanner = new Scanner(myFile);

        // char values
        Map<Character, Integer> cardMap = new HashMap<>();

        cardMap.put('A', 14);
        cardMap.put('K', 13);
        cardMap.put('Q', 12);
        cardMap.put('J', 1);
        cardMap.put('T', 10);
        cardMap.put('9', 9);
        cardMap.put('8', 8);
        cardMap.put('7', 7);
        cardMap.put('6', 6);
        cardMap.put('5', 5);
        cardMap.put('4', 4);
        cardMap.put('3', 3);
        cardMap.put('2', 2);


        // Map with different types separated
        Map<Integer, List<List<String>>> cardType = new HashMap<>();

        for (int i = 1; i <= 7; i++) {
            List<List<String>> list = new ArrayList<>();
            cardType.put(i, list);
        }

        // Map to rank cards
        Map<Character, Integer> ranker = new HashMap<>();


        while (scanner.hasNext()) {
            List<String> temp = new ArrayList<>(2);

            temp.add(0, scanner.next());
            temp.add(1, scanner.next());

            String sequence = temp.get(0);
            for (int i = 0; i < sequence.length(); i++) {
                char c = sequence.charAt(i);
                ranker.put(c, ranker.getOrDefault(c, 0) + 1);
            }

            int n = ranker.size();

            List<List<String>> vals;
            switch (n) {
                case 5:
                    if (ranker.containsKey('J')) {
                        vals = cardType.get(2);
                        vals.add(comparison(cardMap, sequence, vals), temp);
                    }
                    else {
                        vals = cardType.get(1);
                        vals.add(comparison(cardMap, sequence, vals), temp);
                    }
                    break;
                case 4:
                    if (ranker.containsKey('J')) {
                        vals = cardType.get(4);
                        vals.add(comparison(cardMap, sequence, vals), temp);
                    }
                    else {
                        vals = cardType.get(2);
                        vals.add(comparison(cardMap, sequence, vals), temp);
                    }
                    break;
                case 3:
                    int v = Collections.max(ranker.values());
                    if (v == 2) {
                        if(ranker.containsKey('J')) {
                            if(ranker.get('J') == 1) {
                                vals = cardType.get(5);
                                vals.add(comparison(cardMap, sequence, vals), temp);
                            }
                            else {
                                vals = cardType.get(6);
                                vals.add(comparison(cardMap, sequence, vals), temp);
                            }
                        }
                        else {
                            vals = cardType.get(3);
                            vals.add(comparison(cardMap, sequence, vals), temp);
                        }
                    }
                    else {
                        if(ranker.containsKey('J')) {
                            vals = cardType.get(6);
                            vals.add(comparison(cardMap, sequence, vals), temp);
                        }
                        else {
                            vals = cardType.get(4);
                            vals.add(comparison(cardMap, sequence, vals), temp);
                        }
                    }
                    break;
                case 2:
                    int a = Collections.max(ranker.values());
                    if(ranker.containsKey('J')) {
                        vals = cardType.get(7);
                        vals.add(comparison(cardMap, sequence, vals), temp);
                    }
                    else {
                        if (a == 3) {
                            vals = cardType.get(5);
                            vals.add(comparison(cardMap, sequence, vals), temp);
                        }
                        else {
                            vals = cardType.get(6);
                            vals.add(comparison(cardMap, sequence, vals), temp);
                        }
                    }
                    break;
                case 1:
                    vals = cardType.get(7);
                    vals.add(comparison(cardMap, sequence, vals), temp);
                    break;
            }
            ranker.clear();
        }

        long output = 0;
        int n = 1000;

        for(int i = 7; i >= 1; i --) {
            List<List<String>> vals = cardType.get(i); {
                for(int j = vals.size() - 1; j >= 0; j--) {
                    long bid = Long.parseLong(vals.get(j).get(1));
                    output += (n * bid);
                    n--;
                }
            }
        }

        System.out.println(output);

    }

    public static int comparison(Map<Character, Integer> map, String card, List<List<String>> values) {
        if(values.isEmpty()) return 0;

        int i = 0;
        int j = 0;

        while(j < values.size()) {
            char c = card.charAt(i);
            String val = values.get(j).get(0);

            if(map.get(c) > map.get(val.charAt(i)) && i == 0) {
                j++;
                continue;
            }
            if(map.get(c) == map.get(val.charAt(i))) {
                i++;
            }
            else if (map.get(c) > map.get(val.charAt(i))) {
                j++;
                i = 0;
            }
            else return j;
        }
        return j;
    }
}

