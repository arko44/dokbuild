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
public class Cards extends AbstractEntity {
	
	@Column
	private String name;

	@Column
	private Integer characterId;
	@Column
	private Integer awakedCardId;
	@Column
	private Integer cost;
	@Column
	private Integer rarity;
	@Column
	private Integer hpMax;
	@Column
	private Integer atkMax;
	@Column
	private Integer defMax;
	@Column
	private Integer element;
	@Column
	private Integer lvMax;
	
	@Column
	private Integer specialId;
	@Column
	private Integer passiveSkillSetId;
	@Column
	private Integer leaderSkillId;
	
	@Column(name="link_skill1_id")
	private Integer linkSkill1Id;
	@Column(name="link_skill2_id")
	private Integer linkSkill2Id;
	@Column(name="link_skill3_id")
	private Integer linkSkill3Id;
	@Column(name="link_skill4_id")
	private Integer linkSkill4Id;
	@Column(name="link_skill5_id")
	private Integer linkSkill5Id;
	@Column(name="link_skill6_id")
	private Integer linkSkill6Id;
	@Column(name="link_skill7_id")
	private Integer linkSkill7Id;
	
	@Column(name="card_category1_id")
	private Integer cardCategory1Id;

	@Column(name="card_category2_id")
	private Integer cardCategory2Id;
	
}
