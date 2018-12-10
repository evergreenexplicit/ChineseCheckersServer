import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {
        /**
         * Runs the application. Pairs up clients that connect.
         */
        public static void main(String[] args) throws Exception {
            ServerSocket listener = new ServerSocket(8901);
            List<Player> playersAtStart = new ArrayList<Player>();
            System.out.println("ChineseCheckersServer is running");
            try {
                while (true) { // czy zaczynac, liczba graczy, plansza pozniej
                    playersAtStart.add(new PlayerClassic(listener.accept()),0);
                    playersAtStart.get(0).setRules();
                    int playersNumInt = Integer.parseInt(playersNum)
                    game.getPlayer(0).input.readLine();
                }


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
    MOVE

 */