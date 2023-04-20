package model;

import java.lang.Math;

public class Controler {

    protected Score sc;

    protected DoubleLinkedList listy; 

    protected Player[] players;

    protected Stree scoreTree;

    public Controler(int cVal, int fVal ){

        listy = new DoubleLinkedList(cVal, fVal);

        Score topTime = new Score(600);

        topTime.startTimer();

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

    public String showPly(int ply){

        String ans = "";

        if(ply<players.length){

            ans = players[ply].getToken(); 

        }

        return ans;

    }

    public int verifyTurns(int ply){

        if(ply< players.length){

            return ply;

        }else{

            ply = 0;

            return ply;

        }

    }

    public String movePlayer(int PlayerCounter){

        int diceValue = (int)(Math.random()*6+1);

        String msg = "The value of the dice is: " + diceValue;

        listy.movePlayer(PlayerCounter, diceValue);

        return msg;

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

    public String showScore(){

        String msg  = showScore(0);

        return msg;

    }

    private String showScore(int counter){

        if(counter < players.length){

            players[counter].calculateValueScore();

            scoreTree.add(players[counter]);

            showScore(counter+1);

        }
        
        return scoreTree.inOrder(scoreTree.getRoot());


    }

    public boolean validateInGame(boolean inGame, int playerCounter){

        inGame = listy.validateEnd(inGame, playerCounter);

        return inGame;

    }

    public void addNodes(int numPlayers){

        int rows = listy.getfVal(), columns = listy.getcVal();

        int totalNodes = rows*columns, counter = 1;

        listy.createNodes(totalNodes, counter, numPlayers);  

    }

    public String showBoardByRows(){

        int value = listy.getfVal()*listy.getcVal();

        String board = listy.invokeBoard(value, listy.getcVal());

        return board;

    }

    public int specifyComodins(int numComodins){

        if(numComodins >= listy.getcVal() && numComodins >= listy.getfVal()){

            numComodins = 1;  //El numero de snakes es mayor que el de las columnas del board

        }else if((listy.getfVal()*listy.getcVal())/numComodins < 3){

            numComodins = 1;  //El numero de nodos es muy corto para crear tantas snakes

        } else{

            return numComodins;

        }

        return numComodins;

    }

    public String showComodins(){

        int value = listy.getcVal()*listy.getfVal();

        String msg  = listy.invokeComodinBoard(value, listy.getcVal());

        return msg;

    }

    public String createComodins(int numSnakes, int numLadders){

        boolean st = true;

        int snakeCounter = 0;

        int ladderCounter = 0;
        
        String msg = "The Comodins have been sucesfully created";

        listy.createSnakeArray(numSnakes);

        listy.createLadderArray(numLadders);

        addBoardComodins(st, numSnakes, snakeCounter, numLadders, ladderCounter);

        st = false; 

        addBoardComodins(st, numSnakes, snakeCounter, numLadders, ladderCounter);

        return msg;

    }

    public void addBoardComodins(boolean st, int numSnakes, int snakeCounter, int numLadders, int ladderCounter){

        if(st == true){     //Estamos creando Snakes

            int tailValue = (int)(Math.random()*(listy.getcVal()*listy.getfVal()) +1 );

            int headValue = (int)(Math.random()*(listy.getcVal()*listy.getfVal()) +1 );

            if(tailValue == listy.getTail().getValue() || tailValue == listy.getHead().getValue() || headValue == listy.getTail().getValue() || headValue == listy.getHead().getValue()){

                addBoardComodins(st, numSnakes, snakeCounter, numLadders, ladderCounter);

            } else if(tailValue> headValue || headValue-tailValue < listy.getcVal()){

                addBoardComodins(st, numSnakes, snakeCounter, numLadders, ladderCounter);     //El valor de la cola es mayor que el valor de la cabeza  o la distancia es menor a la la distancia de las columnas
                                                                
            }else{

                boolean validation = listy.validationOfComodin(tailValue); //Validacion de que no existe una Snake ya ubicada en este Nodo

                boolean validation1 = listy.validationOfComodin(headValue); // Validacion de que no existe un comodin que ya este ubicado en este Nodo
    
                if(validation == false && validation1 == false && tailValue<=listy.getcVal()){   // El primer valor random debe ser mayor a la primera fila del tablero
    
                    addBoardComodins(st, numSnakes, snakeCounter, numLadders, ladderCounter);
    
                }else{      //Se  crean los objetos Snake
    
                    if(snakeCounter <numSnakes){    

                        listy.instanceSnakes(headValue, tailValue, snakeCounter);

                        addBoardComodins(st, numSnakes, snakeCounter+1, numLadders, ladderCounter);

                    }else{

                        System.out.println(" The Snakes were Created sucesfully");

                    }
    
                }

            }

        }else{

            int tailValue = (int)(Math.random()*(listy.getcVal()*listy.getfVal()) +1 );

            int headValue = (int)(Math.random()*(listy.getcVal()*listy.getfVal()) +1 );

            if(tailValue == listy.getTail().getValue() || tailValue == listy.getHead().getValue() || headValue == listy.getTail().getValue() || headValue == listy.getHead().getValue()){

                addBoardComodins(st, numSnakes, snakeCounter, numLadders, ladderCounter);

            }else if(tailValue > headValue || headValue-tailValue < listy.getcVal()){

                addBoardComodins(st, numSnakes, snakeCounter, numLadders, ladderCounter);

            }else{

                boolean validation = listy.validationOfComodin(tailValue);

                boolean validation1 = listy.validationOfComodin(headValue);

                int lastRow = (listy.getcVal()*listy.getfVal()) - listy.getcVal(); 

                if(validation == false && validation1 == false && headValue< lastRow){

                    addBoardComodins(st, numSnakes, snakeCounter, numLadders, ladderCounter);

                }else{      //Se crean los objetos ladder
    
                    if(ladderCounter < numLadders){

                        listy.instanceLadders(tailValue, headValue, ladderCounter);

                        addBoardComodins(st, numSnakes, snakeCounter, numLadders, ladderCounter+1);

                    }else{

                        System.out.println("the ladders were sucesfully created");

                    }

                }

            }

        }

    }

}
