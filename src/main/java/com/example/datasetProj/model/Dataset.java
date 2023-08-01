package com.example.datasetProj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
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
@Entity
@Table(name = "datasets")
public class Dataset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name="name")
    private String name;

    @Type(type = "jsonb")
    @Column(name = "data_schema", columnDefinition = "jsonb")
    private Map<String, Object> data_schema = new HashMap<>();

    @Type(type = "jsonb")
    @Column(name = "router_config", columnDefinition = "jsonb")
    private Map<String, Object> router_config = new HashMap<>();

    @Enumerated(EnumType.STRING)
    public Status status;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "updated_by")
    private String updated_by;

    @Column(name = "created_date")
    private long createdDate;

    @Column(name = "updated_date")
    private long updatedDate;


    public enum Status{
        Live,
        Draft,
        Retired
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getData_schema() {
        return data_schema;
    }

    public void setData_schema(Map<String, Object> data_schema) {
        this.data_schema = data_schema;
    }

    public Map<String, Object> getRouter_config() {
        return router_config;
    }

    public void setRouter_config(Map<String, Object> router_config) {
        this.router_config = router_config;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
    }
}

