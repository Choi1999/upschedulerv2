package com.sparta.upschedulerv2.manager;

import jakarta.persistence.*;

@Entity
public class Manager{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;



}
