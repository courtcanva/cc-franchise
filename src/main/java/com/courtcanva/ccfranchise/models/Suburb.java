package com.courtcanva.ccfranchise.models;

import com.courtcanva.ccfranchise.constants.AUState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "suburb")
public class Suburb {

    @Id
    private Long sscCode;

    @Column(name = "suburb")
    private String suburbName;

    @Column
    private int postcode;

    @Column
    @Enumerated(EnumType.STRING)
    private AUState state;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "dutyAreas")
    private Set<Franchisee> availableFranchisees = new HashSet<>();

    public Set<Franchisee> getFranchisee() {
        return availableFranchisees;
    }

}
