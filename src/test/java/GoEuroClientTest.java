import com.goeuro.client.GoEuroClient;
import com.goeuro.client.GoEuroServerException;
import org.junit.Test;

import java.util.List;

public class GoEuroClientTest {

    @Test
    public void DataReceivedTest() throws GoEuroServerException {
        List l = GoEuroClient.getInfo("Potsdam");
        assert l != null;
    }

}
