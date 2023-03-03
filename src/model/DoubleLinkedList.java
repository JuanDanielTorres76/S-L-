package model;

public class DoubleLinkedList {

    private Node tail; 

    private Node head;

    public DoubleLinkedList(){

        tail = null;

        head = null;

    }

    public void addNodeAtTail(Node node){

        if(head == null){

            tail = node;

            head = node;

        }else{

            tail.setNext(node);

            tail = node;

            node.setPrevious(tail);

            node.setNext(head);

        }

    }

    public void createNodes(int totalNodes, int counter){
        
        Node node = new Node(counter);

        if(counter <= totalNodes){

            addNodeAtTail(node);

            createNodes(totalNodes, counter+1);

        }

    }

    public String showNodes(int value, Node node){

        String nodes = " "; 

        if(node != null){

            if(node.getValue() == value){

                nodes += " [ " + node.getValue() + " ] ";

                nodes += showNodes(value+1, node.getNext());

            }

        }

        return nodes;

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
