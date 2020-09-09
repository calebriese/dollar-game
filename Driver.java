/* Title: Dollar Game
   Name of File:
   External Files:
   External Files Created by Program: n/a
   Programmers: Kerry Houihan, Caleb Riese
   Programmers Emails: kehbcw@mail.umsl.edu cerck
   Class: CS 4500 Section 1
   Date Finished:
   Date Sumbitted:
   Explaination: This program simulates the dollar game. In this game, the user indicates the number
                 of vertices along with how these vertices are connected to each other. The vertices
                 are assigned a dollar amount which can be positive or negative. Each vertex can give
                 a dollar to all the vertices they are connected to, or they can take from all the
                 vertices they are connected with. The objective of the game is to have all the vertices
                 not have a negative dollar amount associated with it in as few moves as possible.
                 The user can play until they decide to quit. When they quit, all of the information
                 from the game will be outputted, and they will be told if they won or not.
   Resources Used:
 */

import java.util.*;

public class Driver
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the dollar game! The objective of this game is to have all of your vertices equal to one or zero "
                + "essentially, you do not want debt. You will specify the number of vertices along with how they are connected. "
                + "You can play as long as you want when you're done we'll let you know if you have won or not. Good Luck!");
        System.out.println();

        int numberOfVertices = 0;
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
        int maxEdges = (numberOfVertices * (numberOfVertices - 1)) / 2;
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

        Vertex[] vertices = new Vertex[numberOfVertices];

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
            vertices[count] = new Vertex(startingDollars, getLetter(count)); //Might not even need to add name/check later
        }
        System.out.println();


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
    }


    public static boolean checkString(String edgeName, Vertex[] vertices){

        //checks the length of the edge given
        if(edgeName.length() != 2 ){
            System.out.println("Error: invalid input. An edge can only consist of two vertices.");
            return false;
        }

        //checks to sure the edge given is not an edge to itself
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
                letter = 'Z'; //this shouldn't happen
                break;
        }
        return letter;
    }


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