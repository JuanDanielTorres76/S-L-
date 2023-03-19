package model;

public abstract class Comodin{
    
    private Node beggining;

    private Node end; 

    public Comodin(){

        end = null;

        beggining = null;

    }

    public Node getBeggining() {
        return beggining;
    }

    public void setBeggining(Node beggining) {
        this.beggining = beggining;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

}
