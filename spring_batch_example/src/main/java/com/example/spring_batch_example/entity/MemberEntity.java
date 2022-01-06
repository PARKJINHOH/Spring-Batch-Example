package com.example.spring_batch_example.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean status;


    public MemberEntity changeStatusTrue() {
        this.status = true;
        return this;
    }

    @Builder
    public MemberEntity() {}

    @Builder
    public MemberEntity(String name) {
        this.name = name;
        this.status = false;
    }
}

