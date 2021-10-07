package javajpa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboPopup;

import javajpa.model.Product;
import javajpa.model.ProductRepository;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import com.github.lgooddatepicker.components.DatePicker;

public class AddProductFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAddProductName;
	private JTextField textFieldprice;
	private JTextArea textAreaDetail;
	private DatePicker datePicker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProductFrame frame = new AddProductFrame();
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
	public AddProductFrame() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("D:\\3CS\\data warehouse\\myShopIcon-20210707T020618Z-001\\myShopIcon\\product.png"));
		setTitle("เพิ่มสินค้า");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 534);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ชื่อสินค้า");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(29, 54, 77, 22);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("รายละเอียดสินค้า");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(29, 121, 109, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ราคา");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(29, 219, 45, 13);
		contentPane.add(lblNewLabel_2);

		textFieldAddProductName = new JTextField();
		textFieldAddProductName.setBounds(141, 58, 233, 29);
		contentPane.add(textFieldAddProductName);
		textFieldAddProductName.setColumns(10);

		textFieldprice = new JTextField();
		textFieldprice.setBounds(141, 213, 233, 29);
		contentPane.add(textFieldprice);
		textFieldprice.setColumns(10);

		JButton btnConfirm = new JButton("ยืนยัน");
		btnConfirm.setForeground(Color.DARK_GRAY);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductRepository repository = new ProductRepository();
				Product product = new Product();
				String name = textFieldAddProductName.getText();
				String price = textFieldprice.getText();

//				product.setPid(130);
				if (name != null) {
					product.setPname(textFieldAddProductName.getText());
					product.setPdetail(textAreaDetail.getText());
					product.setPrice(Integer.parseInt(price));
					product.setImage("ไม่มีรูปภาพ");
					Calendar cal = Calendar.getInstance();
					Date today = cal.getTime();
					cal.add(Calendar.YEAR, 1);
					Date nextYear = cal.getTime();
					java.sql.Date date = java.sql.Date.valueOf(datePicker.getDate());
					product.setExpire(date);
					repository.addProduct(product);
					String st = "Insert Sucess";
					JOptionPane.showMessageDialog(null, st);
				}

			}
		});
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirm.setBounds(75, 420, 124, 29);
		contentPane.add(btnConfirm);

		JButton btnCancle = new JButton("ยกเลิก");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}

		});
		btnCancle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancle.setBounds(250, 420, 124, 29);
		contentPane.add(btnCancle);

		textAreaDetail = new JTextArea();
		textAreaDetail.setBounds(148, 122, 226, 65);
		contentPane.add(textAreaDetail);

		JLabel lblNewLabel_3 = new JLabel("expire");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(29, 281, 56, 22);
		contentPane.add(lblNewLabel_3);

		datePicker = new DatePicker();
		datePicker.setBounds(141, 286, 233, 29);
		contentPane.add(datePicker);
	}

	void closeFrame() {
		this.dispose();

	}
}
