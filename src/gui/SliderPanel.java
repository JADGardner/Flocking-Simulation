package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;



public class SliderPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static JFrame frame = new JFrame();
	static JPanel sidePanel = new JPanel();
	static JPanel lowerPanel = new JPanel();
	static BoxLayout sideLayout;
	
	
	private JSlider slider;
	private JCheckBox functionSwitch;
	private ImageIcon toggleOnIcon;
	private ImageIcon toggleOffIcon;
	
	public SliderPanel(String name, int min, int max){
		
		setBackground(Color.white);
		//setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.black));
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
							 BorderFactory.createMatteBorder(10, 10, 10, 10, Color.white), name);
		titleBorder.setTitleJustification(TitledBorder.LEFT);
		titleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 30));
		setBorder(titleBorder);
		
		slider = new JSlider(min, max);
		slider.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		slider.setMinorTickSpacing(max/10);
		slider.setMajorTickSpacing(max/2);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setOpaque(false);
		slider.setMinimum(min);
		slider.setMaximum(max);
		//slider.setForeground(Color.black);
		//slider.setSize(300, 110);
		
		toggleOnIcon = new ImageIcon("toggle_on_side.png");
		toggleOffIcon = new ImageIcon("toggle_off_side.png");
		toggleOnIcon = scaleImageIcon(toggleOnIcon);
		toggleOffIcon = scaleImageIcon(toggleOffIcon);
		
		functionSwitch = new JCheckBox();
		functionSwitch.setSelected(true);
		functionSwitch.setSelectedIcon(toggleOnIcon);
		functionSwitch.setIcon(toggleOffIcon);
		functionSwitch.setOpaque(false);

		add(slider, BorderLayout.WEST);
		add(functionSwitch, BorderLayout.EAST);

		//setBorder(BorderFactory.createLineBorder(new EmptyBorder(10, 10, 10, 10), Color.black));
		setMinimumSize(new Dimension(350, 110));
		setMaximumSize(new Dimension(350, 110));

	}
	
	private ImageIcon scaleImageIcon(ImageIcon i) {
		Image image = i.getImage(); // transform it 
		Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		i = new ImageIcon(newimg);  // transform it back
		return i;
	}
	
	
	public static void main(String[] args) {
		frame = new JFrame();
		frame.setTitle("SidePanelTest");
		
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (0.5*screenDimension.getWidth());
		int height = width;
		
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		ImageIcon menuIcon = new ImageIcon("menu.png");
		menuIcon = scaleImageIconStatic(menuIcon, 70, 53);
		JLabel menuLabel = new JLabel(menuIcon);		
		
		
		sidePanel.setPreferredSize(new Dimension((int) (0.374*width), height));
		sidePanel.setBackground(Color.white);
		sideLayout = new BoxLayout(sidePanel, BoxLayout.Y_AXIS);
		sidePanel.setLayout(sideLayout);
		
		lowerPanel.setPreferredSize(new Dimension(width, (int) (0.0654*height)));
		lowerPanel.setBackground(Color.white);

		
		frame.add(sidePanel, BorderLayout.EAST);
		frame.add(lowerPanel, BorderLayout.SOUTH);


		SliderPanel sliderPanelone = new SliderPanel("Cohesion", 0, 1000);
		SliderPanel sliderPaneltwo = new SliderPanel("Seperation", 0, 1000);
		SliderPanel sliderPanelthree = new SliderPanel("Alignment", 0, 1000);
		SliderPanel sliderPanelfour = new SliderPanel("Mouse Avoidance", 0, 1000);
		sidePanel.add(menuLabel, BorderLayout.EAST);
		sidePanel.add(sliderPanelone);
		sidePanel.add(sliderPaneltwo);
		sidePanel.add(sliderPanelthree);
		sidePanel.add(sliderPanelfour);
		
		frame.revalidate();
		
	}
	
	private static ImageIcon scaleImageIconStatic(ImageIcon i, int scaleX, int scaleY) {
		Image image = i.getImage(); // transform it 
		Image newimg = image.getScaledInstance(scaleX, scaleY,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		i = new ImageIcon(newimg);  // transform it back
		return i;
	}
	
}
