package com.pablo.easports.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Match name is required")
    @Size(min = 3, max = 18, message = "Match name must be between 3 and 18 characters")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 150, message = "Description must be between 3 and 150 characters")
    private String description;

    @NotNull(message = "Price is required ")
    @Min(value = 10, message = "$10 per match ")
    private Double price;

    // creating relationships
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "match_has_user",
            // join colum is related to the class your inside, in this case match
            joinColumns = @JoinColumn(name ="match_id"),
            // inverse is related to the class you are connecting, in this case user
            inverseJoinColumns = @JoinColumn(name="user_id")
    )

    // The above REL stores a list of users, referred to it as players
    // The idea being players can join and join a match
            List<User> players;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User submittedBy;


    // This will not allow the createdAt column to be updated after creation
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    // constructor
    public Match() {
    }

    // method overloading but wait... What does this do again ?
    public Match(
            String name,
            String location,
            LocalDateTime date,
            String description,
            Double price

    ) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.description = description;
        this.price = price;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public User getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(User submittedBy) {
        this.submittedBy = submittedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    @PrePersist
    private void onCreate(){
        this.createdAt = new Date();
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @PreUpdate
    private void onUpdate(){
        this.updatedAt = new Date();
    }
}
