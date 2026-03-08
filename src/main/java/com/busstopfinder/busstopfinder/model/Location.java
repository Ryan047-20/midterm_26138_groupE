package com.busstopfinder.busstopfinder.model;

import jakarta.persistence.*;
import lombok.Data; 

@Data
@Entity
@Table(name = "Locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cell;
    private String sector;
    private String district;
    private String street;
    private Double latitude;
    private Double longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;
}

    

