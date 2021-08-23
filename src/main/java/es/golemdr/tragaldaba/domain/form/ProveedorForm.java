package es.golemdr.tragaldaba.domain.form;




public class ProveedorForm{

	private Long idProveedor;
	private String razonSocial;
	private String direccion;
	private Long codPostal;
	private String localidad;
	private String telefono;
	private String email;



public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
public Long getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(Long codPostal) {
		this.codPostal = codPostal;
	}
public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
