package model;

public class Stree {

    private Snode root;

    public Stree(){

        root = null;

    }
    
    public void add(Player player){

        root = addRecursive(root, player);

    }

    private Snode addRecursive(Snode node, Player player){

        if(node == null){

            return new Snode(player);

        }else if(node.getPlayer().getScore().getScore()< player.getScore().getScore()){

            addRecursive(node.getLeft(), player);

        }else if(node.getPlayer().getScore().getScore()> player.getScore().getScore()){

            addRecursive(node.getRight(), player);

        }

        return node;

    }

    public String inOrder(Snode pointer){

        String msg = "";

        if(pointer != null){

            msg += inOrder(pointer.getLeft());
            
            msg += pointer.getPlayer().toString() + " has " + pointer.getPlayer().getScore().getScore() + "Points  \n";

            msg += inOrder(pointer.getRight());

            return msg;

        }else{
  
            return "";

        }

    }

    public Snode getRoot() {
        return root;
    }

    public void setRoot(Snode root) {
        this.root = root;
    }



}
