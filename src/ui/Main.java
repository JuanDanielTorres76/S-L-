import java.util.Scanner;

import model.*;

public class Main {
    
    public static Scanner read = new Scanner(System.in);

    private static Main gameApp;

    private Controler gameControler;

    public Main(){

        gameControler = new Controler();

    }

    public static void addControler(){

        gameApp = new Main();

    }

    public static void initializeGame(){
        System.out.println("Welcome to our game 'Snakes and Ladders' \n please chose one option");
        System.out.println(" - 1 Play");
        System.out.println(" - 2 End Game");
        read


    }

    public void menu(){
        boolean inGame = true;
        if(inGame == false){
            System.out.println("The game has ended");
        }else{
            while(inGame){

            }
        }
        


    }

    
    public static Main getGameApp() {
        return gameApp;
    }

    public Controler getGameControler() {
        return gameControler;
    }

}
