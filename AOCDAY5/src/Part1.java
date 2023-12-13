import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY5\\src\\input.txt");
        Scanner scanner = new Scanner(myObj);

        List<Map<Long[], Long>> maps = new ArrayList<>();

        Map<Long[], Long> seedToSoil = new HashMap<>();
        Map<Long[], Long> soilToFert = new HashMap<>();
        Map<Long[], Long> fertToWater = new HashMap<>();
        Map<Long[], Long> waterToLight = new HashMap<>();
        Map<Long[], Long> lightToTemp = new HashMap<>();
        Map<Long[], Long> tempToHumid = new HashMap<>();
        Map<Long[], Long> humidToLoc = new HashMap<>();

        maps.add(seedToSoil);
        maps.add(soilToFert);
        maps.add(fertToWater);
        maps.add(waterToLight);
        maps.add(lightToTemp);
        maps.add(tempToHumid);
        maps.add(humidToLoc);

        List<Long> IDs = new ArrayList<>();

        scanner.next();
        String currentToken = scanner.next();

        while (currentToken.matches("[0-9]+")) {
            IDs.add(Long.parseLong(currentToken));
            currentToken = scanner.next();
        }


        while (!currentToken.matches("[0-9]+")) {
            currentToken = scanner.next();
        }

        // Fill seed-to-soil map:
        while (currentToken.matches("[0-9]+")) {
            long sourceL = Long.parseLong(scanner.next());
            long sourceRange = Long.parseLong(scanner.next());

            Long[] sourceBound = {sourceL, sourceL + sourceRange - 1};
            seedToSoil.put(sourceBound, Long.parseLong(currentToken));
            currentToken = scanner.next();
        }


        while (!currentToken.matches("[0-9]+")) {
            currentToken = scanner.next();
        }


        // Fill soil-to-fert map:
        while (currentToken.matches("[0-9]+")) {
            long sourceL = Long.parseLong(scanner.next());
            long sourceRange = Long.parseLong(scanner.next());

            Long[] sourceBound = {sourceL, sourceL + sourceRange - 1};
            soilToFert.put(sourceBound, Long.parseLong(currentToken));
            currentToken = scanner.next();
        }


        while (!currentToken.matches("[0-9]+")) {
            currentToken = scanner.next();
        }

        // Fill fert-to-water map:
        while (currentToken.matches("[0-9]+")) {
            long sourceL = Long.parseLong(scanner.next());
            long sourceRange = Long.parseLong(scanner.next());

            Long[] sourceBound = {sourceL, sourceL + sourceRange - 1};
            fertToWater.put(sourceBound, Long.parseLong(currentToken));
            currentToken = scanner.next();
        }


        while (!currentToken.matches("[0-9]+")) {
            currentToken = scanner.next();
        }


        // Fill water-to-light map:
        while (currentToken.matches("[0-9]+")) {
            long sourceL = Long.parseLong(scanner.next());
            long sourceRange = Long.parseLong(scanner.next());

            Long[] sourceBound = {sourceL, sourceL + sourceRange - 1};
            waterToLight.put(sourceBound, Long.parseLong(currentToken));
            currentToken = scanner.next();
        }

        while (!currentToken.matches("[0-9]+")) {
            currentToken = scanner.next();
        }

        // Fill light-to-temp map:
        while (currentToken.matches("[0-9]+")) {
            long sourceL = Long.parseLong(scanner.next());
            long sourceRange = Long.parseLong(scanner.next());

            Long[] sourceBound = {sourceL, sourceL + sourceRange - 1};
            lightToTemp.put(sourceBound, Long.parseLong(currentToken));
            currentToken = scanner.next();
        }

        while (!currentToken.matches("[0-9]+")) {
            currentToken = scanner.next();
        }
        // Fill temp-to-humid map:
        while (currentToken.matches("[0-9]+")) {
            long sourceL = Long.parseLong(scanner.next());
            long sourceRange = Long.parseLong(scanner.next());

            Long[] sourceBound = {sourceL, sourceL + sourceRange - 1};
            tempToHumid.put(sourceBound, Long.parseLong(currentToken));
            currentToken = scanner.next();
        }

        while (!currentToken.matches("[0-9]+")) {
            currentToken = scanner.next();
        }

        // Fill humid-to-loc map:
        while (currentToken.matches("[0-9]+")) {
            long sourceL = Long.parseLong(scanner.next());
            long sourceRange = Long.parseLong(scanner.next());

            Long[] sourceBound = {sourceL, sourceL + sourceRange - 1};
            humidToLoc.put(sourceBound, Long.parseLong(currentToken));
            if (scanner.hasNext()) {
                currentToken = scanner.next();
            } else break;
        }

        List<Long> locations = new ArrayList<>();

        for (long ID : IDs) {
            long temp = ID;
            int i = 0;
            while (i < maps.size()) {
                Map<Long[], Long> map = maps.get(i);
                temp = func1(temp, map);
                i++;
            }
            locations.add(temp);
        }

        long result = Collections.min(locations);
        System.out.println(result);

    }

    public static long func1(long ID, Map<Long[], Long> map) {
        long result = ID;
        for (Long[] key : map.keySet()) {
            if (ID >= key[0] && ID <= key[1]) {
                long temp = map.get(key);
                result = temp + (ID - key[0]);
                break;
            }
        }
        return result;
    }
}







