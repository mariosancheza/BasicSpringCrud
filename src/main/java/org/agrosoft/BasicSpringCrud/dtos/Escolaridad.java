package org.agrosoft.BasicSpringCrud.dtos;

import static org.agrosoft.BasicSpringCrud.constants.ValidationConstants.CANNOT_BE_NULL_OR_EMPTY;
import static org.agrosoft.BasicSpringCrud.constants.ValidationConstants.OTHER_LENGHT_NOT_VALID;
import static org.agrosoft.BasicSpringCrud.constants.ValidationConstants.VALUE_NOT_RECOGNIZED;

import java.io.Serializable;

import org.agrosoft.BasicSpringCrud.enums.NivelEscolaridadEnum;
import org.agrosoft.BasicSpringCrud.validators.EnumValidator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="escolaridad")
public class Escolaridad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_escolaridad")
	@JsonIgnore
	private int idEscolaridad;
	
	@EnumValidator(enumClass = NivelEscolaridadEnum.class, message = "NIVEL_ESCOLARIDAD" + VALUE_NOT_RECOGNIZED)
	@NotEmpty(message = "NIVEL_ESCOLARIDAD" + CANNOT_BE_NULL_OR_EMPTY)
	@Size(min = 3, max = 40, message = "NIVEL_ESCOLARIDAD" + OTHER_LENGHT_NOT_VALID)
	private String escolaridad;
}
