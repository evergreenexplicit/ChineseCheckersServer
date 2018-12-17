import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ChineseCheckersClassicBuilder {
    int playersNumber;
    public void create(ChineseCheckers game,int port) throws IOException {
        ServerSocket listener = new ServerSocket(port);
        ArrayList<Player> playersAtStart = new ArrayList<Player>();
        System.out.println("ChineseCheckersServer is running");
        playersAtStart.add(new PlayerClassic(listener.accept(),0,game));
        setRules(playersAtStart.get(0));
        try {
            for(int i = 1;i <playersNumber;i++){
                playersAtStart.add(new PlayerClassic(listener.accept(),i,game));
            }
            game.setBoard();
            for(int i = 0;i <playersNumber;i++){
               game.addPlayers(playersAtStart);
            }


        } finally {
            listener.close();
        }



    }
    public void setRules(Player player) {
        try {

            String request[] = player.input.readLine().split(" ");
            if(request[0].equals("RULES"))
            playersNumber =  Integer.parseInt(request[1]);

        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }
}
/*
public class Server {
public static void main(String[] args) throws Exception {
    ServerSocket listener = new ServerSocket(8901);
    System.out.println("ChineseCheckersServer is running");
    try {
        while (true) { // czy zaczynac, liczba graczy, plansza pozniej
            ChineseCheckers game = new ChineseCheckersClassic();
            game.addPlayer(listener.accept());
            String playersNum = game.getPlayer(0).input.readLine();
            game.getPlayer(0).input.readLine();
        }


    } finally {
        listener.close();
    }
}


}
List of commands:
OUT:
    INVALID_MOVE - zly ruch //cofanie do poprzedniego polozenia
    OPPONENT_MOVED oldX oldY newX newY //zweryfikowany ruch, tylko pozmieniac pola
    TURN playeridx
    YOUR_TURN //bez argumentow

    VICTORY
    DEFEAT

pierwszy klik = handler w watku , zmienne zapisujace co przyszlo, sprawdzanie dostepnych i zwracanie
drugi klik - odpalanie move z pierwszymi arg. (jesli na to samo pole to odwolanie tego?)
IN:
    RULES
    MOVE_REQ
    POSSIBLE_MOVES_REQ
    END_TURN_REQ
 */