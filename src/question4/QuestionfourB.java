package question4;

class QuestionfourB{
    int data;
    QuestionfourB next;
    QuestionfourB head=null;
    QuestionfourB(int data){
        this.data = data;
        this.next = null;
    }
    public void addNode(int data){
        QuestionfourB newNode = new QuestionfourB(data);
        if (head==null){
            head=newNode;
        } else{
            QuestionfourB current=head;
            while(current.next!=null){
                current=current.next;
            }
            current.next=newNode;
        }
    }
    public void sortList(){
        QuestionfourB first=head;
        QuestionfourB second=head.next;
        int step=0;
        while (second!=null){
            if(first.data>second.data){
                first.next=second.next;
                second.next=null;
                second=first.next;
                step++;
            } else{
                first=first.next;
                second=second.next;
            }
        }
        System.out.println("The number of steps is "+step);
    }
    public static void main(String[] args) {
        QuestionfourB newnode = new QuestionfourB(30);
        newnode.addNode(20);
        newnode.addNode(5);
        newnode.sortList();
    }
}

