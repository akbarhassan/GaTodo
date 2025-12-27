package com.ga.todoApp.model;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;


    @Column
    private String name;

    @Column
    private String description;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category",orphanRemoval = true)
    private List<Item> itemList;


    @Column
    @CreationTimestamp
    private LocalDateTime createdDate;


    @Column
    @UpdateTimestamp
    private LocalDateTime updatedDate;

}
