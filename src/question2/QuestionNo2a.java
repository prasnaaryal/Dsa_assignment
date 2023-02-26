package question2;



import java.util.*;

class Ancestors {
    // A helper function to calculate the greatest common divisor using Euclid's algorithm

    public int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }
    // Depth-first search function to traverse the tree and find the ancestors of each node

    public void dfs(int[] nums, LinkedList<Integer>[] tree, int depth, int node, boolean[] visited, int[] ans, Map<Integer,int[]> map, boolean[][] poss) {
        if(visited[node]) return;
        visited[node] = true;
        // Initialize variables to track the closest ancestor with a coprime value and its depth

        int ancestor = -1;
        int d = Integer.MAX_VALUE;
        // Iterate through all possible values that could be a coprime with the current node's value

        for(int i = 1; i < 51; i++) {
            // If there is a path from the current node's value to the current possible coprime value and it has already been visited before

            if(poss[nums[node]][i] && map.containsKey(i)) {
                // If the difference in depth between the current node and the ancestor with this coprime value is less than or equal to the previous closest ancestor

                if(depth - map.get(i)[0] <= d) {
                    d = depth - map.get(i)[0];
                    ancestor = map.get(i)[1];
                }
            }
        }
        // Set the ancestor of the current node to be the closest ancestor found (or -1 if none were found)

        ans[node] = ancestor;
        int[] exist = (map.containsKey(nums[node])) ? map.get(nums[node]) :  new int[]{-1,-1};
        map.put(nums[node],new int[]{depth,node});
        // Recurse on all children of the current node

        for(int child : tree[node]) {
            if(visited[child]) continue;
            dfs(nums, tree, depth+1, child, visited, ans, map, poss);
        }
        // Update the map of values to their last-seen depth and node, or remove them if they weren't seen before

        if(exist[0] != -1) map.put(nums[node], exist);
        else map.remove(nums[node]);

        return;
    }

    public int[] getCoprimes(int[] nums, int[][] edges) {
        boolean[][] poss = new boolean[51][51];
        for(int i = 1; i < 51; i++) {
            for(int j = 1; j < 51; j++) {
                if(gcd(i,j) == 1) {
                    poss[i][j] = true;
                    poss[j][i] = true;
                }
            }
        }
        // Determine the number of nodes in the tree

        int n = nums.length;
        LinkedList<Integer>[] tree = new LinkedList[n];

        for(int i =0 ; i < tree.length; i++) tree[i] = new LinkedList<>();
        for(int edge[] : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = -1;
        Map<Integer,int[]> map = new HashMap<>();

        boolean[] visited = new boolean[n];
        dfs(nums, tree, 0, 0, visited, ans, map, poss);

        return ans;
    }

    public static void main(String[] args) {
        int[][] edges =  {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}};
        int [] values = {3,2,6,6,4,7,12};
        Ancestors listOfAncestors = new Ancestors();
        int[] ans = listOfAncestors.getCoprimes(values,edges);
        List<Integer> answer= new ArrayList<Integer>();
        for (int i = 0;i<ans.length;i++) {
            answer.add(ans[i]);
        }
        System.out.println(answer);
    }


}