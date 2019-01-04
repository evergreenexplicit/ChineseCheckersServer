import java.util.ArrayList;

public class Notifier {
    private ArrayList<Player> players; //todo usunac w chinesecheckersclassic a dodac tutaj
    public void notifyAll(String message){ //todo  SEPARATE CLASSES FROM EACH OTHER
        for(int i = 0; i < players.size();i++){

            players.get(i).send(message);
        }
    }
    public void notifyAboutTurn(int playerIdx){
            for (int i = 0; i < players.size(); i++) {
                if(i == playerIdx)
                    players.get(i).send("MESSAGE Your turn!");
                else
                    players.get(i).send("MESSAGE Turn of player nr " + playerIdx);
            }
        }
    void addPlayers(ArrayList<Player> players){
        this.players = players;
    }
    ArrayList<Player> getPlayers(){return players;}
    }

