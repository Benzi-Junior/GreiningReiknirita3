

public class KruskalMST{
    // weight er summa e.weight() yfir öll e í mst.
    private int weight = 0;
    private Queue<Edge> mst = new Queue<Edge>();
    
    
    //~ Notkun: KruskalMST KMST = new KruskalMST(G);
    //~ Fyrir: G er hlutur af taginu EdgeWeightedGraph.
    //~ Eftir: KMST er minnsta spannandi tré fyrir G
    //      (minnsti spannandi skógur ef G er ekki samanhangandi).
    public KruskalMST(EdgeWeightedGraph G) {
        // Raða leggjum G eftir vigt.
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            // uf inniheldur samhengisþætti nets af stærð G.V()
            // með mengi leggja = mst.
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
    //~ Fyrir: KMST inniheldur d og er minnsta spanntré fyrir G.
    //~ Eftir: w er vigt minnsta spannandi trés (eða skógar)
    //      netsins G þegar leggurinn d hefur verið fjarlægður.
    public int secondMSTweight( EdgeWeightedGraph G, Edge d ){
        // Raða leggjum G eftir vigt.
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        
        // Finn samhengisþætti KMST án leggsins d.
        UF uf = new UF(G.V());
        for (Edge e : this.edges()) {
            if(e.equals(d)) continue;
            int v = e.either();
            int w = e.other(v);
            uf.union(v,w);
        }
        
        // Finn þann legg í G, annan en d, sem tengir saman 
        // samhengisþættina tvo og hefur minnsta vigt.
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            if(e.equals(d)) continue;
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                return weight - d.weight() + e.weight();
            }
        }
        // d er eini leggurinn í G sem tengir samhengisþættina.
        return weight - d.weight();
    }
    
    //~ Notkun: Iterable<Edge> E = KMST.edges();
    //~ Fyrir: KMST er hlutur af taginu KruskalMST.
    //~ Eftir: E inniheldur öll stök mst.   
    public Iterable<Edge> edges() {
        return mst;
    }
    
    //~ Notkun: Iterable<Edge> E = KMST.sortedEdges();
    //~ Fyrir: KMST er hlutur af taginu KruskalMST.
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
}