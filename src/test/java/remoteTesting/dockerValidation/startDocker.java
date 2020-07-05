package remoteTesting.dockerValidation;

import org.junit.Test;

import java.io.IOException;

public class startDocker {
    @Test
    public void startUp() throws IOException {

        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start dockerUp.cmd");
    }
}
