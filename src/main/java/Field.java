public class Field {
    boolean visible;
    int taken; //TODO: enum
    int playersTarget;
    public Field(){
        visible = false;
        taken = -1;
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
}
