package model;

import java.lang.Math;

public class DoubleLinkedList {

    private Node tail; 

    private Node head;

    private int cVal;

    private int fVal;

    private Snake snakeList[];

    private Ladder ladderList[];

    public DoubleLinkedList(int cVal, int fVal){

        tail = null;

        head = null;

        this.fVal = fVal;

        this.cVal = cVal;

    }

    public void addNodeAtTail(Node node){

        if(head == null){

            tail = node;

            head = node;

        }else{

            tail.setNext(node);

            node.setPrevious(tail);

            tail = node;

            node.setNext(head);

        }

    }

    public void createNodes(int totalNodes, int counter, int numPlayers){
        
        Node node = new Node(counter, numPlayers);

        if(counter <= totalNodes){

            addNodeAtTail(node);

            createNodes(totalNodes, counter+1, numPlayers);

        }

        fillBooleanArray(head, 1);

    }

    public String prinSearchedNode(int value, Node node){

        String extention = "";

        if(node.getValue() == value){

            extention = " [ " + node.getValue() + " " + node.ShowPlayers(0) + " ]";


        }else{

            extention = prinSearchedNode(value, node.getNext());

        }

        return extention;

    }

    public String invokeBoard(int value, int cValue){

        String board = showNdBoardByRows(value, tail, cValue, true);

        return board;

    }

    public String invokeComodinBoard(int value, int cValue){

        String board = printComodins(value, cValue, tail, true);

        return board;

    }

    public String printComodins(int value, int cVal, Node pointer, boolean st){

        String board = "";

        if(pointer != null){

            if(pointer.getValue() == value && st == true){
                
                if(pointer.getValue() == tail.getValue()){

                    board +=  " [ - ] ";

                    board += printComodins(value-1, cVal, pointer.getPrevious(), st);

                } else if(value == head.getValue()){

                    board += " [ - ] ";

                }else if(value%cVal == 0 && value != tail.getValue()){

                    st = false; 

                    board += " \n";

                    board += printComodinOfSearchedNode(head, value);

                    board += printComodins(value-1, cVal, pointer.getPrevious(), st);

                }else{

                    board += printComodinOfSearchedNode(head, value); 

                    board += printComodins(value-1, cVal, pointer.getPrevious(), st);

                }

            }else if(pointer.getValue() == value && st == false){

                if(value == head.getValue()){

                    int value3 = getValueForBk(value, cVal, cVal, st);

                    board += printComodinOfSearchedNode(head, value3);

                }else if(value != tail.getValue() && value%cVal != 0){

                    int value3 = getValueForBk(value, cVal, value, st);

                    if(value3>0){
                    
                        board += printComodinOfSearchedNode(head, value3);

                    }

                    board += printComodins(value-1, cVal, pointer.getPrevious(), st);

                }else if(value != tail.getValue() && value%cVal == 0){

                    st = true;

                    board += " \n";

                    int value3 = getValueForBk(value, cVal, value, st);

                    if(value3>0){
                    
                        board += printComodinOfSearchedNode(head, value3);

                    }

                    board += printComodins(value-1, cVal, pointer.getPrevious(), st);

                }

            }

        }

        return board;

    }

    public String printComodinOfSearchedNode(Node pointer, int value ){

        String extention = "";

        if(pointer.getValue() != value){

            extention = printComodinOfSearchedNode(pointer.getNext(), value);

        }else{

            if(pointer.getComodin() == null){

                extention = " [ - ] ";
    
            }else{

                if(pointer.getComodin() instanceof Snake){

                    Snake obj = (Snake)pointer.getComodin();

                    extention = " [ " + obj.getIdentifier() + " ] ";

                }else{

                    Ladder obj = (Ladder)pointer.getComodin();

                    extention = " [ " + obj.getIdentifier() + " ] ";

                }

            }

        }

        return extention;

    }

