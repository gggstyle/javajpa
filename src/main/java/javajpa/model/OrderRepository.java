package javajpa.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class OrderRepository {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydbconnect");
	
	public List<Orders> getOrders(){
		EntityManager em = emf.createEntityManager();
		TypedQuery<Orders> query = em
				.createQuery("select o from Orders o", Orders.class);
		return query.getResultList();
	}
}
