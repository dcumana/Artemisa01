package com.mateo.artemisa.app.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.mateo.artemisa.app.fachadaimp.Tipodocimp;
import com.mateo.artemisa.app.fachadaimp.Clienteimp;
import com.mateo.artemisa.app.modelo.Clientes;
import com.mateo.artemisa.app.modelo.Tipo_doc;
import com.mateo.artemisa.app.modelo.Usuarios;

@ManagedBean(name="clienteBean")
@RequestScoped
public class ClientesBean {
	
	private Clienteimp clienteimp = new Clienteimp();
	
	private Tipodocimp tipodocImp = new Tipodocimp();

	private Clientes cliente = new Clientes();
	
	private Tipo_doc tipo_doc = new Tipo_doc();

	private List<Tipo_doc> listaTipDoc;
	
	///
	public Clientes getCliente() {
		return cliente;
	}

	public List<Tipo_doc> getListaTipDoc() {
		return tipodocImp.obtenerTodos();
	}

	public void setListaTipDoc(List<Tipo_doc> listaTipDoc) {
		this.listaTipDoc = listaTipDoc;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Tipo_doc getTipo_doc() {
		return tipo_doc;
	}

	public void setTipo_doc(Tipo_doc tipo_doc) {
		this.tipo_doc = tipo_doc;
	}

	public ClientesBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientesBean(Clientes cliente, Tipo_doc tipo_doc) {
		super();
		this.cliente = cliente;
		this.tipo_doc = tipo_doc;
	}
	
	public List<Clientes> obtenerTodos(){
		List<Clientes> lista = new ArrayList<Clientes>();
		lista = clienteimp.obtenerTodos();
		return lista;
	}
	
	public String nuevo(){
    	return "registroCliente.xhtml?faces-redirect=true";
    }
	
	public String crear() { 
		cliente.setTipo_doc(tipo_doc);
		clienteimp.crearCliente(cliente); 
		return "Clientes.xhtml?faces-redirect=true"; 
	}

	 public String editar(int idCliente, Clientes clientes) {
		 cliente = clienteimp.obtenerPorId(idCliente);
		 tipo_doc.setIdDoc(clientes.getTipo_doc().getIdDoc());
		 cliente = clientes;
	     Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	     sessionMap.put("cliente", cliente);
		 return "editarCliente.xhtml";
	 }
	
	 public String actualizar(Clientes clientes) {
		tipo_doc = tipodocImp.obtenerPorId(clientes.getTipo_doc().getIdDoc());
		clientes.setTipo_doc(tipo_doc);
		clienteimp.editarCliente(clientes);
		return "Clientes.xhtml?faces-redirect=true"; 
	 }
	 
	 public void eliminar(int idCliente) {
		 clienteimp.eliminarCliente(idCliente);
	 }
	
}
