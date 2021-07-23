package gson.demo.dtos.imports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRootDto {
    @XmlElement(name = "user")
    List<UserImportDto> users;

    public UserRootDto() {
    }

    public List<UserImportDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserImportDto> users) {
        this.users = users;
    }
}
