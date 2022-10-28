package com.mateo.artemisa.app.fachadaimp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mateo.artemisa.app.fachada.ICliente;
import com.mateo.artemisa.app.modelo.Clientes;
import com.mateo.artemisa.app.util.JPAUtil;

public class Clienteimp implements ICliente{

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	@Override
	public List<Clientes> obtenerTodos() {
		List<Clientes> listaCliente = new ArrayList<Clientes>();
		Query hql = entity.createQuery("SELECT cl FROM Clientes cl");
		listaCliente = hql.getResultList();
		return listaCliente;
	}

	@Override
	public Clientes obtenerPorId(int idCliente) {
		Clientes cli = new Clientes();
		cli = entity.find(Clientes.class, idCliente);
		return cli;
	}

	@Override
	public void crearCliente(Clientes cliente) {
		entity.getTransaction().begin();
		entity.persist(cliente);
		entity.getTransaction().commit();
		
	}

	@Override
	public void editarCliente(Clientes cliente) {
		entity.getTransaction().begin();
		entity.merge(cliente);
		entity.getTransaction().commit();
		
	}

	@Override
	public void eliminarCliente(int idCliente) {
		Clientes cli = new Clientes();
		cli = entity.find(Clientes.class, idCliente);
		entity.getTransaction().begin();
		entity.remove(cli);
		entity.getTransaction().commit();
		
	}
	

}
