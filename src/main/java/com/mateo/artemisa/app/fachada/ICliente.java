package com.mateo.artemisa.app.fachada;

import java.util.List;

import com.mateo.artemisa.app.modelo.Clientes;

public interface ICliente {
	public List<Clientes> obtenerTodos();
	public Clientes obtenerPorId(int idCliente);
	public void crearCliente(Clientes cliente);
	public void editarCliente(Clientes cliente);
	public void eliminarCliente(int idCliente);
}
