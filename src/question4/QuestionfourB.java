package question4;

class QuestionfourB{

        static class ListNode {
            int val;

            ListNode next;

            ListNode(int x) {
                val = x;
            }
        }
//returns the number of steps (swaps) required to sort the list.
        public int sortList(ListNode head) {
            if (head == null || head.next == null)
                return 0;

            int count = 0;
            ListNode current = head;
            while (current.next != null) {
                if (current.val > current.next.val) {
                    current.next = current.next.next;
                    count++;
                } else {
                    current = current.next;
                }
            }
            return count;
        }
//main method creates a linked list and passes it to the sortList
        public static void main(String[] args) {
            QuestionfourB obj = new QuestionfourB();
            ListNode head = new ListNode(5);
            head.next = new ListNode(2);
            head.next.next = new ListNode(1);
            head.next.next.next = new ListNode(0);
            head.next.next.next.next = new ListNode(8);
            head.next.next.next.next.next = new ListNode(3);
            head.next.next.next.next.next.next = new ListNode(8);
            head.next.next.next.next.next.next.next = new ListNode(7);

            System.out.println("Number of steps required to sort the linked list: " + obj.sortList(head));
        }
    }