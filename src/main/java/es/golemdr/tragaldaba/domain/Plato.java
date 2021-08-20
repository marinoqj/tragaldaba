package es.golemdr.tragaldaba.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="platos")
public class Plato{

	private Long idPlato;
	private String nombre;
	private Double precio;
	private String nombreFoto;
	private Long tipo;



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="ID_PLATO")
public Long getIdPlato() {
		return idPlato;
	}
	public void setIdPlato(Long idPlato) {
		this.idPlato = idPlato;
	}
@Column(name="NOMBRE")
public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
@Column(name="PRECIO")
public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
@Column(name="NOMBRE_FOTO")
public String getNombreFoto() {
		return nombreFoto;
	}
	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}
@Column(name="TIPO")
public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

}
