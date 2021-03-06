package remoteTesting.dockerValidation;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class startDocker {
    @Test
    public void startUp() throws IOException, InterruptedException {

        String outputfile = "output.txt";
        boolean flag = false;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 30);
        long stopscript = cal.getTimeInMillis();

        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);

        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start dockerUp.cmd");
        Thread.sleep(3000);     //wait for output.txt
        while (System.currentTimeMillis() < stopscript) {  //wait for docker to start
            if (flag) {
                break;
            }
            BufferedReader reader = new BufferedReader(new FileReader(outputfile));
            String currentLine = reader.readLine();
            while (currentLine != null && !flag) {
                if (currentLine.contains("The node is registered to the hub")) {
                    System.out.println("First dockernode is registred to the hub");
                    flag = true;
                    break;
                }
                currentLine = reader.readLine();
            }
            reader.close();
        }
        Assert.assertTrue(flag);
        runtime.exec("cmd /c start dockerScaleChrome.cmd"); //create new Instances of Chrome
        Thread.sleep(5000); //final wait 5 secs before starting to deploy the test
    }
}