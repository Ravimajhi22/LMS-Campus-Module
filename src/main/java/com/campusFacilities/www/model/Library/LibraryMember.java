package com.campusFacilities.www.model.Library;
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
@Table(name = "library_members")
@Data
public class LibraryMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private Integer maxBooksAllowed = 3;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private Boolean isDeleted = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum MemberType { STUDENT, STAFF }
    public enum Status { ACTIVE, BLOCKED }

}
