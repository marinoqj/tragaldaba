package es.golemdr.tragaldaba.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBObject;

@Document(collection="platos")
public class PlatoMongo {

	@Id
	private String id;
	
	private Long idPlato;
	private String nombre;
	private Double precio;
	private String nombreFoto;
	private Long tipo;
	private String descripcion;
	private String activo;
	
	public PlatoMongo() {}

	public PlatoMongo(String nombre, Double precio, String nombreFoto, Long tipo, String descripcion, String activo) {
		this.nombre = nombre;
		this.precio = precio;
		this.nombreFoto = nombreFoto;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getIdPlato() {
		return idPlato;
	}

	public void setIdPlato(Long idPlato) {
		this.idPlato = idPlato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getNombreFoto() {
		return nombreFoto;
	}

	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	public BasicDBObject asBson() {
		BasicDBObject bson = new BasicDBObject();
		ObjectId objectId = null;
		
		if (this.id == null) {
			objectId = ObjectId.get();
			bson.put("id", objectId);
			this.id = objectId.toString();
		} else {
			bson.put("id", this.id);
		}	
		
		bson.put("idPlato", this.idPlato);
		bson.put("nombre", this.nombre);
		bson.put("precio", this.precio);
		bson.put("nombreFoto", this.nombreFoto);
		bson.put("tipo", this.tipo);
		bson.put("descripcion", this.descripcion);
		bson.put("activo", this.activo);
		
		return bson;
	}

	@Override
	public String toString() {
		String pattern = "Plato [id=%d, idPlato=%s, nombre='%s', precio=%f, nombreFoto='%s'" 
						+ ", tipo=%d, descripcion='%s', activo=%s]";
		return String.format(pattern, id, idPlato, nombre, precio, nombreFoto, tipo, descripcion, activo);
	}

}

// NOTAS:
// 1) @Document(collection="platos") indica que cada documento Plato se insertará en la colección platos
// 2) El MongoDB un documento es equivalente a un registro y una colección sería el equivalente de una tabla
// 3) @Id indica el campo que actuará como identificador de cada documento (registro). Da igual cómo lo llamemos,
//    MongoDB almacenará este atributo como "_id"



