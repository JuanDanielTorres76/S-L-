package model;

public class Controler {

    protected DoubleLinkedList listy; 

    protected Player[] players;

    public Controler(int cVal, int fVal ){

        listy = new DoubleLinkedList(cVal, fVal);

    }

    public String showPlayers(int counter){

        if(counter < players.length){

            if(counter == 0){

                System.out.println(showPlayers(counter+1)); 

                return counter+1 + "- " + players[counter].toString();

            }else if (counter == 1){

                System.out.println(showPlayers(counter+1)); 

                return counter+1 + "- " + players[counter].toString();

            }else{

                return counter+1 + "- " + players[counter].toString();

            }
    
        }else{

            return "Limit of players reached"; 
        }

    }

    public void initializeCollection(int numPlayers){

        players = new Player[numPlayers];

    }

    public void addPlayerCollection(int numPlayers, String name, int counter){
        
        if(counter<numPlayers){

            if(counter == 0){
                
                String token = "*";

                players[counter] = new Player(name, token);


            }else if(counter == 1){

                String token = "$";
    
                players[counter] = new Player(name, token);
    
    
            }else{

                String token = "O";
    
                players[counter] = new Player(name, token);
    
            }

            
        }

    }

    public void addNodes(){

        int rows = listy.getfVal(), columns = listy.getcVal();

        int totalNodes = rows*columns, counter = 1;

        listy.createNodes(totalNodes, counter);       

    }

    public String showBoardByRows(){

        int value = listy.getfVal()*listy.getcVal();

        String board = listy.invokeBoard(value, listy.getcVal());

        return board;

    }

}
