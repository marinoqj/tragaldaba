package es.golemdr.tragaldaba.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="proveedores")
public class Proveedor{

	private Long idProveedor;
	private String razonSocial;
	private String direccion;
	private Long codPostal;
	private String localidad;
	private String telefono;
	private String email;



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="ID_PROVEEDOR")
public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
@Column(name="RAZON_SOCIAL")
public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
@Column(name="DIRECCION")
public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
@Column(name="COD_POSTAL")
public Long getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(Long codPostal) {
		this.codPostal = codPostal;
	}
@Column(name="LOCALIDAD")
public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
@Column(name="TELEFONO")
public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
@Column(name="EMAIL")
public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
