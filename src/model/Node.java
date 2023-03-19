package model;

public class Node {
    
    private Node next; 

    private Node previous;

    private int value;

    private Comodin comodin; 

    public Node(int value){

        this.value = value;

        next = null;

        previous = null;

        comodin = null;

    }

    public Node getNext() {

        return next;

    }

    public void setNext(Node next) {

        this.next = next;

    }

    public Node getPrevious() {

        return previous;

    }

    public void setPrevious(Node previous) {

        this.previous = previous;

    }

    public int getValue() {

        return value;

    }

    public void setValue(int value) {

        this.value = value;

    }

    public Comodin getComodin() {
        return comodin;
    }

    public void setComodin(Comodin comodin) {
        this.comodin = comodin;
    }
  
}
