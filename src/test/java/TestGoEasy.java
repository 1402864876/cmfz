import io.goeasy.GoEasy;
import org.junit.Test;

public class TestGoEasy {
    @Test
    public void easy(){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-b0e8ecd689ab44baa461d1946d81c058");
        goEasy.publish("my_channel","Hello, GoEasy!");
    }
}
