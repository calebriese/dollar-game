public class Vertex
{
    int dollars;
    Vertex[] connectedTo = new Vertex[6]; //can be connected to multiple vertices at most 6, Idk if this has to be a pointer or not?
    //maybe use ArrayList so we can append

    Vertex() { }
    Vertex(int dollars) {
        this.dollars = dollars;
    }

    public void setConnectedTo(Vertex connectMe) //
    {
        connectedTo[0] = connectMe; // I just put this here as a placeholder
        //maybe we could increment a counter everytime we add a new connection, or use ArrayList
    }
}
