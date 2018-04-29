package fr.arko.dokbuild.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class LeaderSkills extends AbstractEntity {
	
	private String name;

	private String description;
	
	private Integer execTimingType;
	
	private Integer causalityType;
	private Integer efficacyType1;
	private Integer efficacyType2;
	private Integer efficacyType3;
	private Integer efficacyType4;
	private Integer efficacyType5;
	private Integer targetType;
	private Integer subTargetTypeSetId1;
	private Integer subTargetTypeSetId2;
	private Integer subTargetTypeSetId3;
	private Integer subTargetTypeSetId4;
	private Integer subTargetTypeSetId5;
	private Integer calcOption1;
	private Integer calcOption2;
	private Integer calcOption3;
	private Integer calcOption4;
	private Integer calcOption5;
	private Integer cauValue1;
	private Integer cauValue2;
	private Integer cauValue3;
	
	@Column(name="eff1_value1")
	private Integer eff1Value1;
	@Column(name="eff1_value2")
	private Integer eff1Value2;
	@Column(name="eff1_value3")
	private Integer eff1Value3;
	@Column(name="eff2_value1")
	private Integer eff2Value1;
	@Column(name="eff2_value2")
	private Integer eff2Value2;
	@Column(name="eff2_value3")
	private Integer eff2Value3;
	@Column(name="eff3_value1")
	private Integer eff3Value1;
	@Column(name="eff3_value2")
	private Integer eff3Value2;
	@Column(name="eff3_value3")
	private Integer eff3Value3;
	@Column(name="eff4_value1")
	private Integer eff4Value1;
	@Column(name="eff4_value2")
	private Integer eff4Value2;
	@Column(name="eff4_value3")
	private Integer eff4Value3;
	@Column(name="eff5_value1")
	private Integer eff5Value1;
	@Column(name="eff5_value2")
	private Integer eff5Value2;
	@Column(name="eff5_value3")
	private Integer eff5Value3;
	
	
	
}
