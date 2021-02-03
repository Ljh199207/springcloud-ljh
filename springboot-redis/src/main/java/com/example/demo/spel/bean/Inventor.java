package com.example.demo.spel.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Inventor {

    private String name;

    private Date time;

    private String ads;
}
