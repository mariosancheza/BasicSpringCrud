package org.agrosoft.BasicSpringCrud.dtos;

import static org.agrosoft.BasicSpringCrud.constants.ValidationConstants.CANNOT_BE_NULL_OR_EMPTY;
import static org.agrosoft.BasicSpringCrud.constants.ValidationConstants.OTHER_LENGHT_NOT_VALID;
import static org.agrosoft.BasicSpringCrud.constants.ValidationConstants.VALUE_NOT_RECOGNIZED;

import java.io.Serializable;

import org.agrosoft.BasicSpringCrud.enums.EntidadFederativaEnum;
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
@Table(name="entidad")
public class Entidad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_entidad")
	@JsonIgnore
	private int idEntidad;
	
	@EnumValidator(enumClass = EntidadFederativaEnum.class, ignoreCase=true, ignoreAccentMark=true, message = "ENTIDAD_FEDERATIVA" + VALUE_NOT_RECOGNIZED)
	@NotEmpty(message = "ENTIDAD_FEDERATIVA" + CANNOT_BE_NULL_OR_EMPTY)
	@Size(min = 3, max = 40, message = "ENTIDAD_FEDERATIVA" + OTHER_LENGHT_NOT_VALID)
	@Column(name="nombre_entidad")
	private String nombreEntidad;
	
}
