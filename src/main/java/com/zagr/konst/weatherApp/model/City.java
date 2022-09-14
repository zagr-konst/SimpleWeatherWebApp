package com.zagr.konst.weatherApp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "City")
public class City {


    private String country;

    private String region;

    private String cityName;

    @Id
    private long cityID;

    @OneToMany(mappedBy = "id")
    private List<User> users;

    @Override
    public String toString() {
        return "City{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityID=" + cityID +
                '}';
    }
}
