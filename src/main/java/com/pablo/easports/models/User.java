package com.pablo.easports.models;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    // IDENTIFICATION
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="First name is required")
    @Size(min=3, max=30, message="First name must be between 3 and 30 characters")
    private String firstName;

    @NotBlank(message="Last Name is required")
    @Size(min=3, max=30, message="Last Name  must be between 3 and 30 characters")
    private String lastName;

    @NotBlank (message = "Email is required")
    @Email(message="Please enter a valid email!")
    private String email;

    @NotBlank (message="Please select a field position")
    private String fieldPosition;

    @NotNull(message = "Enter your age")
    @Min(value=18, message = "You must be 18-years-old")
    private Integer age;

    @NotBlank (message="Your password is required")
    @Size(min=8, message="Password must be at least eight characters")
    private String password;

    @Transient // will not store it in DB
    @NotBlank(message="Your password is required")
    private String confirmPass;

    // creating relationships
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "match_has_user",
            // join colum is related to the class your inside, in this case user
            joinColumns = @JoinColumn(name ="user_id"),
            // inverse is related to the class you are connecting, in this case match
            inverseJoinColumns = @JoinColumn(name="match_id")
    )
    private List<Match> games;

    @OneToMany(mappedBy = "submittedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Match> matchesSubmitted;

    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    // constructor
    public User() {}

    // method overloading but wait... What does this do again ?
    public User (
            String firstName,
            String lastName,
            String email,
            String fieldPosition,
            Integer age
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.fieldPosition = fieldPosition;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFieldPosition() {
        return fieldPosition;
    }

    public void setFieldPosition(String fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public List<Match> getGames() {
        return games;
    }

    public void setGames(List<Match> games) {
        this.games = games;
    }

    public List<Match> getMatchesSubmitted() {
        return matchesSubmitted;
    }

    public void setMatchesSubmitted(List<Match> matchesSubmitted) {
        this.matchesSubmitted = matchesSubmitted;
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
