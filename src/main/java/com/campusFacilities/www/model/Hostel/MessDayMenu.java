package com.campusFacilities.www.model.Hostel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mess_day_menu")
@Data
public class MessDayMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;


    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    // -------- Meals --------
    private String breakfast;
    private String lunch;
    private String dinner;

	
}