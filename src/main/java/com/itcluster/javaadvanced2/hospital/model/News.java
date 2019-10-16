package com.itcluster.javaadvanced2.hospital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class News implements Comparable<News> {

    @Id()
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name="author_id", nullable = false)
    private User author;

    private Date date;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String text;

    private String type;

    @Override
    public int compareTo(News o){
        return o.getDate().compareTo(this.getDate());
    }
}
