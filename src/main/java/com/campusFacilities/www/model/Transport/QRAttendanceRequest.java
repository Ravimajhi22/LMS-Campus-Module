package com.campusFacilities.www.model.Transport;

import lombok.Data;

@Data
public class QRAttendanceRequest {

    private Long studentId;
    private Long vehicleId;
    private Long routeId;
    private String session;
}