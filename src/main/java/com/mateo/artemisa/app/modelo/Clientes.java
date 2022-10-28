package com.mateo.artemisa.app.modelo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Cliente")
public class Clientes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	
	@Column 
	private String docCliente;
	
	@Column 
	private String nombreCliente;
	
	@Column 
	private String apellidoCliente;
	
	@Column 
	private String direccioncliente;
	
	@Column 
	private String emailCliente;
	
	@Column 
	private String telefonoCliente;
	
	@Column 
	private Date created_at;
	
	@Column 
	private Date updated_at;
	
	@ManyToOne
	@JoinColumn(name="idDoc", referencedColumnName ="idDoc")
	private Tipo_doc tipo_doc;

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getDocCliente() {
		return docCliente;
	}

	public void setDocCliente(String docCliente) {
		this.docCliente = docCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getDireccioncliente() {
		return direccioncliente;
	}

	public void setDireccioncliente(String direccioncliente) {
		this.direccioncliente = direccioncliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Tipo_doc getTipo_doc() {
		return tipo_doc;
	}

	public void setTipo_doc(Tipo_doc tipo_doc) {
		this.tipo_doc = tipo_doc;
	}

	public Clientes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Clientes(int idCliente, String docCliente, String nombreCliente, String apellidoCliente,
			String direccioncliente, String emailCliente, String telefonoCliente, Date created_at, Date updated_at,
			Tipo_doc tipo_doc) {
		super();
		this.idCliente = idCliente;
		this.docCliente = docCliente;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.direccioncliente = direccioncliente;
		this.emailCliente = emailCliente;
		this.telefonoCliente = telefonoCliente;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.tipo_doc = tipo_doc;
	}
	
	
	
}
