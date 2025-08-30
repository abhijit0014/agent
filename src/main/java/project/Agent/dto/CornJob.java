package project.Agent.dto;
import java.util.Random;

public class CornJob {

    private String name;

    public CornJob() {
        this.name = "CornJob-"+ (new Random().nextInt(20000) + 15000);
    }

    public String getName() {
        return name;
    }
}
