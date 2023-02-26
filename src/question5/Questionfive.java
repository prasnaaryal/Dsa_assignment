import java.util.*;

class KeyPoint {
    int x;
    int y;

    KeyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public static List<KeyPoint> getBorder(int[][] height) {
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
    public static  int[][] print(List<KeyPoint> result){
        int[][] arr = new int[result.size()][2];

        for (int i=0; i<result.size(); i++){
            arr[i][0]=result.get(i).x;
            arr[i][1]=result.get(i).y;

        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] m = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}};
        System.out.println(Arrays.deepToString(print(getBorder(m))));
    }
}