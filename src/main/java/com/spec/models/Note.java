package com.spec.models;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Note {

    String uuid = UUID.randomUUID().toString();
    String title;
    String body;

}
