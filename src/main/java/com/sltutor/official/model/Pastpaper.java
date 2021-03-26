package com.sltutor.official.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pastpapers")
public class Pastpaper {

    @Id
    private String id;

    private String school;

    private String subject;

    private int term;

    private int year;

    private String medium;

    private String paperLink;

    private Date createdAt;

    private Date updatedAt;

}
