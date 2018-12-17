import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ChineseCheckersClassicBuilder {

    public void create(ChineseCheckers game, int playersNumber,ServerSocket listener,Player admin) throws IOException {
        ArrayList<Player> playersAtStart = new ArrayList<>();
        playersAtStart.add(admin);
        admin.setGame(game);
        try {
            for (int i = 1; i < playersNumber; i++) {
                playersAtStart.add(new Player(listener.accept(), i));
                playersAtStart.get(i).setGame(game);
                for(int j = 0; j< playersAtStart.size();j++){
                    playersAtStart.get(j).output.println(playersAtStart.size() + "players out of" + playersNumber);
                }
            }
            game.setBoard();
            game.addPlayers(playersAtStart);


        } finally {
            listener.close();
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