public class keyrsluforrit {
    public static void main(String[] args) {
        //~ Skanni sem les inn tölurnar
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine()); //vitum að fyrsta línan er bara ein tala
        EdgeWeightedGraph EWG = new EdgeWeightedGraph(n);
        
        //~ F: Búið er að lesa inn eina línu af staðalinntaki.
        while (scanner.hasNext()) {
            //~ I: Búið er að lesa inn eina eða fleiri línur af staðalinntaki 
            //~    og gera viðeigandi aðgerðir með þær.
            String line = scanner.nextLine(); //tekur inntak fram að næsta enter
            String[] S = line.split(" "); //fylki af orðunum í línunni (skipt eftir bilum)
            EWG.addEdge( new Edge(  Integer.parseInt(S[0]), 
                                    Integer.parseInt(S[1]),
                                    Integer.parseInt(S[2]) ));
        }
        //~ E: Búið er að lesa inn allar línur af staðalinntaki
        //~    og gera viðeigandi aðgerðir með þær.
        
        KruskalMST MST = new KruskalMST(EWG);
        Queue<Edge> mst = (Queue<Edge>) MST.edges();
        
        //~ Prentum út á staðalúttak svörin við verkefninu.
        System.out.println((int)MST.weight()); // vigt minnsta spantrés G
        for (Edge e : MST.edges2()) {
            KruskalMST MSTe = new KruskalMST( EWG, mst, e );
            System.out.println(e.toString2() + " " + MSTe.weight()); // u v w  þar sem (u,v) eru leggir minnsta
                                                                     // spantrés G\{e} með vigt w raðað eftir u
        }
    }
}