    public String showNdBoardByRows(int value, Node node, int cVal, boolean st){

        String board = "";

        if(node != null){

            if(node.getValue() == value && st == true){

                if(value == tail.getValue()){

                    board += " [ " + node.getValue() + " "+  node.ShowPlayers(0) + " ]";

                    board += showNdBoardByRows(value-1, node.getPrevious(), cVal, st);

                }else if(value == head.getValue()){

                    board += " [ " + node.getValue() + " " + node.ShowPlayers(0) + " ]";

                }else if(value%cVal == 0 && value != tail.getValue()){

                    st = false;

                    board += " \n";

                    int value3 = getValueForBk(value, cVal, value, st);

                    if(value3 >0){

                        board += prinSearchedNode(value3, head);

                    }

                    board += showNdBoardByRows(value-1, node.getPrevious(), cVal, st); 

                }else{

                    board += " [ " + node.getValue() + " " + node.ShowPlayers(0) +  " ]";

                    board += showNdBoardByRows(value-1, node.getPrevious(), cVal, st); 

                }

            }else if(node.getValue() == value && st == false){

                if(value == head.getValue()){

                    int value3 = getValueForBk(value, cVal, cVal, st);

                    board += prinSearchedNode(value3, node);

                }else if(value != tail.getValue() && value%cVal != 0){

                    int value3 = getValueForBk(value, cVal, value, st);

                    if(value3>0){
                    
                        board += prinSearchedNode(value3, head);

                    }

                    board += showNdBoardByRows(value-1, node.getPrevious(), cVal, st);

                } else if(value != tail.getValue() && value%cVal == 0){

                    st = true;

                    int value3 = getValueForBk(value, cVal, value, st);

                    if(value3>0){
                    
                        board += prinSearchedNode(value3, head);

                    }

                    board += " \n";

                    board += " [ " + node.getValue() + " " + node.ShowPlayers(0) + " ]";

                    board += showNdBoardByRows(value-1, node.getPrevious(), cVal, st) ;

                }

            }

        }

        return board;

    }

    public int getValueForBk(int value, int cVal, int limitMax, boolean st){

        int ans = 0;

        if(value > cVal){

            double ceiling = (double)value/cVal;

            limitMax = (int)(cVal * Math.ceil(ceiling));

            int limitMin = limitMax - cVal;

            int value3 = limitMax - value;

            if(value == limitMax){

                if(st == true){

                   ans = value + cVal; 

                }else{

                    ans =  -1;
                    
                }

            } else if(value3 == 0){
    
                ans = (int)(cVal * Math.ceil(ceiling));

            }else if(value3 == limitMin){

                ans = value3 + cVal;

            }else{
    
                ans = limitMin + value3;

            }

        }else if(value == cVal){

            if(st == false){

                ans = (int)value/cVal;

            }else{

                double ceiling = (double)value/cVal+1;

                ans = (int)(cVal * ceiling); 

            }

        }else{

            if(st == false){

                limitMax = cVal;

                int limitMin = limitMax - cVal+1;

                if(value == head.getValue()){

                    ans = cVal; 

                }else{

                    int value3 = limitMax - value;

                    ans = limitMin + value3;

                }

            }else{

                ans = value;

            }

        }

        return ans;

    }

    public void createSnakeArray(int numSnakes){

        snakeList = new Snake[numSnakes];

    }

    public void createLadderArray(int numLadders){

        ladderList = new Ladder[numLadders];
    }

    public void assignNodeBeggining(int value, Node pointer, Comodin cn){

        if(pointer.getValue() != value){

            assignNodeBeggining(value, pointer.getNext(), cn);

        }else{

            cn.setBeggining(pointer);

        }

    }

    public void assignNodeEnd(int value, Node pointer, Comodin cn ){

        if(pointer.getValue() != value){

            assignNodeEnd(value, pointer.getNext(), cn);

        }else{

            cn.setEnd(pointer);

        }

    }

    public String assignID(int numSnake){

        numSnake++;

        String ch;

        switch(numSnake){

            case 1:

                ch = "A";

                break;

            case 2:

                ch = "B";

                break;

            case 3:

                ch = "C";

                break;

            case 4:

                ch = "D";

                break;

            case 5:

                ch = "E";

                break;
            
            default:

                ch = "Z";

                break;
        }

        return ch;

    }

    public void changeNodeStatus(Node pointer, int value, Comodin cm){

        if(pointer.getValue() != value){
        
            changeNodeStatus(pointer.getNext(), value, cm);

        }else{

            pointer.setComodin(cm);

        }

    }

    public void instanceLadders(int beggining, int end, int ladderCounter){

        ladderList[ladderCounter] = new Ladder(ladderCounter+1);

        assignNodeBeggining(beggining, head, ladderList[ladderCounter]);

        assignNodeEnd(end, head, ladderList[ladderCounter]);

        changeNodeStatus(head, beggining, ladderList[ladderCounter]);

        changeNodeStatus(head, end, ladderList[ladderCounter]);

    }

    public void instanceSnakes(int headvalue, int tailValue, int snakeCounter){

        String identifier = assignID(snakeCounter);

        snakeList[snakeCounter] = new Snake(identifier);

        assignNodeBeggining(tailValue, head, snakeList[snakeCounter]);

        assignNodeEnd(headvalue, head, snakeList[snakeCounter]);

        changeNodeStatus(head, headvalue, snakeList[snakeCounter]);

        changeNodeStatus(head, tailValue, snakeList[snakeCounter]);

    }

