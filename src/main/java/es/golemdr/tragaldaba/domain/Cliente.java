package es.golemdr.tragaldaba.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente{

	private Long idCliente;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String dni;
	private String direccion;



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="ID_CLIENTE")
public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
@Column(name="NOMBRE")
public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
@Column(name="APELLIDOS")
public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
@Column(name="TELEFONO")
public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
@Column(name="DNI")
public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
@Column(name="DIRECCION")
public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
