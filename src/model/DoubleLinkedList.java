package model;

public class DoubleLinkedList {

    private Node tail; 

    private Node head;


    public DoubleLinkedList(){
        tail = null;
        head = null;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }


    
}
