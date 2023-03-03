import java.util.Scanner;

import model.*;

public class Main {
    
    public static Scanner read = new Scanner(System.in);

    private static Main gameApp;

    private Controler gameControler;

    public static void main(String[] args) {
        
        addControler();

        initializeGame();
    }

    public Main(){

        gameControler = new Controler();

    }

    public static void addControler(){

        gameApp = new Main();

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

            gameApp.menu(start);

        }

    }

    public void createGameBoard(){

        System.out.println("Write the rows that the board will have \n Remember the maximum value is 10 ");  
        
        int rows = read.nextInt();

        System.out.println("Now the columns");

        int columns = read.nextInt();

        gameControler.addNodes(rows, columns);

    }

    public void showBoard(){

        String board = gameControler.showBoard();

        System.out.println(board);

    }

    public void menu(boolean start){

        gameApp.createGameBoard();

        gameApp.addPlayers();

        System.out.println(gameControler.showPlayers(0)); 

        boolean inGame = start;

        if(inGame == false){

            System.out.println("The game has ended");

        }else{

            while(inGame){

                System.out.println("The game has started");

                System.out.println("Choose one of the next options ");

                System.out.println(" - 1 Show the board");

                System.out.println(" - 2 ");

                int option = read.nextInt();

                switch(option){

                    case 1: gameApp.showBoard();

                        break;
                    
                    case 2:

                        break;

                    case 3: 

                        break;
                    
                    default:

                        break;

                }

            }

        }
        
    }

    public void addPlayers(){

        System.out.println("Only three players can play the game simultaneously");

        System.out.println("Choose the amount of players that are going to play");

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

    }

    public void initializeCollection(int numPlayers, int counter){

        if(counter<numPlayers){

            System.out.println("Type the name of the player");
            
            String name = read.nextLine(); 

            gameControler.addPlayerCollection(numPlayers, name, counter);

            initializeCollection(numPlayers, counter+1);

        } else{

            System.out.println("The players have been created sucesfully");
        }

    }

    public static Main getGameApp() {

        return gameApp;

    }

    public Controler getGameControler() {

        return gameControler;

    }

}
