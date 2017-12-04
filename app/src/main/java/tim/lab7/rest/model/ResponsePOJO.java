package tim.lab7.rest.model;

import java.util.ArrayList;
import java.util.List;

public class ResponsePOJO {

    private String name;

    private List<String> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAttributes() {

        if (attributes == null) {
            attributes = new ArrayList<>();
        }

        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ResponsePOJO{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
