package com.gw.tool.fixtip;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WycReportJPanel extends JPanel {

    private JButton executeBtn = new JButton("执行");
    private JTextArea textMsg = new JTextArea(2,5);

    public WycReportJPanel() {
        super(new BorderLayout());
        initGUI();
        initListeners();
    }

    private void initGUI() {
        textMsg.setText("");

        executeBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
        executeBtn.setForeground(Color.white);

        JPanel are = new JPanel(new BorderLayout());
        JScrollPane pane1 =new JScrollPane(textMsg);
        textMsg.setLineWrap(true);
        are.add(pane1, BorderLayout.CENTER);

        // init btn pane
        JPanel btnPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPane.add(executeBtn);
        // init main ui
        this.add(btnPane, BorderLayout.SOUTH);
        this.add(are, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    private void initListeners() {
        executeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    String path = this.getClass().getResource("/").getPath();

                } catch (Exception ex) {

                }
            }
        });
    }

}
