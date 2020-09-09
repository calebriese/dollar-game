/* Title: Dollar Game
   Name of File: Main.java , Vertex.java
   External Files: n/a
   External Files Created by Program: n/a
   Programmers: Kerry Houlihan, Caleb Riese
   Programmers Emails: kehbcw@mail.umsl.edu , cerck6@mail.umsl.edu
   Class: CS 4500 Section 1
   Date Finished: 9/9/2020
   Date Sumbitted: 9/9/2020
   Explaination: This program simulates the dollar game. In this game, the user indicates the number
                 of vertices along with how these vertices are connected to each other. The vertices
                 are assigned a dollar amount which can be positive or negative. Each vertex can give
                 a dollar to all the vertices they are connected to, or they can take from all the
                 vertices they are connected with. The objective of the game is to have all the vertices
                 not have a negative dollar amount associated with it in as few moves as possible.
                 The user can play until they decide to quit. When they quit, all of the information
                 from the game will be outputted, and they will be told if they won or not.
   Resources Used: n/a
 */

import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in); //object used to get input from user

        System.out.println("Welcome to the dollar game! The objective of this game is to have all of your vertices equal to one or zero "
                + "essentially, you do not want debt. You will specify the number of vertices along with how they are connected. "
                + "You can play as long as you want when you're done we'll let you know if you have won or not. Good Luck!");
        System.out.println();


        int numberOfVertices = 0;
        //Keeps asking user for numberOfVertices until valid input is given
        while (numberOfVertices < 2 || numberOfVertices > 7)
        {
            System.out.print("How many Vertices? (Must be between 2-7 inclusive): ");
            try
            {
                numberOfVertices = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException ex)
            {
                System.out.println("Must be an integer");
            }
        }
        System.out.println();

        int numberOfEdges = 0;
        int maxEdges = (numberOfVertices * (numberOfVertices - 1)) / 2; //finds the maximum amount of edges allowed

        //Keeps asking user for numberOfEdges until valid input is given
        while (numberOfEdges < numberOfVertices - 1 || numberOfEdges > maxEdges)
        {
            System.out.print("How many Edges? (Must be between " + (numberOfVertices - 1) + "-" + maxEdges + "): ");
            try
            {
                numberOfEdges = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException ex)
            {
                System.out.println("Must be an integer");
            }
        }
        System.out.println();

        Vertex[] vertices = new Vertex[numberOfVertices]; //This holds all of the vertices that the user created

        /* This loop asks the user for starting amount for each vertex. Then, each vertex
           is initialized with the starting amount and the letter of the alphabet that will
           refer to each vertex
         */
        for (int count = 0; count < vertices.length; count++)
        {
            int startingDollars;
            while (true)
            {
                System.out.print("Enter the amount of starting dollars for vertex " + getLetter(count) + ": $");
                try
                {
                    startingDollars = Integer.parseInt(input.nextLine());
                    break;
                } catch (NumberFormatException ex)
                {
                    System.out.println("Must be an integer");
                }
            }
            vertices[count] = new Vertex(startingDollars, getLetter(count)); //initializes each vertex object in the array with the given money and name
        }
        System.out.println();


        //This loop creates all the edges
        for(int count = 0; count < numberOfEdges; count++){

            String edgeName;
            do{
                System.out.print("Enter edge to create (Must be two valid letters): ");
                edgeName = input.nextLine().toUpperCase();
            } while(!checkString(edgeName, vertices)); //checks to see if this is a valid connection

            //Assigns edge between both vertices
            vertices[getIndex(edgeName.charAt(0))].setConnectedTo(vertices[getIndex(edgeName.charAt(1))]);
            vertices[getIndex(edgeName.charAt(1))].setConnectedTo(vertices[getIndex(edgeName.charAt(0))]);
        }
        System.out.println();


        int moves = 0; //counter for moves
        char vertexChoice; //holds the choice of which vertex the user wants to work with
        char userChoice; //holds the choice of what type of move the user makes
        boolean won; //status of game

        do{

            System.out.println();
            won = true;
            //Displays dollar amounts for each vertex and sets won to false if any vertex is in debt
            for(int i = 0; i < vertices.length; i++){
                System.out.println(vertices[i].getName() + " current dollar amount: $" + vertices[i].getMoney());

                if(vertices[i].getMoney() < 0){
                    won = false;
                }
            }

            //Tells user when they won
            if(won){
                System.out.println("You have a winning configuration!");
                System.out.println();
            }

            //checks for valid user input of a vertex name or 'Q'
            do{
                System.out.print("Enter the character of the vertex to move, or Q if you wish to quit: ");
                vertexChoice = input.next().toUpperCase().charAt(0);
            }while( !(checkChar(vertexChoice, vertices)) && !(vertexChoice == 'Q') );

            //breaks loop if the user chose to quit
            if(vertexChoice == 'Q'){
                System.out.println("Ending now. Thanks for playing!");
                break;
            }

            //checks for valid input of the users move and the calls giveDollars() or takeDollars()
            do{
                System.out.print("\nEnter G to give a dollar to every vertices connected to vertex " + vertexChoice
                        + ",\nor enter T to take a dollar from every vertices connected to vertex " + vertexChoice + ": ");

                userChoice = input.next().toUpperCase().charAt(0);
                if(userChoice == 'G'){
                    vertices[getIndex(vertexChoice)].giveDollars();
                }else if(userChoice == 'T'){
                    vertices[getIndex(vertexChoice)].takeDollars();
                }else{
                    System.out.println("Error: input does not match T or G.");
                }
            }while(!(userChoice == 'G' || userChoice == 'T'));

            moves++;

        }while(vertexChoice != 'Q'); //Keeps playing unless q is entered


        //Displays dollar amount once game is over
        System.out.println("\nResults:");
        for(int i = 0; i < vertices.length; i++){
            System.out.println(vertices[i].getName() + " current dollar amount: $" + vertices[i].getMoney());
        }

        //Displays whether you won/lost and amount of moves
        if(won){
            System.out.print("\nCongrats, you won!\n");
        }else{
            System.out.print("\nSorry, you lost!\n");
        }
        System.out.print("You had " + moves + " moves.");
    }


    //checks the two vertex names to see if it is valid input and displays error messages
    public static boolean checkString(String edgeName, Vertex[] vertices){

        //checks if length of the edge given is more than 2 vertices
        if(edgeName.length() != 2 ){
            System.out.println("Error: invalid input. An edge can only consist of two vertices.");
            return false;
        }

        //checks that the vertex given is not an edge to itself
        if(edgeName.charAt(0) == edgeName.charAt(1)){
            System.out.println("Error: An edge cannot connect a vertex to itself");
            return false;
        }

        //checks if given edgeName contains only letters and is between A-G and contains two existing vertices
        //if given character has a greater index than what actually exists then it returns false
        if( (getIndex(edgeName.charAt(0)) >= vertices.length) || (getIndex(edgeName.charAt(1)) >= vertices.length) ){
            System.out.println("Error: One or more of those vertices don't exist, please enter two valid letters");
            return false;
        }

        //checks to see if the edge already exists
        if(vertices[getIndex(edgeName.charAt(0))].isConnected(vertices[getIndex(edgeName.charAt(1))])) // Only checks one because if they are connected both would show it
        {
            System.out.println("Error: Edge already exists.");
            return false;
        }

        return true;
    }

    //checks if given vertexChoice contains letter and is between A-G
    //if given character has a greater index than what actually exists then it returns false
    public static boolean checkChar(char vertexChoice, Vertex[] vertices){
        if(getIndex(vertexChoice) >= vertices.length && (vertexChoice != 'Q')){
            System.out.println("Error: That is not a valid vertex choice.");
            return false;
        }else{
            return true;
        }
    }

    //gets the letter associated with the given index
    public static char getLetter(int index)
    {
        char letter;
        switch (index) {
            case 0:
                letter = 'A';
                break;
            case 1:
                letter = 'B';
                break;
            case 2:
                letter = 'C';
                break;
            case 3:
                letter = 'D';
                break;
            case 4:
                letter = 'E';
                break;
            case 5:
                letter = 'F';
                break;
            case 6:
                letter = 'G';
                break;
            default:
                letter = 'Z'; //Z isnt used
                break;
        }
        return letter;
    }


    //gets the index associated with the given letter
    public static int getIndex(char letter)
    {
        int index;
        switch (letter) {
            case 'A':
                index = 0;
                break;
            case 'B':
                index = 1;
                break;
            case 'C':
                index = 2;
                break;
            case 'D':
                index = 3;
                break;
            case 'E':
                index = 4;
                break;
            case 'F':
                index = 5;
                break;
            case 'G':
                index = 6;
                break;
            default: //When an invalid character is given 7 is returned which is not a valid index
                index = 7;
                break;
        }
        return index;
    }
}