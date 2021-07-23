package gson.demo.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSalesRootDto {
    @XmlElement(name = "user")
    private List<UserWithSalesDto> users;

    public UserWithSalesRootDto() {
    }

    public List<UserWithSalesDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSalesDto> users) {
        this.users = users;
    }
}
