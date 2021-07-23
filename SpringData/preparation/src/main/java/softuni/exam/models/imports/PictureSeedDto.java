package softuni.exam.models.imports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import softuni.exam.models.Car;

import java.time.LocalDateTime;

public class PictureSeedDto {
    @Expose
    @SerializedName(value = "name")
    private String name;
    @Expose
    private LocalDateTime dateAndTime;
    @Expose
    private int car;

    public PictureSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }
}
