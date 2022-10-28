package com.mateo.artemisa.app.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.mateo.artemisa.app.fachadaimp.Ventaimp;
import com.mateo.artemisa.app.fachadaimp.Clienteimp;
import com.mateo.artemisa.app.fachadaimp.Usuarioimp;
import com.mateo.artemisa.app.modelo.Ventas;
import com.mateo.artemisa.app.modelo.Clientes;
import com.mateo.artemisa.app.modelo.Usuarios;

@ManagedBean(name="ventaBean")
@RequestScoped
public class VentaBean {

	private Ventaimp ventaimp = new Ventaimp();
	
	private Clienteimp clienteimp = new Clienteimp();
	
	private Usuarioimp usuarioimp = new Usuarioimp();
	
	private Ventas venta = new Ventas();
	
	private Clientes cliente = new Clientes();
	
	private Usuarios usuario = new Usuarios();
	
	private List<Clientes> listaClientes;
	
	private List<Usuarios> listaUsuarios;

		
	public List<Clientes> getListaClientes() {
		return clienteimp.obtenerTodos(); 
	}

	public void setListaClientes(List<Clientes> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Usuarios> getListaUsuarios() {
		return usuarioimp.obtenerTodos(); 
	}

	public void setListaUsuarios(List<Usuarios> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Ventas getVenta() {
		return venta;
	}

	public void setVenta(Ventas venta) {
		this.venta = venta;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public VentaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VentaBean(Ventas venta, Clientes cliente, Usuarios usuario) {
		super();
		this.venta = venta;
		this.cliente = cliente;
		this.usuario = usuario;
	}
	
	
	public List<Ventas> obtenerTodos(){
		List<Ventas> lista = new ArrayList<Ventas>();
		lista = ventaimp.obtenerTodos();
		return lista;
	}
	
	public String nuevo(){
    	return "registroVenta.xhtml?faces-redirect=true";
    }

	public String crear() { 
		venta.setCliente(cliente);
		venta.setUsuario(usuario); 
		ventaimp.crearVenta(venta); 
		return "Venta.xhtml?faces-redirect=true"; 
	}
	
	public String editar(int idVenta, Ventas ventas) {
		 venta = ventaimp.obtenerPorId(idVenta);
		 cliente.setIdCliente(ventas.getCliente().getIdCliente());
		 usuario.setIdUsuario(ventas.getUsuario().getIdUsuario());
		 venta = ventas;
		 Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	     sessionMap.put("venta", venta);
		 return "editarVenta.xhtml";
	}
		 
	public String actualizar(Ventas ventas) {
		cliente = clienteimp.obtenerPorId(ventas.getCliente().getIdCliente());
		usuario = usuarioimp.obtenerPorId(ventas.getUsuario().getIdUsuario());
		ventas.setCliente(cliente); 
		ventas.setUsuario(usuario);
		ventaimp.editarVenta(ventas); 
		return "Venta.xhtml?faces-redirect=true"; 
	}
	 
	public void eliminar(int idVenta) {
		 ventaimp.eliminarVenta(idVenta); 
	}
	
}
