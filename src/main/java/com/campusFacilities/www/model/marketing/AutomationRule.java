package com.campusFacilities.www.model.marketing;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "automation_rules")
@Data
public class AutomationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TriggerType triggerType;
    public enum TriggerType {
        USER_ENROLLED,
        CART_ABANDONED,
        USER_INACTIVE,
        COURSE_COMPLETED,
        CUSTOM_EVENT
    }
    
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    public enum ActionType {
        SEND_EMAIL,
        SEND_WHATSAPP,
        SEND_PUSH,
        APPLY_COUPON,
        ADD_TAG
    }

    private Integer delayInMinutes;

    private Boolean active = true;

    private LocalDateTime createdAt;
}