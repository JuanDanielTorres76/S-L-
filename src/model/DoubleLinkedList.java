package model;

import java.lang.Math;

public class DoubleLinkedList {

    private Node tail; 

    private Node head;

    private int cVal;

    private int fVal;

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

    public void createNodes(int totalNodes, int counter){
        
        Node node = new Node(counter);

        if(counter <= totalNodes){

            addNodeAtTail(node);

            createNodes(totalNodes, counter+1);

        }

    }

    public String showNodes(int value, Node node){

        String nodes = " "; 

        if(node != null){

            if(node.getValue() == value){

                nodes += " [ " + node.getValue() + " ] ";

                nodes += showNodes(value+1, node.getNext());

            }

        }

        return nodes;

    }

    public String prinSearchedNode(int value, Node node){

        String extention = "";

        if(node.getValue() == value){

            extention = " [ " + node.getValue() + " ]";


        }else{

            extention = prinSearchedNode(value, node.getNext());

        }

        return extention;

    }

    public String invokeBoard(int value, int cValue){

        String board = showNdBoardByRows(value, tail, cValue, true);

        return board;

    }

    public String showNdBoardByRows(int value, Node node, int cVal, boolean st){

        String board = "";

        if(node != null){

            if(node.getValue() == value && st == true){

                if(value == tail.getValue()){

                    board += " [ " + node.getValue() + " ]";

                    board += showNdBoardByRows(value-1, node.getPrevious(), cVal, st);

                }else if(value == head.getValue()){

                    board += " [ " + node.getValue() + " ]";

                }else if(value%cVal == 0 && value != tail.getValue()){

                    st = false;

                    board += " \n";

                    int value3 = getValueForBk(value, cVal, value, st);

                    if(value3 >0){

                        board += prinSearchedNode(value3, head);

                    }

                    board += showNdBoardByRows(value-1, node.getPrevious(), cVal, st); 

                }else{

                    board += " [ " + node.getValue() + " ]";

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

                    board += " [ " + node.getValue() + " ]";

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
