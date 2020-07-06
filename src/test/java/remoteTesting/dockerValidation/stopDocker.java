package remoteTesting.dockerValidation;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class stopDocker {
    @Test
    public void shutdown() throws IOException, InterruptedException {

        String outputfile = "output.txt";
        boolean flag = false;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 30);
        long stopscript = cal.getTimeInMillis();

        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);

        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start dockerDown.cmd");
        Thread.sleep(3000);     //wait for output.txt
        while (System.currentTimeMillis() < stopscript) {  //wait for docker to start
            if (flag) {
                break;
            }
            BufferedReader reader = new BufferedReader(new FileReader(outputfile));
            String currentLine = reader.readLine();
            while (currentLine != null && !flag) {
                if (currentLine.contains("Shutdown complete")) {
                    System.out.println("Docker Shutdown complete");
                    flag = true;
                    break;
                }
                currentLine = reader.readLine();
            }
            reader.close();
        }
        Assert.assertTrue(flag);
        //Delete outputfile
        File fileToDelete = new File(outputfile);
        if (fileToDelete.delete()) {
            System.out.println(fileToDelete + "deleted");
        }
    }
}