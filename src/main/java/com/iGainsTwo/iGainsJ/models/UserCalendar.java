package com.iGainsTwo.iGainsJ.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "calendar")
public class UserCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_name")
    private Exercise exercise;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    @Column(name = "break_duration")
    private int breakDuration;

    @Column(name = "start_time")
    private int startTime;

    @Column(name = "set_quaintity")
    private int setQuantity;

}
