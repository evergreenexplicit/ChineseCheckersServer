import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ChineseCheckersBuilder {

    public void create(Moves moves,Board board,WinConditions winConditions,String gameTypeName, int playersNumber, ServerSocket listener, Player admin) throws IOException {
        List<Bot> bots = new ArrayList<>();
        Notifier notifier = new Notifier();
        TurnHandler turnHandler = new TurnHandler(playersNumber,0,bots);

        ArrayList<Player> playersAtStart = new ArrayList<>();
        playersAtStart.add(admin);

        admin.setMoves(moves);
        admin.setWinConditions(winConditions);
        admin.setNotifier(notifier);
        admin.setTurnHandler(turnHandler);

        try {
            for (int i = 1; i < playersNumber; i++) {
                playersAtStart.add(
                        new Player.Builder(i)
                                .withSocket(listener.accept())
                                .withMoves(moves)
                                .withTurnHandler(turnHandler)
                                .withNotifier(notifier)
                                .withWinConditions(winConditions)
                        .build()
                );
                playersAtStart.get(i).send(  gameTypeName+ " " + playersNumber);
                playersAtStart.get(i).send(
                        "MESSAGE You are player " + i
                );

                for(int j = 0; j< playersAtStart.size();j++){

                    playersAtStart.get(j).send(
                           "MESSAGE " + playersAtStart.size() + " players out of" + playersNumber
                    );
                }
            }
            moves.setBoard(board);
            notifier.addPlayers(playersAtStart);

            for (Player aPlayersAtStart : playersAtStart) aPlayersAtStart.start();
            for (Player aPlayersAtStart : playersAtStart) aPlayersAtStart.join();


            System.out.println("End of the game, quitting...");
 //???????????? tu nie powinno byc czegos
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            listener.close();
        }


    }
}

