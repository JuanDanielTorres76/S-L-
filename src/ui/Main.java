package ui;

import java.util.Scanner;

import model.*;

public class Main {
    
    public static Scanner read = new Scanner(System.in);

    private static Main gameApp;

    private Controler gameControler;

    public static void main(String[] args) {

        initializeGame();
    }

    public Main(int cVal, int fVal){

        gameControler = new Controler(cVal, fVal);

    }

    public static void addControler(){

        System.out.println("Type the columns of the board ");

        int cVal = read.nextInt();

        System.out.println("Now the rows ");

        int fVal = read.nextInt();

        gameApp = new Main(cVal, fVal);

    }

    public static void initializeGame(){

        boolean start = false;
        
        System.out.println("Welcome to our game 'Snakes and Ladders' \n please chose one option");
        
        System.out.println(" - 1 Play");
        
        System.out.println(" - 2 End Game");
        
        int option = read.nextInt();
        
        if(option!= 1){
            
            System.out.println("The game has ended");

        }else{
            
            start = true;

            addControler();

            gameApp.menu(start);

        }

    }

    public void createGameBoard(int numPlayers){

        gameControler.addNodes(numPlayers);

        showBoard();

    }

    public void showBoard(){

        System.out.println("This is the gameboard");

        String gameBoard = gameControler.showBoardByRows();

        System.out.println(gameBoard);

    }

    public void menu(boolean start){

        int numPlayers = gameApp.addPlayers();

        gameApp.createGameBoard(numPlayers);

        System.out.println(gameControler.showPlayers(0)); 

        gameApp.createComodins();

        boolean inGame = start;

        if(inGame == false){

            System.out.println("The game has ended");

        }else{

            System.out.println("The game has started");

            gameApp.startGame(numPlayers, start, numPlayers);

        }
        
    }

    public void startGame(int playerCounter, boolean start, int numPlayers){

        boolean inGame = start;

        playerCounter = gameControler.verifyTurns(playerCounter);

        String plyToken = gameControler.showPly(playerCounter);

        while(inGame){

            System.out.println(" It is the turn of the player " + plyToken);

            System.out.println("Choose one of the next options ");

            System.out.println(" - 1 Show the board");

            System.out.println(" - 2 Show comodin Board");

            System.out.println(" - 3 Throw the dice");

            int option = read.nextInt();

            switch(option){

                case 1: gameApp.showBoard();

                    break;
                    
                case 2: gameApp.showComodins();

                    break;

                case 3: gameApp.throwDice(playerCounter);

                    System.out.println("Your turn has ended");

                    inGame = gameControler.validateInGame(inGame, numPlayers);

                    startGame(playerCounter+1, inGame, numPlayers);

                    break;
                    
                default:

                    System.out.println("Choose a valid option");

                    break;

            }

        }

        gameApp.showFinalScore();

        System.out.println("VALUE OF START " + Boolean.valueOf(start));

        System.out.println("Do you want to restart the game?");

        System.out.println("- 1 Yes");

        System.out.println("- 2 No");

        int restart = read.nextInt();

        if(restart == 1){

            menu(start);

        }else{

            System.out.println(" The game has ended ");

        }
        
    }

    public void showFinalScore(){

        System.out.println("Here are the final scores of the players ");

        String msg = gameControler.showScore();

        System.out.println(msg);

    }

    public void throwDice(int playerCounter){

        System.out.println("Press enter to throw the dice");

        read.nextLine();

        read.nextLine();

        System.out.println(gameControler.movePlayer(playerCounter));

    }

    public int addPlayers(){

        System.out.println(" - Only three players can play the game simultaneously");

        System.out.println(" - Choose the amount of players that are going to play");

        int numPlayers = read.nextInt();

        read.nextLine();

        if(numPlayers>3 || numPlayers <0){

            System.out.println("Choose a valid option");

            addPlayers();

        }else{

            int counter = 0; 

            gameControler.initializeCollection(numPlayers);

            initializeCollection(numPlayers, counter);

        }

        return numPlayers;

    }

    public void initializeCollection(int numPlayers, int counter){

        if(counter<numPlayers){

            System.out.println(" Type the name of the player");
            
            String name = read.nextLine(); 

            gameControler.addPlayerCollection(numPlayers, name, counter);

            initializeCollection(numPlayers, counter+1);

        } else{

            System.out.println(" The players have been created sucesfully");
        }

    }

    public void createComodins(){

        int numSnakes = createSnakes();
        
        int numLadders = createLadders();

        gameControler.createComodins(numSnakes, numLadders);

    }

    public int createSnakes(){

        System.out.println(" - Type the number of Snakes that will be created");

        System.out.println(" - Remeber that them have to be proporcional to the size of the board ");

        System.out.println(" - The max amount of Ladders that can be created are 5 ");

        System.out.println(" - Type 6 if you want to read the specifications");

        int numSnakes = read.nextInt();

        if(numSnakes == 6){

            System.out.println(" - The snake created will only be 1 if: ");

            System.out.println(" 1 - The amount of the snakes is higher than the amount of columns ans Rows of the board ");

            System.out.println(" 2 - The space between Snakes of the board is lesser than 3 if the snakes are created");

            createSnakes();

        }else if(numSnakes > 5){

            System.out.println("Type a valid option");

            createSnakes();

        }else{

            numSnakes = gameControler.specifyComodins(numSnakes);

            System.out.println(" - The Snakes that are gonna be created according to the specifications are " + numSnakes);

        }
        return numSnakes;

    }

    public int createLadders(){

        System.out.println(" - Type the number of Ladders that will be created");

        System.out.println(" - Remeber that them have to be proporcional to the size of the board ");

        System.out.println(" - The max amount of Ladders that can be created are 5 ");

        System.out.println(" - Type 6 if you want to read the specifications");

        int numLadders = read.nextInt();

        if(numLadders == 6){

            System.out.println(" - The Ladders created will only be 1 if: ");

            System.out.println(" 1 - The amount of the Ladders is higher than the amount of columns ans Rows of the board ");

            System.out.println(" 2 - The space between Ladders of the board is lesser than 3 if the ladders are created");

            createLadders();

        }

        if(numLadders>5){

            System.out.println("Type a valid option");

            createLadders();

        }else{

            numLadders = gameControler.specifyComodins(numLadders);

            System.out.println("The ladders that are gonna be created according to the specifications are " + numLadders);

        }

        return numLadders;

    }

    public void showComodins(){

        System.out.println("Those are the commodins of the gameboard");
        
        String board = gameControler.showComodins();

        System.out.println(board);
    }

    public static Main getGameApp() {

        return gameApp;

    }

    public Controler getGameControler() {

        return gameControler;

    }

}
