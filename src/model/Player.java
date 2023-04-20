package model;

public class Player {
    
    private String name;

    private String token;

    private Score score;

    public Player(String name, String token){

        this.name = name;

        this.token = token;

        score = new Score(600);

    }

    public String toString(){

        return "The player " + getName() + " is identified with the token " + getToken();

    }

    public void calculateValueScore(){

        this.score.calculateTime();

    }

    public String getToken() {

        return token;

    }

    public void setToken(String Token) {

        Token = token;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;
        
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

}
