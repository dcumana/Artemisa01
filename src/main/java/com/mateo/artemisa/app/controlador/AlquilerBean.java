package com.mateo.artemisa.app.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.mateo.artemisa.app.fachadaimp.Alquilerimp;
import com.mateo.artemisa.app.fachadaimp.Clienteimp;
import com.mateo.artemisa.app.modelo.Alquileres;
import com.mateo.artemisa.app.modelo.Clientes;


@ManagedBean(name="alquilerBean")
@RequestScoped
public class AlquilerBean {
	
	private Alquilerimp alquilerimp = new Alquilerimp();
	
	private Clienteimp clienteimp = new Clienteimp();

	private Alquileres alquiler = new Alquileres();
	
	private Clientes cliente = new Clientes();
	
	private List<Clientes> listaClientes;

		
	public List<Clientes> getListaClientes() {
		return clienteimp.obtenerTodos(); 
	}

	public void setListaClientes(List<Clientes> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Alquileres getAlquiler() {
		return alquiler;
	}

	public void setAlquiler(Alquileres alquiler) {
		this.alquiler = alquiler;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public AlquilerBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlquilerBean(Alquileres alquiler, Clientes cliente) {
		super();
		this.alquiler = alquiler;
		this.cliente = cliente;
	}
	
	public List<Alquileres> obtenerTodos(){
		List<Alquileres> lista = new ArrayList<Alquileres>();
		lista = alquilerimp.obtenerTodos();
		return lista;
	}
	
	public String nuevo(){
    	return "registroAlquiler.xhtml?faces-redirect=true";
    }

	public String crear() { 
		alquiler.setCliente(cliente);
		alquilerimp.createAlquiler(alquiler);
		return "Alquiler.xhtml?faces-redirect=true"; 
	 }
	
	 public String editar(int idAlquiler, Alquileres alquileres) {
		 alquiler = alquilerimp.obtenerPorId(idAlquiler);		 
		 cliente.setIdCliente(alquileres.getCliente().getIdCliente());	
		 alquiler = alquileres;
		 Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	     sessionMap.put("alquiler", alquiler);
		 return "editarAlquiler.xhtml";
	 }
		 
	 
	 public String actualizar(Alquileres alquileres) {
		cliente = clienteimp.obtenerPorId(alquileres.getCliente().getIdCliente());
		alquileres.setCliente(cliente); 
		alquilerimp.editarAlquiler(alquileres);
		return "Alquiler.xhtml?faces-redirect=true"; 
	 }
	 
	 
	 
	 
	 public void eliminar(int idAlquiler) {
		 alquilerimp.eliminarAlquiler(idAlquiler);
	 }
	
}
