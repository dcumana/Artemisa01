package com.mateo.artemisa.app.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.mateo.artemisa.app.fachadaimp.Visitaimp; 
import com.mateo.artemisa.app.fachadaimp.TipoVentaAlquilerimp; 
import com.mateo.artemisa.app.fachadaimp.Clienteimp; 
import com.mateo.artemisa.app.fachadaimp.Productoimp;
import com.mateo.artemisa.app.modelo.TipoVentaAlquileres;
import com.mateo.artemisa.app.modelo.Usuarios;
import com.mateo.artemisa.app.modelo.Visitas;
import com.mateo.artemisa.app.modelo.Clientes;
import com.mateo.artemisa.app.modelo.Productos;



@ManagedBean(name="visitaBean")
@RequestScoped
public class VisitasBean {

	private Visitaimp visitaimp = new Visitaimp();
	
	private Clienteimp clienteimp = new Clienteimp();
	
	private Productoimp productoimp = new Productoimp();
	
	private TipoVentaAlquilerimp tipoVentaAlquilerimp = new TipoVentaAlquilerimp();
	
	private Visitas visita = new Visitas();
	
	private Clientes cliente = new Clientes(); 
	
	private Productos producto =  new Productos();

	private TipoVentaAlquileres tipoVentaAlquiler =  new TipoVentaAlquileres();

	private List<Clientes> listaClientes;
	
	private List<Productos> listaProductos;
	
	private List<TipoVentaAlquileres> listaTipoVentaAlquileres;
	
	///
	
	public List<Clientes> getListaClientes() {
		return clienteimp.obtenerTodos(); 
	}

	public void setListaClientes(List<Clientes> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Productos> getListaProductos() {
		return productoimp.obtenerTodos(); 
	}

	public void setListaProductos(List<Productos> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<TipoVentaAlquileres> getListaTipoVentaAlquileres() {
		return tipoVentaAlquilerimp.obtenerTodos();
	}

	public void setListaTipoVentaAlquileres(List<TipoVentaAlquileres> listaTipoVentaAlquileres) {
		this.listaTipoVentaAlquileres = listaTipoVentaAlquileres;
	}

	public Visitas getVisita() {
		return visita;
	}
	
	public void setVisita(Visitas visita) {
		this.visita = visita;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public TipoVentaAlquileres getTipoVentaAlquiler() {
		return tipoVentaAlquiler;
	}

	public void setTipoVentaAlquiler(TipoVentaAlquileres tipoVentaAlquiler) {
		this.tipoVentaAlquiler = tipoVentaAlquiler;
	}

	public VisitasBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VisitasBean(Visitas visita, Clientes cliente, Productos producto, TipoVentaAlquileres tipoVentaAlquiler) {
		super();
		this.visita = visita;
		this.cliente = cliente;
		this.producto = producto;
		this.tipoVentaAlquiler = tipoVentaAlquiler;
	}

	public List<Visitas> obtenerTodos(){
		List<Visitas> lista = new ArrayList<Visitas>();
		lista = visitaimp.obtenerTodos();
		return lista;
	}
	
	public String nuevo(){
    	return "registroVisita.xhtml?faces-redirect=true";
    }

	public String crear() { 
		visita.setCliente(cliente);
		visita.setProducto(producto);
		visita.setTipoVentaAlquiler(tipoVentaAlquiler);
		visitaimp.crearVisitas(visita);
	   	return "Visitas.xhtml?faces-redirect=true"; 
	 }
	
	
	 public String editar(int idVisita, Visitas visitas) {
		 visita = visitaimp.obtenerPorId(idVisita);
		 cliente.setIdCliente(visitas.getCliente().getIdCliente());		 	
		 producto.setIdProducto(visitas.getProducto().getIdProducto());
		 tipoVentaAlquiler.setIdTipoVentaAlquiler(visitas.getTipoVentaAlquiler().getIdTipoVentaAlquiler());
		 visita = visitas;
		 Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	     sessionMap.put("visita", visita);
		 return "editarVisita.xhtml";
	 }
	
	 public String actualizar(Visitas visitas) {
		cliente = clienteimp.obtenerPorId(visitas.getCliente().getIdCliente()); 
		producto = productoimp.obtenerPorId(visitas.getProducto().getIdProducto());
		tipoVentaAlquiler = tipoVentaAlquilerimp.obtenerPorId(visitas.getTipoVentaAlquiler().getIdTipoVentaAlquiler());
		visitas.setCliente(cliente);
		visitas.setProducto(producto);
		visitas.setTipoVentaAlquiler(tipoVentaAlquiler);
		visitaimp.editarVisitas(visitas);
		return "Visitas.xhtml?faces-redirect=true"; 
	 }
	 
	 public void eliminar(int idVisita) {
		 visitaimp.eliminarVisitas(idVisita);
	 }
	 
	
	
}
