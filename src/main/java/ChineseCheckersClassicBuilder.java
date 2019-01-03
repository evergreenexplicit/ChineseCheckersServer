import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ChineseCheckersClassicBuilder {

    public void create(ChineseCheckers game, int playersNumber,ServerSocket listener,Player admin) throws IOException { //todo chinese checkers classic?
        ArrayList<Player> playersAtStart = new ArrayList<>();
        playersAtStart.add(admin);
        admin.setGame(game);
        try {
            for (int i = 1; i < playersNumber; i++) {
                playersAtStart.add(new Player(listener.accept(), i));
                playersAtStart.get(i).setGame(game);
                for(int j = 0; j< playersAtStart.size();j++){
                    playersAtStart.get(j).send(  "CLASSIC " + playersNumber);
                    playersAtStart.get(j).send(
                           "MESSAGE " + playersAtStart.size() + " players out of" + playersNumber
                    );
                }
            }
            game.setBoard(playersNumber);
            game.addPlayers(playersAtStart);

 //???????????? tu nie powinno byc czegos
        } finally {
            listener.close();
        }


    }
}


//TODO:  terms of win,tests, mockito, finishing game