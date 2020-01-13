package com.example.listener.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Messages")
public class Message {

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "Message")
    private String message;

}
