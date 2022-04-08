import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dijkastra_Problem {
    public static List<Node> graph = new ArrayList<>();
    public static List<Way> V = new ArrayList<>();
    public static List<Integer> visited = new ArrayList<>();
    public static int nbCites;
    public static int villeDep;
    public  static int Vmin;

    public static void fillGraph(){
        boolean rep;
        Scanner s = new Scanner(System.in);
        for(int i = 0;i<nbCites;i++){
            Node n = new Node();
            n.setNumberOfNode(i+1);
            for (int j = 0 ;j<nbCites;j++){
                if (j != i) {
                    System.out.println("est-ce que le " + (i+1) + "eme city voisin de le " + (j+1) + "eme ? (true or false !)");
                    rep = s.nextBoolean();
                    if (rep) {
                        NextTo nt = new NextTo();
                        nt.setNumberOfCity(j+1);
                        System.out.println("donner la valeur");
                        nt.setValue(s.nextInt());
                        n.getNextNode().add(nt);
                    }
                }
            }
            graph.add(n);
        }
    }

    public static void initializeV(){
        for (int i = 0;i<nbCites;i++){
            Way w = new Way();
            w.setValue(Integer.MAX_VALUE);
            V.add(w);
        }
        V.get(villeDep-1).setValue(0);
    }

    public static Node searchMinValue(){
        Node x = new Node();

        for (int i=0;i<nbCites;i++){
            if ( (! visited.contains(graph.get(i).getNumberOfNode())) && (V.get(i).getValue() < Vmin) ){
                x.setNumberOfNode(graph.get(i).getNumberOfNode());
                for (int j=0;j<graph.get(i).getNextNode().size();j++){
                    NextTo nt = new NextTo();
                    nt.setValue(graph.get(i).getNextNode().get(j).getValue());
                    nt.setNumberOfCity(graph.get(i).getNextNode().get(j).getNumberOfCity());
                    x.getNextNode().add(nt);
                }
                Vmin = V.get(i).getValue();
            }
        }
        return x;
    }


    public static void Dijkastra(){
        initializeV();
        do {
            Vmin=Integer.MAX_VALUE;
            Node x = new Node();
            x = searchMinValue();

            if (Vmin < Integer.MAX_VALUE){
                visited.add(x.getNumberOfNode());
                for (int j=0;j<x.getNextNode().size();j++){
                    if ( (! visited.contains(x.getNextNode().get(j).getNumberOfCity()) ) && ((V.get(x.getNumberOfNode()-1).getValue() + x.getNextNode().get(j).getValue()) < V.get(x.getNextNode().get(j).getNumberOfCity()-1).getValue() ) ){
                        V.get(x.getNextNode().get(j).getNumberOfCity()-1).setValue(V.get(x.getNumberOfNode()-1).getValue() + x.getNextNode().get(j).getValue());
                        V.get(x.getNextNode().get(j).getNumberOfCity()-1).getMinWay().clear();
                        for (int k = 0;k<V.get(x.getNumberOfNode()-1).getMinWay().size();k++){
                            V.get(x.getNextNode().get(j).getNumberOfCity()-1).getMinWay().add(V.get(x.getNumberOfNode()-1).getMinWay().get(k));
                        }
                        V.get(x.getNextNode().get(j).getNumberOfCity()-1).getMinWay().add(x.getNumberOfNode());
                    }
                }
            }
        }while (Vmin != Integer.MAX_VALUE);
    }



    public static void showMinWay(int k){
        for (int i=0;i<V.get(k-1).getMinWay().size();i++){
            System.out.print(V.get(k-1).getMinWay().get(i) + " - ");
        }
        System.out.print(k);
    }


    public static void show(){
        for(int i=0;i<nbCites;i++){
            System.out.println("city number"+graph.get(i).getNumberOfNode());
            System.out.println("avoir "+graph.get(i).getNextNode().size()+" cites :");
            for (int j = 0;j<graph.get(i).getNextNode().size();j++){
                System.out.println(graph.get(i).getNextNode().get(j).getNumberOfCity()+" de valeur "+graph.get(i).getNextNode().get(j).getValue());
            }
        }
    }

    public static void main(String[] args){
        int k;
        Scanner s = new Scanner(System.in);
        System.out.println("donner nombre des villes");
        nbCites = s.nextInt();

        fillGraph();
        show();
        System.out.println("donner la ville de depart");
        villeDep = s.nextInt();
        Dijkastra();
        System.out.println("donner la ville finale");
        k = s.nextInt();
        showMinWay(k);
    }

}
