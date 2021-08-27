package es.golemdr.tragaldaba.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	private Long idPedido;
	private Long numArticulos;
	private Double total;
	private Date fecha;
	private Long tipoEntrega;
//	private Long idCliente;
	private Long idProveedor;
	private List<Plato> platos = new ArrayList<>();
	private Cliente cliente;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PEDIDO")
	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	@Column(name = "NUM_ARTICULOS")
	public Long getNumArticulos() {
		return numArticulos;
	}

	public void setNumArticulos(Long numArticulos) {
		this.numArticulos = numArticulos;
	}

	@Column(name = "TOTAL")
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(name = "FECHA")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "TIPO_ENTREGA")
	public Long getTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(Long tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}

//	@Column(name = "ID_CLIENTE")
//	public Long getIdCliente() {
//		return idCliente;
//	}
//
//	public void setIdCliente(Long idCliente) {
//		this.idCliente = idCliente;
//	}

	@Column(name = "ID_PROVEEDOR")
	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_cliente")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "platos_pedido", joinColumns = { @JoinColumn(name = "id_pedido") }, inverseJoinColumns = {
			@JoinColumn(name = "id_plato") })
	public List<Plato> getPlatos() {
		return platos;
	}

	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", numArticulos=" + numArticulos + ", total=" + total + ", fecha="
				+ fecha + ", tipoEntrega=" + tipoEntrega + ", idProveedor=" + idProveedor + ", platos=" + platos
				+ ", cliente=" + cliente + "]";
	}

	


}
