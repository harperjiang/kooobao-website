package com.kooobao.util.print;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import org.harper.frm.core.logging.LogManager;

public class PrintExpressOrderFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8594678789214315176L;

	private PrintExpressOrderController controller;

	private JTextArea remarkArea;

	private JTextField refNumberField;

	private ContactInfoPanel fromPanel;

	private ContactInfoPanel toPanel;

	private JComboBox companyCombo;

	public PrintExpressOrderFrame(PrintExpressOrderController controller) {
		super();
		this.controller = controller;

		setTitle("Print Express Order");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 500);

		setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1));
		add(mainPanel, BorderLayout.CENTER);

		fromPanel = new ContactInfoPanel();
		fromPanel.setBorder(new TitledBorder("From Information"));
		mainPanel.add(fromPanel);

		toPanel = new ContactInfoPanel();
		toPanel.setBorder(new TitledBorder("To Information"));
		mainPanel.add(toPanel);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		add(topPanel, BorderLayout.NORTH);

		JLabel refNumberLabel = new JLabel("Ref Number");
		topPanel.add(refNumberLabel);

		refNumberField = new JTextField();
		refNumberField.setPreferredSize(new Dimension(100, 25));
		topPanel.add(refNumberField);

		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						getController().load();
					}
				}).start();
			}
		});
		topPanel.add(loadButton);

		companyCombo = new ExpressCompanyCombo();
		companyCombo.setPreferredSize(new Dimension(100, 25));
		topPanel.add(companyCombo);

		JButton printButton = new JButton("Print");
		topPanel.add(printButton);
		printButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Thread(new Runnable() {
					public void run() {
						try {
							getController().print();
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									JOptionPane.showMessageDialog(
											PrintExpressOrderFrame.this,
											"Printed");
								}
							});

						} catch (final Exception ex) {
							LogManager.getInstance()
									.getLogger(PrintExpressOrderFrame.class)
									.error("Error while printing", ex);
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									JOptionPane.showMessageDialog(
											PrintExpressOrderFrame.this,
											ex.getMessage(), "Error Occurrs",
											JOptionPane.ERROR_MESSAGE);
								}
							});
						}
					}
				}).start();

			}
		});

		JPanel bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.SOUTH);

		bottomPanel.setLayout(new FlowLayout());

		JLabel remarkLabel = new JLabel("Remark");
		bottomPanel.add(remarkLabel);
		remarkArea = new JTextArea();

		JScrollPane sp = new JScrollPane();
		sp.setPreferredSize(new Dimension(420, 60));
		sp.setViewportView(remarkArea);

		bottomPanel.add(sp);

		setVisible(true);
	}

	public ContactInfoPanel getFromPanel() {
		return fromPanel;
	}

	public ContactInfoPanel getToPanel() {
		return toPanel;
	}

	public PrintExpressOrderController getController() {
		return controller;
	}

	public void setController(PrintExpressOrderController controller) {
		this.controller = controller;
	}

	public JComboBox getCompanyCombo() {
		return companyCombo;
	}

	public JTextArea getRemarkArea() {
		return remarkArea;
	}

	public JTextField getRefNumberField() {
		return refNumberField;
	}

}
