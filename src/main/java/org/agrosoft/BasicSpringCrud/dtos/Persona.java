package org.agrosoft.BasicSpringCrud.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.agrosoft.BasicSpringCrud.constants.ValidationConstants.CANNOT_BE_NULL_OR_EMPTY;
import static org.agrosoft.BasicSpringCrud.constants.ValidationConstants.NAME_LENGHT_NOT_VALID;
import static org.agrosoft.BasicSpringCrud.constants.ValidationConstants.MUST_BE_IN_PAST;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="persona")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name="id_persona")
	private int idPersona;
	
	@NotEmpty(message = "NOMBRE" + CANNOT_BE_NULL_OR_EMPTY)
	@Size(min = 3, max = 20, message = "NOMBRE" + NAME_LENGHT_NOT_VALID)
	@Column(name="nombre")
	private String nombrePersona;
	
	@NotEmpty(message = "PRIMER_APELLIDO" + CANNOT_BE_NULL_OR_EMPTY)
	@Size(min = 3, max = 20, message = "PRIMER_APELLIDO" + NAME_LENGHT_NOT_VALID)
	@Column(name="primer_apellido")
	private String primerApellido;
	
	@Nullable
	@Size(min = 3, max = 20, message = "SEGUNDO_APELLIDO" + NAME_LENGHT_NOT_VALID)
	@Column(name="segundo_apellido")
	private String segundoApellido;
	
	@Past(message = MUST_BE_IN_PAST)
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Valid
	@ManyToOne
	@JoinColumn(name="id_entidad_nacimiento")
	private Entidad entidadNacimiento;
	
	@Valid
	@ManyToOne
	@JoinColumn(name="id_escolaridad")
	private Escolaridad nivelEscolaridad;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public int getEdad() {
		return (int) ChronoUnit.YEARS.between(this.fechaNacimiento, LocalDate.now());
	}
	
	
}
