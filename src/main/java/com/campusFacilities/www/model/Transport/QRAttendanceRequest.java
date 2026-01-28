package com.campusFacilities.www.model.Transport;

import lombok.Data;

@Data
public class QRAttendanceRequest {

    private Long studentId;
    private Long vehicleId;
    private String session;
}