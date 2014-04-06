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
        System.out.println(MST.weight());
        for (Edge e : MST.sortedEdges()) {
            System.out.println(e.toString2() + " " + MST.secondMSTweight(EWG,e));
        }
    }
}
