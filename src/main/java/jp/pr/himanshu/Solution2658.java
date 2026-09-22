package jp.pr.himanshu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2658 {

    List<List<Integer>> connectedComponents;
    List<Integer>  currentPath;
    boolean[] visited;

    public int countCompleteComponents(int n, int[][] edges) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        connectedComponents = new ArrayList<>();
        int res = 0;
        visited = new boolean[n];
        for (int i= 0; i< n; i++){
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int source = edge[0];
            int target = edge[1];
            graph.get(source).add(target);
            graph.get(target).add(source);
        }

        for (int i= 0; i< n; i++){
            currentPath = new ArrayList<>();

            dfs(i, graph, currentPath);
            connectedComponents.add(currentPath);
        }
        for (int i= 0; i< connectedComponents.size(); i++){

                boolean found = true;
                List<Integer> currentNodes = connectedComponents.get(i);
                for(int j: currentNodes){
                    if(graph.get(j).size()!= currentNodes.size()-1){
                        found = false;
                        break;
                    }
                }
                if(found){
                    res++;
                }
        }
        return res;
    }

    public void dfs(int v, Map<Integer, List<Integer>> graph, List<Integer> path){
        visited[v] = true;
        currentPath.add(v);
        for (int i: graph.get(v)){
            if (!visited[i]){
                dfs(i, graph, path);
            }
        }
    }
}
