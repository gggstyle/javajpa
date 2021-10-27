package javajpa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javajpa.model.Product;
import javajpa.model.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Window.Type;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTable table;
	UpdateProductFrame updateProductFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	UpdateProductFrame frame;

	public MainFrame() {
		setBackground(new Color(204, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/javajpa/icon/product.png")));
		setTitle("My Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydbconnect");
				EntityManager em = emf.createEntityManager();
				Product product = em.find(Product.class, 2);
				System.out.println(product.getPid() + " " + product.getPname());
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(533, 10, 155, 17);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("**กรุณากรอกข้อมูลค้นหาสินค้าจากชื่อหรือรายละเอียดของสินค้า**");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(73, 23, 387, 25);
		contentPane.add(lblNewLabel);

		textFieldID = new JTextField();
		textFieldID.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldID.setBounds(10, 58, 502, 35);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);

		JButton btnAddProduct = new JButton("เพิ่มข้อมูลสินค้า");
		btnAddProduct.setIcon(new ImageIcon(MainFrame.class.getResource("/javajpa/icon/add.png")));
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProductFrame frame = new AddProductFrame();
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		btnAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddProduct.setBounds(10, 119, 155, 46);
		contentPane.add(btnAddProduct);

		JButton btnNewButton_2 = new JButton("ค้นหาสินค้า");
		btnNewButton_2.setIcon(new ImageIcon(MainFrame.class.getResource("/javajpa/icon/search.png")));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductRepository repository = new ProductRepository();
				String input = textFieldID.getText();
				
					List<Product> products = repository.getProductByNameOrDetail(input);

					DefaultTableModel model = new DefaultTableModel();
					Object[] columns = { "id", "name", "detail", "price", "expire" };
					model.setColumnIdentifiers(columns);

					for (Product product : products) {
						Object[] obj = { product.getPid(), product.getPname(), product.getPdetail(), product.getPrice(),
								product.getExpire() };
						model.addRow(obj);
					}
					table.setModel(model);
				} 
		});
		btnNewButton_2.setBounds(533, 47, 155, 46);
		contentPane.add(btnNewButton_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 175, 678, 289);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setBackground(new Color(204, 255, 255));
		scrollPane.setViewportView(table);

		JButton btnNewButton_3 = new JButton("ลบข้อมูลสินค้า");
		btnNewButton_3.setIcon(new ImageIcon(MainFrame.class.getResource("/javajpa/icon/delete.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRow = table.getSelectedRowCount();
				if (selectRow == 1) {
					ProductRepository repository = new ProductRepository();
					int selected = table.getSelectedRow();
					int id = (int) table.getModel().getValueAt(selected, 0);
					int affected = repository.deleteProduct(id);
					if (affected > 0) {

						JOptionPane.showMessageDialog(null, "Delete Success");
						List<Product> products = repository.getProduct();

						DefaultTableModel model = new DefaultTableModel();
						Object[] columns = { "pid", "pname", "pdetail", "price", "expire" };
						model.setColumnIdentifiers(columns);
						for (Product product : products) {
							Object[] obj = { product.getPid(), product.getPname(), product.getPdetail(),
									product.getPrice(), product.getExpire() };
							model.addRow(obj);
						}
						table.setModel(model);
					} else {

					}
				} else {
					JOptionPane.showMessageDialog(null, "กรุณาเลือกข้อมูล");
					
				}

			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBounds(189, 119, 146, 46);
		contentPane.add(btnNewButton_3);

		JButton btnUpdate = new JButton("แก้ไขข้อมูลสินค้า");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateProductFrame frame = new UpdateProductFrame();
				int selectRow = table.getSelectedRowCount();
				if (selectRow == 1) {
					ProductRepository repository = new ProductRepository();
					int selected = table.getSelectedRow();
					if (selected > 0) {
						int pid = (int) table.getModel().getValueAt(selected, 0);
						frame.setPid(pid);
						frame.setLocationRelativeTo(null);
						frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						frame.setVisible(true);
						System.out.println(pid);
					} else {

					}
				} else {
					JOptionPane.showMessageDialog(null, "กรุณาเลือกข้อมูล");
				}

			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setIcon(new ImageIcon(MainFrame.class.getResource("/javajpa/icon/edit.png")));
		btnUpdate.setBounds(357, 119, 155, 46);
		contentPane.add(btnUpdate);

		JButton btnRepresh = new JButton("refresh");
		btnRepresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductRepository repository = new ProductRepository();
				List<Product> products = repository.getProduct();

				DefaultTableModel model = new DefaultTableModel();
				Object[] columns = { "pid", "pname", "pdetail", "price", "expire" };
				model.setColumnIdentifiers(columns);

				for (Product product : products) {
					Object[] obj = { product.getPid(), product.getPname(), product.getPdetail(), product.getPrice(),
							product.getExpire() };
					model.addRow(obj);
				}
				table.setModel(model);
			}
		});
		btnRepresh.setIcon(new ImageIcon(MainFrame.class.getResource("/javajpa/icon/refresh.png")));
		btnRepresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRepresh.setBounds(533, 119, 155, 46);
		contentPane.add(btnRepresh);
		actionPerformed();
	}

	protected void setPid(int pid) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed() {
		ProductRepository repository = new ProductRepository();
		List<Product> products = repository.getProduct();

		DefaultTableModel model = new DefaultTableModel();
		Object[] columns = { "pid", "pname", "pdetail", "price", "expire" };
		model.setColumnIdentifiers(columns);

		for (Product product : products) {
			Object[] obj = { product.getPid(), product.getPname(), product.getPdetail(), product.getPrice(),
					product.getExpire() };
			model.addRow(obj);
		}
		table.setModel(model);

	}
}
