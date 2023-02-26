package question5;
import java.util.*;

public class QuestionfiveB {

    class KeyPoint {
        int x;
        int y;

        KeyPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class Solution {
        public List<KeyPoint> getBorder(int[][] height) {
            List<KeyPoint> result = new ArrayList<>();

            int n = height.length;

            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int i = 0; i < n; i++) {
                int x1 = height[i][0];
                int x2 = height[i][1];
                int h = height[i][2];

                if (!map.containsKey(x1)) {
                    map.put(x1, h);
                } else {
                    map.put(x1, Math.max(map.get(x1), h));
                }

                if (!map.containsKey(x2)) {
                    map.put(x2, 0);
                }
            }

            int max = 0;

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int x = entry.getKey();
                int h = entry.getValue();

                if (h != max) {
                    result.add(new KeyPoint(x, h));
                    max = h;
                }
            }

            return result;
        }
    }



//    explain

//    This implementation uses a TreeMap to store the x coordinates and their corresponding heights.
//    It first inserts the x coordinates and heights into the map and updates the height if a duplicate x coordinate is encountered.
//    Then it iterates through the map, checking if the height has changed.
//    If it has, it adds a new KeyPoint with the current x coordinate and height to the result list.
}
