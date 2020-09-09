import java.util.ArrayList;
public class Vertex
{
    int dollars;
    char name; //changed it from private for now but might change it back
    ArrayList<Vertex> connectedTo = new ArrayList<Vertex>();

    Vertex(int dollars, char name) {
        this.dollars = dollars;
        this.name = name;
    }

    public void setConnectedTo(Vertex connectMe)
    {
        connectedTo.add(connectMe);
    }

    public boolean isConnected(Vertex otherVertex)
    {
        return connectedTo.contains(otherVertex);
    }
    
    public void giveDollars(){
        this.dollars--;
    }
    
    //Will add a dollar when another vertex gives
    public void addDollars(){
        this.dollars++;
    }
    
    public char getName(){
        return this.name;
    }

    public int getMoney(){
        return this.dollars;
    }
}