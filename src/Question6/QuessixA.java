package Question6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class QuessixA {


    class QuessixANode{
        int data;
        char c;
        QuessixANode left;
        QuessixANode right;
    }

    //prints the encoded QuessixA code
    public static void printCode(QuessixANode root, String s){
        if(root.left==null&&root.right==null&&Character.isLetter(root.c)){
            System.out.println(root.c+":"+s);
            return;
        }

        printCode(root.left,s+"0");
        printCode(root.right,s+"1");
    }

    //encoding
    public QuessixANode encode(char[] charArray, int[] charFreq){

        int n=charArray.length;

        PriorityQueue<QuessixANode> q = new PriorityQueue<>(n, new MyComparator());

        for(int i=0; i<n; i++){
            QuessixANode hn = new QuessixANode();
            hn.c=charArray[i];
            hn.data=charFreq[i];

            hn.left=null;
            hn.right=null;

            q.add(hn);
        }

        QuessixANode root=null;

        while(q.size()>1){
            QuessixANode x = q.peek();
            q.poll();
            QuessixANode y = q.peek();
            q.poll();
            QuessixANode f = new QuessixANode();
            f.data = x.data+y.data;
            f.c='-';
            f.left=x;
            f.right=y;

            root=f;

            q.add(f);

        }
        printCode(root,"");
        return root;
    }


    //decoding the QuessixA tree
    public void decode(QuessixANode root, String str){


        ArrayList characters= new ArrayList<>();
        ArrayList frequency=new ArrayList<>();

        int i=0;
        while(i<str.length()){
            QuessixANode current = root;
            while (current.c=='-'){
                if(str.charAt(i)=='0'){
                    current=current.left;
                    i++;
                }
                else {
                    current=current.right;
                    i++;
                }
            }
            char c = current.c;
            int f =current.data;
            characters.add(current.c);
            frequency.add(current.data);



        }
        printDecode(characters,frequency);

    }

    public static void printDecode(ArrayList characters, ArrayList frequencies){
        for (int i=0; i<characters.size(); i++){
            System.out.println(characters.get(i)+":"+frequencies.get(i));
        }
    }

    class MyComparator implements Comparator<QuessixANode> {
        public int compare(QuessixA.QuessixANode x, QuessixA.QuessixANode y)
        {
            //used to sort the character in the sequence of r
            return x.data - y.data;
        }}




    //driver method
    public static void main(String[] args) {
        char[] ch={'A','B','C','D','E'};
        int[] fre= {4,7,3,2,7};

        QuessixA h = new QuessixA();
        QuessixANode hn= h.encode(ch,fre);
        System.out.println(hn.data);
        String cha = "000100111011";
        h.decode(hn,cha);

    }
}


