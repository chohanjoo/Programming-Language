import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import except.InputSpecialCharException;
import except.ModifyNumberException;
import except.NotMemberException;
import except.OutOfNameBoundException;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class SnackBar extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	public ClientManager clientManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		SnackBar frame = new SnackBar();
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public SnackBar() {
		clientManager = new ClientManager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		setTab1(tabbedPane);
		setTab2(tabbedPane);

	}

	private void setTab1(JTabbedPane tabbedPane) {
		JPanel panel = new JPanel();
		tabbedPane.addTab("주문 관리", null, panel, null);
		panel.setLayout(null);

		JLabel label_4 = new JLabel("\uB0A0      \uC9DC");
		label_4.setBounds(100, 40, 62, 18);
		panel.add(label_4);

		JLabel label_5 = new JLabel("\uACE0\uAC1D\uBC88\uD638");
		label_5.setBounds(100, 104, 62, 18);
		panel.add(label_5);

		JLabel label_6 = new JLabel("\uBA54      \uB274");
		label_6.setBounds(100, 170, 62, 18);
		panel.add(label_6);

		textField_4 = new JTextField();
		textField_4.setBounds(210, 37, 238, 24);
		panel.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(210, 101, 238, 24);
		panel.add(textField_5);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("김밥");
		comboBox.addItem("떡볶이");
		comboBox.addItem("순대");
		comboBox.addItem("오뎅");
		comboBox.addItem("튀김");
		comboBox.setSelectedIndex(0);

		comboBox.setBounds(210, 167, 238, 24);
		panel.add(comboBox);

		JButton btnNewButton = new JButton("\uC8FC\uBB38");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String date = textField_4.getText();
						String client_number = textField_5.getText();
						String menu = comboBox.getSelectedItem().toString();

						try {
							if (date.compareTo("") == 0 || client_number.compareTo("") == 0)
								throw new NotMemberException("빈 칸이 있습니다.");
							if (clientManager.clientList.containsKey(client_number)) {
								Client client = clientManager.clientList.get(client_number);
								client.addOrder(date, menu);
								JOptionPane.showMessageDialog(null, "주문이 완료되었습니다.");
								if (client.getCountOfOrder() == 3) {
									client.setCountOfOrder(0);
									JOptionPane.showMessageDialog(null, client_number + "번 고객님\n무료쿠폰이 배송되었습니다.");
									client.coupon++;
								}
							} else {
								Client client = null;
								if (clientManager.clientList.containsKey("0000"))
									client = clientManager.clientList.get("0000");
								else
									client = new Client("0000", "0", "Guest", "0", "0");

								client.addOrder(date, menu);
								clientManager.clientList.put("0000", client);
								client.setCountOfOrder(0);
							}
							clientManager.inputFile();
						} catch (NotMemberException e1) {
							System.out.println(e1);
						}
					}
				});
				
				thread.start();
				
			}
		});
		btnNewButton.setBounds(100, 233, 128, 59);
		panel.add(btnNewButton);

		JButton button_3 = new JButton("\uC8FC\uBB38\uCDE8\uC18C");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String date = textField_4.getText();
						String client_number = textField_5.getText();
						String menu = comboBox.getSelectedItem().toString();
						Boolean find = false;

						try {
							
							if(date.compareTo("")==0 || client_number.compareTo("")==0)
								throw new NotMemberException("빈 칸이 있습니다.");
							if (clientManager.clientList.containsKey(client_number)) {
								Client client = clientManager.clientList.get(client_number);
								for (int i = 0; i < client.order_date.size(); ++i)
									if (client.order_date.get(i).compareTo(date) == 0
											&& client.order_menu.get(i).compareTo(menu) == 0) {
										client.order_date.remove(i);
										client.order_menu.remove(i);
										if (client.getCountOfOrder() != 0)
											--client.countOfOrder;
										else {
											--client.coupon;
											client.setCountOfOrder(2);
										}
										find = true;
										JOptionPane.showMessageDialog(null, "주문을 취소하였습니다.");
									}

								if (!find)
									throw new NotMemberException("주문한 기록이 없습니다.");
							} else {
								Client client = clientManager.clientList.get("0000");
								for (int i = 0; i < client.order_date.size(); ++i)
									if (client.order_date.get(i).compareTo(date) == 0
											&& client.order_menu.get(i).compareTo(menu) == 0) {
										client.order_date.remove(i);
										client.order_menu.remove(i);
										find = true;
									}

								if (!find)
									throw new NotMemberException("주문한 기록이 없습니다.");
							}
							clientManager.inputFile();
						} catch (NotMemberException e1) {
							System.out.println(e1);
							JOptionPane.showMessageDialog(null, "에러");
						}
					}
				});
				thread.start();
				
			}
		});
		button_3.setBounds(343, 233, 135, 59);
		panel.add(button_3);
	}

	private void setTab2(JTabbedPane tabbedPane) {
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("고객 관리", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel label = new JLabel("\uACE0\uAC1D\uBC88\uD638");
		label.setBounds(100, 25, 62, 18);
		panel_1.add(label);

		JLabel label_1 = new JLabel("\uACE0 \uAC1D \uBA85");
		label_1.setBounds(100, 70, 62, 18);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("\uC804\uD654\uBC88\uD638");
		label_2.setBounds(100, 115, 62, 18);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("\uAC00 \uC785 \uC77C");
		label_3.setBounds(100, 163, 62, 18);
		panel_1.add(label_3);

		JLabel lblNewLabel = new JLabel("\uC0DD     \uC77C");
		lblNewLabel.setBounds(100, 206, 62, 18);
		panel_1.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(195, 22, 297, 30);
		panel_1.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(195, 67, 297, 30);
		panel_1.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(195, 112, 297, 30);
		panel_1.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(195, 160, 297, 30);
		panel_1.add(textField_3);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(195, 203, 297, 30);
		panel_1.add(textField_6);

		JButton button = new JButton("\uACE0\uAC1D\uB4F1\uB85D");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String number = textField.getText();
						String name = textField_1.getText();
						String phoneNumber = textField_2.getText();
						String joinDate = textField_3.getText();
						String birthday = textField_6.getText();
						Client client = null;

						try {
							for (int i = 0; i < number.length(); ++i)
								if (!(number.charAt(i) > 47 && number.charAt(i) < 58))
									throw new InputSpecialCharException("고객번호에 숫자 이외의 문자가 입력되었습니다.");
							if (name.length() > 10)
								throw new OutOfNameBoundException("이름이 10자이상 입력되었습니다.");
							for (int i = 0; i < name.length(); ++i)
								if (!(name.charAt(i) > 64 && name.charAt(i) < 91)
										&& !(name.charAt(i) > 96 && name.charAt(i) < 123)
										&& !name.matches(".*[ㄱ-ㅎ ㅏ-ㅣ 가-힣]+.*"))
									throw new InputSpecialCharException("이름에 특수문자가 입력되었습니다.");
							for (int i = 0; i < phoneNumber.length(); ++i)
								if (!(phoneNumber.charAt(i) > 47 && phoneNumber.charAt(i) < 58)
										&& phoneNumber.charAt(i) != '-')
									throw new InputSpecialCharException("전화번호를 다시 입력해주세요.");

							for (int i = 0; i < joinDate.length(); ++i)
								if (!(joinDate.charAt(i) > 47 && joinDate.charAt(i) < 58) && joinDate.charAt(i) != '/')
									throw new InputSpecialCharException("가입날짜를 다시 입력해주세요.");

							if (number.compareTo("") == 0 || name == null || phoneNumber == null || birthday == null
									|| joinDate == null)
								throw new NotMemberException("빈 칸이 있습니다.");
							client = new Client(number, joinDate, name, phoneNumber, birthday);

							java.util.Iterator iterator = clientManager.clientList.keySet().iterator();
							while (iterator.hasNext()) {
								String key = (String) iterator.next();
								Client person = clientManager.clientList.get(key);

								if (person.name.compareTo(name) == 0 && person.number.compareTo(number) != 0)
									throw new ModifyNumberException("고객번호는 수정할 수 없습니다.");
							}

							Client tp_client = null;
							if ((tp_client = clientManager.searchClient(number)) != null) {
								if (tp_client.name.compareTo(name) == 0) {
									tp_client.modifyBirthday(birthday);
									tp_client.modifyJoinData(joinDate);
									tp_client.modifyPhoneNumber(phoneNumber);
									clientManager.addClient(tp_client);
									JOptionPane.showMessageDialog(null, "고객정보를 수정하였습니다.");
								} else
									JOptionPane.showMessageDialog(null, "고객번호가 중복됩니다.");
							} else {
								clientManager.addClient(client);
								JOptionPane.showMessageDialog(null, "고객이 등록되었습니다.");
							}
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
							textField_3.setText("");
							textField_6.setText("");
						} catch (OutOfNameBoundException e1) {
							System.out.println(e1);
							JOptionPane.showMessageDialog(null, "에러");
						} catch (InputSpecialCharException e2) {
							System.out.println(e2);
							JOptionPane.showMessageDialog(null, "에러");
						} catch (ModifyNumberException e3) {
							System.out.println(e3);
							JOptionPane.showMessageDialog(null, "에러");
						} catch (NotMemberException e4) {
							System.out.println(e4);
							JOptionPane.showMessageDialog(null, "에러");
						}

					}
				});
				thread.start();

			}
		});
		button.setBounds(100, 257, 105, 47);
		panel_1.add(button);

		JButton button_1 = new JButton("\uACE0\uAC1D\uAC80\uC0C9");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						String number = textField.getText();
						try {
							if (number.compareTo("") == 0)
								throw new NotMemberException("고객번호를 입력해야합니다.");

							Client client;
							if ((client = clientManager.searchClient(number)) != null) {
								textField_1.setText(client.name);
								textField_2.setText(client.phoneNumber);
								textField_3.setText(client.joinDate);
								textField_6.setText(client.birthday);
								JOptionPane.showMessageDialog(null, "고객을 찾았습니다.");
							} else
								JOptionPane.showMessageDialog(null, "고객을 찾지 못했습니다.");
						} catch (NotMemberException e1) {
							System.out.println(e1);
						}

					}
				});

				thread.start();

			}
		});
		button_1.setBounds(246, 257, 105, 47);
		panel_1.add(button_1);

		JButton button_2 = new JButton("\uACE0\uAC1D\uC0AD\uC81C");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String number = textField.getText();
						try {
							if (number.compareTo("") == 0)
								throw new NotMemberException("고객번호를 검색해야합니다.");

							if (clientManager.searchClient(number) != null) {
								clientManager.deleteClient(number);
								JOptionPane.showMessageDialog(null, "고객정보가 삭제되었습니다.");
								textField.setText("");
								textField_1.setText("");
								textField_2.setText("");
								textField_3.setText("");
								textField_6.setText("");
							} else
								JOptionPane.showMessageDialog(null, "고객을 찾지 못했습니다.");
						} catch (NotMemberException e) {
							System.out.println(e);
						}

					}
				});

				thread.start();

			}
		});
		button_2.setBounds(387, 257, 105, 47);
		panel_1.add(button_2);
	}
}
