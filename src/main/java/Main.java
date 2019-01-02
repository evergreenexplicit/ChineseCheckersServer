import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int playersNumber;

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
                ChineseCheckersClassicBuilder builder = new ChineseCheckersClassicBuilder();
                builder.create(new ChineseCheckersClassic(),playersNumber,listener,admin);
            }

        } catch (IOException e) {
            System.out.println("Player died: " + e);
        } finally{
            listener.close();
        }
    }

}
