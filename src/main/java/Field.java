public class Field {
    boolean visible;
    int taken;
    int playerTarget;
    int playersTarget;
    public Field(){
        visible = false;
        taken = -1;
        playerTarget = -1;
    }
    void setVisible(boolean visible){
        this.visible = visible;
    }
    boolean getVisible(){
        return visible;
    }
    int getTaken(){
        return taken;
    }
    void setTaken(int taken){
        this.taken = taken;
    }
    int getPlayerTarget(){
        return playerTarget;
    }
    void setPlayerTarget(int playerTarget){
        this.playerTarget = playerTarget;
    }
}
