package com.hibernate.association.annotation.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "job_m2m_h5_anno")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "job_id")
	private int jobId;
	
	@Column (name = "job_name")
	private String jobName;
	
	@ManyToMany
	@JoinTable(name = "job_company_m2m_h5_anno" , joinColumns = {@JoinColumn (name = "job_id")}, 
	inverseJoinColumns = {@JoinColumn(name = "company_id")})
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<Company> company;
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
