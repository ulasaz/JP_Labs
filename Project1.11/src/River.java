import java.util.List;

public class River {
    private String polishName;
    private String czechName;
    private String germanName;
    private String flowsInto;
    private List<Point> path;

    public River(String polishName,
                 String czechName,
                 String germanName,
                 String flowsInto,
                 List<Point> path) {
        this.czechName = czechName;
        this.polishName = polishName;
        this.germanName = germanName;
        this.path = path;
        this.flowsInto = flowsInto;
    }
}


