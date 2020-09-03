import java.util.Scanner;

public class Driver
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Short Description of Game");

        System.out.println("How many Vertices?(Between 2-7 inclusive)");
        int numberOfVertices = Integer.parseInt(input.nextLine());

        System.out.println("How many Edges?(Between 2-7 inclusive)");
        int numberOfEdges = Integer.parseInt(input.nextLine());

        Vertex[] vertices = new Vertex[numberOfVertices];
    }
}
