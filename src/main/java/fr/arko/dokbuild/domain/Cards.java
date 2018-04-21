package fr.arko.dokbuild.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	private String name;

	private Integer characterId;
	private Integer awakedCardId;
	private Integer cost;
	private Integer rarity;
	private Integer hpMax;
	private Integer atkMax;
	private Integer defMax;
	private Integer element;
	private Integer lvMax;
	private Integer specialId;
	private Integer passiveSkillSetId;
	
	@Transient
	private PassiveSkills passiveSkill;
	
	private Integer leaderSkillId;
	
	@Transient
	private LeaderSkills leaderSkill;
	
	@Transient
	private List<LinkSkills> linkSkills;;
	
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
	
	@Transient
	private List<CardCategories> cardCategories;
	
	@Column(name="card_category1_id")
	private Integer cardCategory1Id;

	@Column(name="card_category2_id")
	private Integer cardCategory2Id;
	
	@Column(name="card_category3_id")
	private Integer cardCategory3Id;
	
	@Column(name="card_category4_id")
	private Integer cardCategory4Id;
	
	@Column(name="card_category5_id")
	private Integer cardCategory5Id;
	
	@Column(name="card_category6_id")
	private Integer cardCategory6Id;
	
}
