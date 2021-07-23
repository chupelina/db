package gson.demo.dtos.export;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserProductsRootDto {
    @XmlAttribute(name = "count")
    private int count;
    @XmlElement(name = "user")
    private List<UserProductsDto> users;

    public UserProductsRootDto() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserProductsDto> users) {
        this.users = users;
    }
}
