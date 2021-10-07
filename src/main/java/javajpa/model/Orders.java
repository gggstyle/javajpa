package javajpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Orders.findAll", query="SELECT o FROM Orders o")
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ord_id")
	private int ordId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ord_date")
	private Date ordDate;

	private String status;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="order")
	private List<Item> items;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="username")
	private Customer customer;

	public Orders() {
	}

	public int getOrdId() {
		return this.ordId;
	}

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	public Date getOrdDate() {
		return this.ordDate;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setOrder(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setOrder(null);

		return item;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}