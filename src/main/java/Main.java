import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    private static int playersNumber;

    public static void main(String[] args) throws Exception {




        ServerSocket listener = new ServerSocket(8901);
        System.out.println("ChineseCheckersServer is running");
        Player admin = new Player(listener.accept(),0);

        try {
            admin.send("RULES_REQ");
            String request;

            request = admin.input.readLine();
            String split[] = request.split(" ");
            if(split[0].equals("CLASSIC")){
                playersNumber =  Integer.parseInt(split[1]);
                ChineseCheckersBuilder builder = new ChineseCheckersBuilder();
                builder.create(new MovesClassic(),new BoardClassic(4,playersNumber),new WinConditionsClassic(4,playersNumber),split[0],playersNumber,listener,admin);
            }

        } catch (IOException e) {
            System.out.println("Player died: " + e);
        } finally{
            listener.close();
        }
    }

}