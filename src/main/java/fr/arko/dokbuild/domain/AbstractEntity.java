package fr.arko.dokbuild.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import fr.arko.dokbuild.config.converter.LocalDateTimeConverter2;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

	@Id
	private Integer id;

	@Convert(converter = LocalDateTimeConverter2.class)
	private LocalDateTime createdAt = null;

//	@Column
//	@Convert(converter = LocalDateTimeConverter2.class)
//	private LocalDateTime openAt = null;

	@Convert(converter = LocalDateTimeConverter2.class)
	private LocalDateTime updatedAt = null;
}
