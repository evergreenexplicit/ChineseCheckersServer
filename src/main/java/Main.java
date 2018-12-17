import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ChineseCheckersClassicBuilder builder = new ChineseCheckersClassicBuilder();
       builder.create(new ChineseCheckersClassic(),8901);

    }
}
