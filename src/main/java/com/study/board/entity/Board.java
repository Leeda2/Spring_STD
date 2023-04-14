package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
//DB 테이블을 의미
public class Board {
    @Id
    //id:프라이머리키 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //identity : 마리아 디비에서 사용
    private Integer id;
    private String title;
    private String content;
    private String filename;
    private String filepath;
}
