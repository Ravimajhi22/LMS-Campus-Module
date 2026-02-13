package com.campusFacilities.www.model.Transport;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transport_settings")
@Data
public class TransportSetting {

    @Id
    @Column(length = 50)
    private String key;  

    @Column(length = 255)
    private String value;
}