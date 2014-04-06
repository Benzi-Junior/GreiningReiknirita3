

public class KruskalMST{
    private int weight;  // weight of MST
    private Queue<Edge> mst = new Queue<Edge>();  // edges in MST	
	
	
	//~ Notkun: KruskalMST KMST = new KruskalMST(G);
    //~ Fyrir: G er hlutur af taginu EdgeWeightedGraph.
    //~ Eftir: KMST er minnsta spannandi tré fyrir G (minnsti spannandi skógur ef G er ekki samanhangandi).
	public KruskalMST(EdgeWeightedGraph G) {
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
                weight += e.weight();
            }
        }
    }
	
	//~ Notkun: w = KMST.secondMSTweight();
    //~ Fyrir: KMST er hlutur af taginu KruskalMST
    //~ Eftir: w er heildarvigt leggjanna í KMST.
	public int secondMSTweight( EdgeWeightedGraph G, Edge d ){
		MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        int weight2 = weight - d.weight();
        
		UF uf = new UF(G.V());
		for (Edge e : this.edges()) {
			if(e.equals(d)) continue;
            int v = e.either();
            int w = e.other(v);
            uf.union(v,w);
		}
		
		while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            if(e.equals(d)) continue;
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
				return weight - d.weight() + e.weight();
            }
        }
        return weight - d.weight();
	}
	
	public Iterable<Edge> edges() {
        return mst;
    }
	
    //~ Notkun: Iterable<Edge> E = KMST.sortedEdges();
    //~ Fyrir: KMST er hlutur af taginu KruskalMST
    //~ Eftir: E inniheldur öll stök mst og er raðað vaxandi eftir fyrri hnút leggsins.
    public Iterable<Edge> sortedEdges() {
        MinPQ<Edge> sorted = new MinPQ<Edge>(new Edge.lexiCompare());
        for (Edge e : mst) {
            sorted.insert(e);
        } 
        return sorted;
    }    
    
    //~ Notkun: w = KMST.weight();
    //~ Fyrir: KMST er hlutur af taginu KruskalMST
    //~ Eftir: w er heildarvigt leggjanna í KMST.
    public int weight() {
        return weight;
    }
    
    public static void main(String[] args){}
}
