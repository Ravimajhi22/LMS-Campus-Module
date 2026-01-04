package com.campusFacilities.www.model.Transport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stop_id")
    private Long stopId;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private RouteWay route;

    @Column(name = "stop_name", nullable = false)
    private String stopName;

    @Column(name = "sequence_number", nullable = false)
    private Integer sequenceNumber;
}
