package com.iGainsTwo.iGainsJ.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "calendar")
public class UserCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_name")
    private Exercise exercise;

    @Column(name = "is_completed", nullable = false)
    @NotBlank
    private boolean isCompleted;

    @Column(name = "break_duration")
    private int breakDuration;

    @Column(name = "start_time")
    private int startTime;

    @Column(name = "set_quaintity")
    private int setQuantity;

}
