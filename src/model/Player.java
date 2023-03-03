package model;

public class Player {
    
    private String name;

    private String token;

    public Player(String name, String token){

        this.name = name;

        this.token = token;

    }

    public String toString(){

        return "The player " + getName() + " is identified with the token " + getToken();

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



}
