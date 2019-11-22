/*
 * Copyright (C) 2015 Jack Jiang(cngeeker.com) The Swing9patch Project. 
 * All rights reserved.
 * Project URL:https://github.com/JackJiang2011/Swing9patch
 * Version 1.0
 * 
 * Jack Jiang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 * Demo.java at 2015-2-6 16:10:05, original version by Jack Jiang.
 * You can contact author with jb2011@163.com.
 */
package com.gw.tool.fixtip;

import com.gw.tool.utils.DesUtils;
import com.gw.tool.utils.StringUtils;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

public class Demo extends JPanel
{
	private JTextArea txtMsg = new JTextArea(5,5);
	private JTextArea txtNewMsg = new JTextArea(5,5);

	private JButton aesDeBtn = new JButton("AES解密");
	private JButton aesEnBtn = new JButton("AES加密");
	private JButton base64enBtn = new JButton("BASE64编码");
	private JButton base64deBtn = new JButton("BASE64解码");
	private JButton urlEnBtn = new JButton("URLEncode");
	private JButton urlDeBtn = new JButton("URLDecode");

	private static final String KEY = "746c5aec287e9c5c964ac5e771483066";
	private FixtipPane fixtipPane = new FixtipPane();
	private FloatableDialog fixtipDialog = null;
	
	public Demo()
	{
		super(new BorderLayout());
		initGUI();
		initListeners();
	}
	
	private void initGUI()
	{
		// init sub coms
		txtMsg.setText("");
		txtNewMsg.setText("");

		aesDeBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		aesDeBtn.setForeground(Color.white);
		aesEnBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		aesEnBtn.setForeground(Color.white);
		base64enBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		base64enBtn.setForeground(Color.white);
		base64deBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		base64deBtn.setForeground(Color.white);
		urlEnBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		urlEnBtn.setForeground(Color.white);
		urlDeBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		urlDeBtn.setForeground(Color.white);

		// init btn pane
		JPanel btnPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPane.add(aesDeBtn);
		btnPane.add(aesEnBtn);
		btnPane.add(base64enBtn);
		btnPane.add(base64deBtn);
		btnPane.add(urlEnBtn);
		btnPane.add(urlDeBtn);


		// init
		JPanel are = new JPanel(new BorderLayout());
		JScrollPane pane1 =new JScrollPane(txtMsg);
		JScrollPane pane2 =new JScrollPane(txtNewMsg);
		txtNewMsg.setLineWrap(true);
		txtMsg.setLineWrap(true);
		are.add(pane1,BorderLayout.NORTH);
		are.add(pane2,BorderLayout.CENTER);

		// init main ui
		this.add(btnPane, BorderLayout.SOUTH);
		this.add(are, BorderLayout.CENTER);
		this.add(new JLabel("请填写需要解密的密文： "), BorderLayout.NORTH);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	
	private void initListeners()
	{
		aesDeBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					String mStr = txtMsg.getText();
					if(StringUtils.isNotBlank(mStr)){
						String value = DesUtils.deCodeByAES(mStr, KEY);
						txtNewMsg.setText(value);
					}else{
						txtNewMsg.setText("error：原始输入密文为空 ！！！");
					}
				} catch (Exception ex) {
					txtNewMsg.setText(ex.getMessage());
				}
			}
		});
		aesEnBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					String mStr = txtMsg.getText();
					if(StringUtils.isNotBlank(mStr)){
						String value = DesUtils.enCodeByAES(mStr, KEY);
						txtNewMsg.setText(value);
					}else{
						txtNewMsg.setText("error：原始输入密文为空 ！！！");
					}
				} catch (Exception ex) {
					txtNewMsg.setText(ex.getMessage());
				}
			}
		});
		base64enBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					String mStr = txtMsg.getText();
					if(StringUtils.isNotBlank(mStr)){
						String value = Base64.getEncoder().encodeToString(mStr.getBytes());
						txtNewMsg.setText(value);
					}else{
						txtNewMsg.setText("error：原始输入密文为空 ！！！");
					}
				} catch (Exception ex) {
					txtNewMsg.setText(ex.getMessage());
				}
			}
		});
		base64deBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					String mStr = txtMsg.getText();
					if(StringUtils.isNotBlank(mStr)){
						String value = new String(Base64.getDecoder().decode(mStr), "utf-8");
						txtNewMsg.setText(value);
					}else{
						txtNewMsg.setText("error：原始输入密文为空 ！！！");
					}
				} catch (Exception ex) {
					txtNewMsg.setText(ex.getMessage());
				}
			}
		});
		urlEnBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					String mStr = txtMsg.getText();
					if(StringUtils.isNotBlank(mStr)){
						String value = URLEncoder.encode(mStr, "utf-8");
						txtNewMsg.setText(value);
					}else{
						txtNewMsg.setText("error：原始输入密文为空 ！！！");
					}
				} catch (Exception ex) {
					txtNewMsg.setText(ex.getMessage());
				}
			}
		});
		urlDeBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					String mStr = txtMsg.getText();
					if(StringUtils.isNotBlank(mStr)){
						String value = URLDecoder.decode(mStr, "UTF-8");
						txtNewMsg.setText(value);
					}else{
						txtNewMsg.setText("error：原始输入密文为空 ！！！");
					}
				} catch (Exception ex) {
					txtNewMsg.setText(ex.getMessage());
				}
			}
		});
	}
}
