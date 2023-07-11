package com.example.datasetProj.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "datasets")
@TypeDefs({@TypeDef(name="jsonb",typeClass = JsonBinaryType.class)})
public class Dataset {

    @Id
    private String id;

    @Type(type = "jsonb")
    @Column(name = "data_schema", columnDefinition = "jsonb", insertable = false, updatable = false)
    private Map<String, String> data_schema = new HashMap<>();

    @Type(type = "jsonb")
    @Column(name = "router_config", columnDefinition = "jsonb")
    private Map<String, String> router_config = new HashMap<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

        private enum Status{
        Live,
        Draft,
        Retired
    }

}

