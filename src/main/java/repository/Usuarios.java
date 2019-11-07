package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Usuario;
import repository.filter.UsuarioFilter;
import service.NegocioException;
import util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}
	
	@Transactional
	public void remover(Usuario usuario) {
		
		try {
			usuario = buscarPorId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Usuário não pode ser excluído.");
		}
		
	}

	public Usuario buscarPorEmail(String email) {
		try {
			return manager.createQuery("from Usuario where upper(email) = :email", Usuario.class)
					.setParameter("email", email.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	} 

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);
		
		if (StringUtils.isNotBlank(filtro.getEmail())) {
			criteria.add(Restrictions.eq("email",filtro.getEmail()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome",filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Usuario buscarPorId(Long id) {
		return manager.find(Usuario.class, id);

	}
	
	public List<Usuario> vendedores() {
		// TODO FIltrar abesnas vendedores (por um grupo especifico)
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

}
