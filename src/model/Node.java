package model;

public class Node {
    
    private Node next; 

    private Node previous;

    private int value;

    private Comodin comodin; 

    private boolean[] positions;

    public Node(int value, int numPlayers){

        this.value = value;

        next = null;

        previous = null;

        comodin = null;

        positions = new boolean[numPlayers];

    }

    public void initializePositions(int numPly, boolean st){

        if(st = true){

            positions[numPly] = true;

        }else{

            positions[numPly] = false;

        }

    }

    public boolean ObtainPlayerPosition(int numPlayer){

        boolean validation = false;

        if(positions[numPlayer] == true){

            validation = true;

        }

        return validation;

    }

    public void changePositionValue(int numPlayer){

        if(positions[numPlayer] == true){

            positions[numPlayer] = false;

        }else{

            positions[numPlayer] = true;

        }

    }

    public String ShowPlayers(int counter){

        String extention = "", token;

        if(counter == 0){

            token = "*";

        }else if(counter == 1){

            token = "$";

        }else if(counter == 2){

            token = "O";

        }else{

            token = "";

        }

        if(counter < positions.length){

            if(positions[counter] == true){

                extention += " " + token + " ";

            }else{
                
                extention = ShowPlayers(counter+1);

            }

        }

        return extention;

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

    
    public boolean[] getPositions() {

        return positions;

    }

    public void setPositions(boolean[] positions) {

        this.positions = positions;

    }
  
}
