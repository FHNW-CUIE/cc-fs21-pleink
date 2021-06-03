package cuie.project.template_businesscontrol;

public class Canton {
    private final String urlName;
    private final String abbreviation;
    private final String name;

    public Canton(String urlName, String abbreviation, String name) {
        this.urlName = urlName;
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public String getUrlName() {
        return urlName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Canton{" +
                "urlName='" + urlName + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
