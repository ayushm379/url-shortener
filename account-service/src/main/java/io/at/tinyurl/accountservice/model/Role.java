package io.at.tinyurl.accountservice.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"code"})})
@Data
@NoArgsConstructor
public class Role {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "role_id", insertable = false, updatable = false, nullable = false)
    private String id; 

    @Column(name = "code", updatable = false, nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    public Role(String code, String description) {
        this.code = code;
        this.description = description;
    }

}

