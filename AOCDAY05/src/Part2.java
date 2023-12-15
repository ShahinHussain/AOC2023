import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
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

        List<Long[]> IDs = new ArrayList<>();

        scanner.next();
        String currentToken = scanner.next();

        while (currentToken.matches("[0-9]+")) {
            long current = Long.parseLong(currentToken);
            long range = Long.parseLong(scanner.next());
            Long[] currentRange = new Long[2];
            currentRange[0] = current;
            currentRange[1] = current + range;
            IDs.add(currentRange);

            currentToken = scanner.next();
        }


        List<Long[]> ranges = new ArrayList<>();

        //  CONVERTING LIST IDs TO NON-INTERSECTING LIST OF 2-TUPLES

        long min = 0, max = 0;

        for(Long[] list : IDs) {
            long minCurrent = list[0];
            long maxCurrent = list[1];
            if (ranges.size() == 0) {
                min = minCurrent;
                max = maxCurrent;
                ranges.add(list);
                continue;
            }
            if (maxCurrent < min) {
                min = minCurrent;
                ranges.add(list);
                continue;
            }
            if (minCurrent > max) {
                max = maxCurrent;
                ranges.add(list);
                continue;
            }
            for(Long[] otherList : ranges) {
                if (minCurrent > otherList[1] || maxCurrent < otherList[0]) continue;
                if (minCurrent > otherList[0]) {
                    if (maxCurrent > otherList[1]) {
                        otherList[1] = maxCurrent;
                    }
                }
                else if (maxCurrent < otherList[1]) {
                    otherList[0] = minCurrent;
                }
                else {
                    otherList[0] = minCurrent;
                    otherList[1] = maxCurrent;
                }
            }
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

        for(Long[] list : ranges) {
            for(long num = list[0]; num <= list[1]; num++) {
                locations.add(func(num, maps));
            }
        }

        System.out.println(Collections.min(locations));

    }

    public static long func(long ID, List<Map<Long[], Long>> maps) {
        for(int i = 0; i < maps.size(); i++) {
            Map<Long[], Long> map = maps.get(i);
            Part1.func1(ID, map);
        }
        return ID;
    }
}





//    public static long func(long ID, Map<Long[], Long> map) {
//        long result = ID;
//        for (Long[] key : map.keySet()) {
//            if (ID >= key[0] && ID <= key[1]) {
//                long temp = map.get(key);
//                result = temp + (ID - key[0]);
//                break;
//            }
//        }
//        return result;
//    }





