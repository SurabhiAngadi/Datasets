package com.example.datasetProj.entities;

import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "datasets")
@TypeDefs({@TypeDef(name="jsonb",typeClass = JsonBinaryType.class)})
public class Dataset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]*")
    @Column(name="name")
    private String name;

    @NotNull
    @Type(type = "jsonb")
    @Column(name = "data_schema", columnDefinition = "jsonb")
    private Map<String, String> data_schema = new HashMap<>();

    @NotNull
    @Type(type = "jsonb")
    @Column(name = "router_config", columnDefinition = "jsonb")
    private Map<String, String> router_config = new HashMap<>();

    @Enumerated(EnumType.STRING)
    public Status status;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]*")
    @Column(name = "created_by")
    private String createdBy;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]*")
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;


    public enum Status{
        Live,
        Draft,
        Retired
    }

}

