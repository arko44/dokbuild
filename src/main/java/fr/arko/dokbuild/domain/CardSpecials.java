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
public class CardSpecials extends AbstractEntity {
	
	@Column
	private Integer cardId;
	
	@Column
	private Integer specialId;
	
	@Column
	private Integer lvStart;
	
	@Column
	private Integer eballNumStart;
	
}
