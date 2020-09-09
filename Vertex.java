import java.util.ArrayList;

//Class for Vertex object which will manage the cash/name/edges of each vertex
public class Vertex
{
    private int dollars; //amount of money each vertex has
    private char name; //name of the vertex
    ArrayList<Vertex> connectedTo = new ArrayList<Vertex>(); //list of all vertices that the vertex is connected to

    //constructor which sets the amount of money and name of the vertex object
    Vertex(int dollars, char name) {
        this.dollars = dollars;
        this.name = name;
    }

    //adds a vertex to the list of connected vertices (edges)
    public void setConnectedTo(Vertex connectMe)
    {
        connectedTo.add(connectMe);
    }

    //check to see if the vertex is connected to another
    public boolean isConnected(Vertex otherVertex)
    {
        return connectedTo.contains(otherVertex);
    }

    //adds a dollar to every vertex connected to the current one and subtracts a dollar each time a dollar is given
    public void giveDollars(){
        for(int i = 0; i < connectedTo.size(); i++){
            connectedTo.get(i).addDollars();
            this.dollars--;
        }
    }

    //takes a dollar from every vertex connected and adds it to the current vertex
    public void takeDollars(){
        for(int i = 0; i < connectedTo.size(); i++){
            connectedTo.get(i).subDollars();
            this.dollars++;
        }
    }

    //subtracts a dollar from the dollar amount
    public void subDollars(){
        this.dollars--;
    }

    //Will add a dollar when another vertex gives
    public void addDollars(){
        this.dollars++;
    }

    //returns the char letter associated with the vertex
    public char getName(){
        return this.name;
    }

    //returns the total amount of money the vertex has
    public int getMoney(){
        return this.dollars;
    }
}