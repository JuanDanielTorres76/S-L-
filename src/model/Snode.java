package model;

public class Snode {

    private Player player;

    private Snode left;

    private Snode right;

    public Snode(Player player){

        this.player = player;

        left = null;

        right = null;

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Snode getLeft() {
        return left;
    }

    public void setLeft(Snode left) {
        this.left = left;
    }

    public Snode getRight() {
        return right;
    }

    public void setRight(Snode right) {
        this.right = right;
    }
    
}