    public boolean validationOfComodin(int value){

        boolean validation = false;

        Comodin nodeComodin = getComodinFromNode(head, value);

        if(nodeComodin == null){

            validation = true;

        }

        return validation;

    }

    public Comodin getComodinFromNode(Node node, int value){

        Comodin ans = null;

        if(value != node.getValue()){

            getComodinFromNode(node.getNext(), value);

        }else {

            ans = node.getComodin();

        }

        return ans;

    }

    public Boolean validateEnd(boolean inGame, int numPlayers){

        boolean[] arr = new boolean[numPlayers];

        for(int i = 0; i <arr.length; i++){

            arr[i] = validateDiceAboveBoard(head, i, 0);

        }

        boolean validation = true;

        for(boolean a: arr){

            if(!a){

                validation = false;

                break;

            }

        }

        inGame = tail.validateEnd(inGame, 0);

        if(!inGame && validation){

            return true;

        }else{

            return false;

        }

    }

    public void fillBooleanArray(Node pointer, int counter){

        if(counter<=tail.getValue()){

            if(pointer.getValue() == head.getValue()){

                boolean value = true;

                pointer.initializePositions(value, counter);

                fillBooleanArray(pointer.getNext(), counter+1);
                
            }else{

                boolean value = false;

                pointer.initializePositions(value, counter);

                fillBooleanArray(pointer.getNext(), counter+1);

            }

        }

    }

    public int obtainPlayerPosition(int PlayerCounter, Node pointer, int counter){

        if(counter <= fVal*cVal){

            if(pointer.ObtainPlayerPosition(PlayerCounter) == true){
    
                return pointer.getValue();     
    
            }else{
    
                return obtainPlayerPosition(PlayerCounter, pointer.getNext(), counter+1);
    
            }

        } else{

            return 0;

        }

    }

        
    public void changePositionValue(Node pointer, int value, int playerCounter){

        if(pointer.getValue() != tail.getValue()){

            if(pointer.getValue() == value){

                pointer.changePositionValue(playerCounter);
    
            }else{
    
                changePositionValue(pointer.getNext(), value, playerCounter);
    
            }

        }

    }

    public int fallenIntoComodin(Node pointer, int position, int counter){

        if(counter <= fVal*cVal){

            if(pointer.getValue() == position){

                if(pointer.getComodin() == null){

                    return position;

                }else{

                    if(pointer.getComodin() instanceof Snake){

                        System.out.println("Oh no! you´ve fallen into a Snake ");

                        Snake obj = (Snake)pointer.getComodin();

                        if(position == obj.getBeggining().getValue()){

                            return obj.getEnd().getValue();

                        }else{

                            return position;

                        }

                    }else{

                        Ladder obj = (Ladder)pointer.getComodin();

                        System.out.println(" You´ve reached a Ladder");

                        if(position == obj.getBeggining().getValue()){

                            return obj.getEnd().getValue();

                        }else{

                            return position;

                        }

                    }

                }

            }else{

                return fallenIntoComodin(pointer.getNext(), position, counter+1);

            }

        }else{

            return position;

        }

    }

    public boolean validateDiceAboveBoard(Node pointer, int numPlayer, int counter){

        if(pointer.getValue() < tail.getValue()){

            if(pointer.getPos(numPlayer)){

                return true;

            }else{

                return validateDiceAboveBoard(pointer.getNext(), numPlayer, counter+1);

            }   

        }else if(pointer.getValue() == tail.getValue()){

            return false;

        }else{

            return false;

        }

    }

    public void movePlayer(int playerCounter, int diceValue){

        int currentPosition = obtainPlayerPosition(playerCounter, head, 0);

        int newPosition = currentPosition + diceValue;

        newPosition = fallenIntoComodin(head, newPosition, 0);

        changePositionValue(head, currentPosition, playerCounter);

        changePositionValue(head, newPosition, playerCounter);


    }

    public Node getTail() {

        return tail;

    }

    public void setTail(Node tail) {

        this.tail = tail;

    }

    public Node getHead() {

        return head;

    }

    public void setHead(Node head) {

        this.head = head;

    }

    public int getcVal() {
        return cVal;
    }

    public void setcVal(int cVal) {
        this.cVal = cVal;
    }

    public int getfVal() {
        return fVal;
    }

    public void setfVal(int fVal) {
        this.fVal = fVal;
    }
    
}
