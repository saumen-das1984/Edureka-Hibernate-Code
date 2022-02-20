package com.hibernate.sql.anno.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "anotheremployee_skills")
@Data
@Builder
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skillId")
	private int skillId;
	
	@Column(name = "skillName")
	private String skillName;
	
	@Column(name = "skillType")
	private String skillType;
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
	
}
