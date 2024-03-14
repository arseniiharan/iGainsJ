package com.iGainsTwo.iGainsJ.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "awards")
public class UserAwards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "award_title", unique = true)
    private String awardTitle;

    @Column(name = "award_image")
    private String awardImage;

    @Column(name = "award_desc")
    private String awardDescription;

}

