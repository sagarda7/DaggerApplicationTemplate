package np.com.sagardevkota.daggertest.models;

/**
 * Created by HP on 10/3/2016.
 */
public class Repository {

    String name;
    String fullName;
    String description;

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
