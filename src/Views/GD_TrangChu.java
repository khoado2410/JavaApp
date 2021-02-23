package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class GD_TrangChu extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_TrangChu frame = new GD_TrangChu();
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
	public GD_TrangChu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 632, 404);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 128));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(186, 85, 211));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(123, 104, 238));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(30, 144, 255));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(138, 43, 226));
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(new Color(60, 179, 113));
		
		JPanel panel_4_2 = new JPanel();
		panel_4_2.setBackground(new Color(0, 191, 255));
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setBackground(new Color(255, 182, 193));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_4_1, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4_2, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4_1_1, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
					.addGap(2))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_4_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_4_2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_4_1_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addGap(157))
		);
		
		JLabel lblNewLabel_7 = new JLabel("L\u1EC5 t\u00E2n");
		lblNewLabel_7.setForeground(new Color(255, 255, 224));
		lblNewLabel_7.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/images/baseline_receipt_long_white_24dp.png")));
		GroupLayout gl_panel_4_1_1 = new GroupLayout(panel_4_1_1);
		gl_panel_4_1_1.setHorizontalGroup(
			gl_panel_4_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_4_1_1.setVerticalGroup(
			gl_panel_4_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_4_1_1.setLayout(gl_panel_4_1_1);
		
		JLabel lblNewLabel_6 = new JLabel("\u0110\u1ED1i t\u00E1c");
		lblNewLabel_6.setForeground(new Color(255, 255, 224));
		lblNewLabel_6.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/images/baseline_people_white_24dp.png")));
		GroupLayout gl_panel_4_2 = new GroupLayout(panel_4_2);
		gl_panel_4_2.setHorizontalGroup(
			gl_panel_4_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_4_2.setVerticalGroup(
			gl_panel_4_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_4_2.setLayout(gl_panel_4_2);
		
		JLabel lblNewLabel_5 = new JLabel("B\u00E1o c\u00E1o");
		lblNewLabel_5.setForeground(new Color(255, 255, 224));
		lblNewLabel_5.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/images/baseline_library_books_white_24dp.png")));
		GroupLayout gl_panel_4_1 = new GroupLayout(panel_4_1);
		gl_panel_4_1.setHorizontalGroup(
			gl_panel_4_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_4_1.setVerticalGroup(
			gl_panel_4_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_4_1.setLayout(gl_panel_4_1);
		
		JLabel lblNewLabel_4 = new JLabel("Kho h\u00E0ng");
		lblNewLabel_4.setForeground(new Color(255, 255, 224));
		lblNewLabel_4.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/images/baseline_house_white_24dp.png")));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblNewLabel_3 = new JLabel("T\u00EDnh gi\u00E1 v\u1ED1n");
		lblNewLabel_3.setForeground(new Color(255, 255, 224));
		lblNewLabel_3.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/images/baseline_menu_book_white_24dp.png")));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblNewLabel_2 = new JLabel("\u0110\u0103ng nh\u1EADp");
		lblNewLabel_2.setForeground(new Color(255, 255, 224));
		lblNewLabel_2.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/images/baseline_account_circle_white_24dp.png")));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("C\u00E0i \u0111\u1EB7t");
		lblNewLabel_1.setBackground(new Color(240, 128, 128));
		lblNewLabel_1.setForeground(new Color(255, 255, 224));
		lblNewLabel_1.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/images/baseline_settings_white_24dp.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		lblNewLabel = new JLabel("B\u00E1n h\u00E0ng");
		lblNewLabel.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/images/baseline_shopping_cart_white_24dp.png")));
		lblNewLabel.setForeground(new Color(255, 255, 224));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(36))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
	}
}
