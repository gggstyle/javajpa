package javajpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.formdev.flatlaf.ui.FlatTableCellBorder.Selected;

import javajpa.model.Product;

public class ProductRepository {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydbconnect");
	

	
	public Product getProductById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Product.class, id);
	}

	public List<Product> getProduct(){
		EntityManager em = emf.createEntityManager();
		TypedQuery<Product> query = em
				.createQuery("select p from Product p", Product.class);
		return query.getResultList();
	}
	public List<Product> getProductByNameOrDetail(String input) {
		input = "%" + input + "%";
		EntityManager em = emf.createEntityManager();
		TypedQuery<Product> query = em
				.createQuery("select p from Product p where p.pname like :input or p.pdetail like :input", Product.class);
	
		query.setParameter("input", input);
		return query.getResultList();
	}
	public void updateProductName(int id,String name) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Product product = getProductById(id);
			product.setPname(name);
			
			em.merge(product);
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
	}
	public void updateProduct(int id,String name,String detail,String price,String date,String image) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Product product = getProductById(id);
			product.setPname(name);
			product.setPdetail(detail);
			product.setPrice(Integer.parseInt(price));	
			java.sql.Date expire = java.sql.Date.valueOf(date);
			System.out.println(date);
			product.setExpire(expire);
			//System.out.println(expire);
			product.setImage(image);
			
			em.merge(product);
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
	}
	public void updateProduct(Product product) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			//Product product = getProductById(id);
//			product.setPname(name);	
			em.merge(product);
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
	}
	public void addProduct(Product product) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(product);
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
	}
	public int deleteProduct(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();	
			try {
				tr.begin();
				//Product product  = new Product();
				Product product = getProductById(id);
				product = em.merge(product);
				em.remove(product);
				tr.commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				tr.rollback();
			}
			return id;			
		}
	
}