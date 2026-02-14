package com.campusFacilities.www.model.marketing;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "messenger_schedules")
@Data
public class MessengerSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private CampaignTable campaign;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AudienceType audienceType;
    public enum AudienceType {
        ALL_LEARNERS,
        UNREGISTERED_LEARNERS,
        COURSE_WISE_LEARNERS,
        SEGMENT_WISE_LEARNERS,
        SPECIFIC_LEARNERS
    }
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SendType sendType;
    public enum SendType {

        NOW,
        SCHEDULED,
        RECURRING
    }

    private LocalDate sendDate;

    private LocalTime sendTime;

    @Enumerated(EnumType.STRING)
    private RecurringType recurringType;
    public enum RecurringType {

        DAILY,
        WEEKLY,
        MONTHLY
    }

    @Column(nullable = false)
    private String notificationTitle;

    @Column(nullable = false, length = 3000)
    private String message;

    private String customIcon;

    private String customImageUrl;

    private String targetLink;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id")
    private MessengerSchedule schedule;
}