package dkeep.guI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class OptionsFrame extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionsFrame window = new OptionsFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OptionsFrame() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(600, 500));
		frame.setBounds(100, 100, 641, 572);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		frame.getContentPane().add(panel);
		
		JLabel WidthLabel = new JLabel("Width : ");
		WidthLabel.setBounds(165, 54, 107, 37);
		WidthLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		WidthLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		WidthLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		WidthLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		
		textField_1 = new JTextField();
		textField_1.setBounds(293, 58, 111, 32);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_1.setPreferredSize(new Dimension(10, 32));
		textField_1.setColumns(5);
		
		JLabel HeightLabel = new JLabel("Height : ");
		HeightLabel.setBounds(156, 126, 116, 37);
		HeightLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		textField = new JTextField();
		textField.setBounds(293, 130, 111, 32);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField.setPreferredSize(new Dimension(10, 32));
		textField.setColumns(5);
		panel.setLayout(null);
		panel.add(WidthLabel);
		panel.add(textField_1);
		panel.add(HeightLabel);
		panel.add(textField);
	}

	public Dimension getFramePreferredSize() {
		return frame.getPreferredSize();
	}
	public void setFramePreferredSize(Dimension preferredSize) {
		frame.setPreferredSize(preferredSize);
	}
}
