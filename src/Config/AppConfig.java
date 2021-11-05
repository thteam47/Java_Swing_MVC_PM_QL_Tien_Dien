/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

/**
 *
 * @author Admin
 */
public class AppConfig {
        public static void  configTable(JTable jTable)
    {
        
        jTable.setOpaque(true);
        jTable.setFillsViewportHeight(true);
        jTable.setBackground(Color.white);
        jTable.getTableHeader().setOpaque(false);
        jTable.getTableHeader().setFont((new Font("Tahoma", Font.BOLD, 16)));
        jTable.getTableHeader().setBackground(Color.white);
        
    }

}
