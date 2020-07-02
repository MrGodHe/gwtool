
package com.gw.tool;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
	private JPanel mainPane = new JPanel(new BorderLayout());
	
	public MainFrame(String path) throws HeadlessException 
	{
		super ("网关小工具");
		initGUI();
		setSize(900, 680);
	}
	
	private void initGUI()
	{
		this.mainPane.add(createMainTabs(), BorderLayout.CENTER);
		this.getContentPane().add(mainPane);
		/*菜单栏*/
//		this.setJMenuBar(createMenuBar());
	}

	/**
	 * 面板组件
	 * @return
	 */
	private JComponent createMainTabs()
	{
		JTabbedPane tbs = new JTabbedPane();
		tbs.add(new com.gw.tool.fixtip.Demo(), "报文解密");
		tbs.add(new com.gw.tool.fixtip.WycReportJPanel(), "网约车对账单解析");
//		tbs.add(new com.gw.tool.popup.Demo(), "Cool tooltip");
//		tbs.add(new com.gw.tool.photoframe.Demo(), "Photo frame");
//		tbs.add(new JPanel(), "Cool border demo");
//		tbs.add(new JPanel(), "仿手机短信内容查看");
//		tbs.add(new JPanel(), "Cool 名片");
//		tbs.add(new org.jb2011.swing9patch.toast.Demo(), "Toast");
		
//		tbs.setToolTipTextAt(0, "Cool tooltip");
//		tbs.setToolTipTextAt(1, "Cool fix tip");
//		tbs.setToolTipTextAt(2, "Photo frame");
//		tbs.setToolTipTextAt(3, "Cool border demo");
//		tbs.setToolTipTextAt(4, "仿手机短信内容查看");
//		tbs.setToolTipTextAt(5, "Cool 名片");
//		tbs.setToolTipTextAt(3, "Toast");
		
		return tbs;
	}


	/**
	 * 菜单
	 * @return
	 */
	private JMenuBar createMenuBar() 
	{
		//------------------------------------ MenuDemo1
//		JMenu fileMenu = new JMenu("MenuDemo1");
//		JMenuItem openMenuItem = new JMenuItem("Menu item 1");
//		JMenuItem saveMenuItem = new JMenuItem("Menu item 2");
//		JMenuItem exitMenuItem = new JMenuItem("Menu item 3");
//		fileMenu.add(openMenuItem);
//		saveMenuItem.setEnabled(false);
//		fileMenu.add(saveMenuItem);
//		fileMenu.addSeparator();
//		fileMenu.add(exitMenuItem);
//
		//------------------------------------ MenuDemo2
//		JMenu fileMenu2 = new JMenu("MenuDemo2");
//		fileMenu2.add(new JMenuItem("Menu item 1"));
//		fileMenu2.add(new JMenuItem("Menu item 2"));
//		fileMenu2.addSeparator();
//		fileMenu2.add(new JMenuItem("Menu item 3"));
//		fileMenu2.add(new JMenuItem("Menu item 4"));
//		fileMenu2.addSeparator();
//		fileMenu2.add(new JMenuItem("Menu item 5"));
//		fileMenu2.add(new JMenuItem("Menu item 6"));
//		fileMenu2.add(new JMenuItem("Menu item 7"));
//		fileMenu2.add(new JMenuItem("Menu item 8"));
		
		//------------------------------------ About
//		JMenu aboutMenu = new JMenu("关于");
//		JMenuItem aboutMenuItem = new JMenuItem("关于本工程");
//		aboutMenuItem.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e)
//			{
//				JOptionPane.showMessageDialog(rootPane, "");
//			}
//		});
//		aboutMenu.add(aboutMenuItem);
//
		JMenuBar menuBar = new JMenuBar();
//		menuBar.add(fileMenu);
//		menuBar.add(fileMenu2);
//		menuBar.add(aboutMenu);
		
		return menuBar;
	}
	

}
