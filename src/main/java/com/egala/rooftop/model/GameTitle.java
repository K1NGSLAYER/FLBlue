package com.egala.rooftop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Document("GameTitles")
public class GameTitle {
    private String id;
    @Id
    private String titleID;
    private String officialTitle;
    private String developer;
    private String publisher;
    private Set<String> genre;
    private String coverImageUrl;
    private Set<String> marqueeImages;
    private Date releaseDate;
}
