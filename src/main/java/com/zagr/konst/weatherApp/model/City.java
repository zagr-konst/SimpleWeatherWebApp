package com.zagr.konst.weatherApp.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode @ToString
public class City {

    private long id;

    private String country;

    private String region;

    private String cityName;

    private long cityID;
}
