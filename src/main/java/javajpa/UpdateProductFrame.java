package javajpa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import com.github.lgooddatepicker.components.DatePicker;

import javajpa.model.Product;
import javajpa.model.ProductRepository;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UpdateProductFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldProductUpdate;
	private JTextField textFieldPriceUpdate;
	private JTextArea textAreaDetailUpdate;
	private DatePicker datePickerUpdate;
	JLabel labelPid;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateProductFrame frame = new UpdateProductFrame();
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
	MainFrame frame;

	private int pid;
	
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	

	public UpdateProductFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				ProductRepository repository = new ProductRepository();
				Product product = repository.getProductById(getPid());
				if( product != null) {
					labelPid.setText(product.getPid()+"");
					textFieldProductUpdate.setText(product.getPname());
					textAreaDetailUpdate.setText(product.getPdetail());
					System.out.println(product.getPdetail());
					textFieldPriceUpdate.setText(product.getPrice()+"");
					//datePickerUpdate.setText(product.getExpire().toString());
					//java.sql.Date date = java.sql.Date.valueOf(datePickerUpdate.getDate());
					//datePickerUpdate.setText();
					datePickerUpdate.setDate(java.time.LocalDate.parse(product.getExpire().toString()));
				}
				
				//datePickerUpdate.setDate(java.time.LocalDate.parse(student.getBirthday().toString()));
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateProductFrame.class.getResource("/javajpa/icon/edit.png")));
		setTitle("แก้ไขข้อมูลสินค้า");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 534);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(144, 238, 144));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ชื่อสินค้า");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(40, 65, 57, 23);
		contentPane.add(lblNewLabel);
		
		textFieldProductUpdate = new JTextField();
		textFieldProductUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldProductUpdate.setBounds(150, 64, 219, 29);
		contentPane.add(textFieldProductUpdate);
		textFieldProductUpdate.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("รายละเอียดสินค้า");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(40, 154, 107, 23);
		contentPane.add(lblNewLabel_1);
		
		textAreaDetailUpdate = new JTextArea();
		textAreaDetailUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaDetailUpdate.setBounds(150, 155, 219, 74);
		contentPane.add(textAreaDetailUpdate);
		
		JLabel lblNewLabel_2 = new JLabel("ราคา");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(40, 287, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		textFieldPriceUpdate = new JTextField();
		textFieldPriceUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPriceUpdate.setBounds(150, 286, 219, 29);
		contentPane.add(textFieldPriceUpdate);
		textFieldPriceUpdate.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("วันหมดอายุ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(40, 373, 78, 23);
		contentPane.add(lblNewLabel_3);
		
		JButton btnUpdate = new JButton("ยืนยัน");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductRepository repository = new ProductRepository();
				//Product product = new Product();
				String id = labelPid.getText();
				//System.out.println(id);
				String name = textFieldProductUpdate.getText();
				String detail = textAreaDetailUpdate.getText();
				String price = textFieldPriceUpdate.getText();
				//String expire = datePickerUpdate.getText();
				
				
				try {
					Date date = new Date();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.YEAR, 1);
					date = calendar.getTime();
					SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
					String expire = date1.format(date);
					String image = "ไม่มีรูปภาพ";
					
					repository.updateProduct(Integer.parseInt(id), name, detail, price, expire, image);
					JOptionPane.showMessageDialog(null, "Update Success");
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(82, 441, 95, 29);
		contentPane.add(btnUpdate);
		
		JButton btnCancleUpdate = new JButton("ยกเลิก");
		btnCancleUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}

			
		});
		btnCancleUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancleUpdate.setBounds(274, 441, 95, 29);
		contentPane.add(btnCancleUpdate);
		
		datePickerUpdate = new DatePicker();
		datePickerUpdate.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 14));
		datePickerUpdate.setBounds(150, 378, 219, 29);
		contentPane.add(datePickerUpdate);
		
		labelPid = new JLabel("");
		labelPid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelPid.setBounds(150, 10, 87, 24);
		contentPane.add(labelPid);
		
		JLabel lblNewLabel_4 = new JLabel("รหัสสินค้า");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(40, 10, 64, 24);
		contentPane.add(lblNewLabel_4);
	}
	public void closeFrame() {
		this.dispose();
		
	}
}
