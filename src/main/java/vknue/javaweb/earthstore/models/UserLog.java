package vknue.javaweb.earthstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vknue.javaweb.earthstore.models.enums.EventType;

import java.time.Instant;

@Entity
@Table(name = "user_log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class    UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ipAddress;
    private Instant loginDate;

    @Enumerated(EnumType.STRING)
    private EventType eventType;
}