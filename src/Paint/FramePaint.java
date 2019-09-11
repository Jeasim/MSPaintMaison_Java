package Paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class FramePaint extends JFrame {
	private JPanel panelPrincipal;
	private JToggleButton tglbtnCrayon;
	private JToggleButton tglbtnEfface;
	private JToggleButton tglbtnPipette;
	private JToggleButton tglbtnPeinture;

	private JButton btnSauvegarde;
	private JButton btnRetour;

	private Surface surface;

	private JLabel lblTailleTrait;

	private JLabel lblCouleur1;
	private JLabel lblCouleur2;

	private JTextField champTailleTrait;
	private JPanel panelCouleurs;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnTriangle;
	private JToggleButton tglbtnCercle;
	private JToggleButton tglbtnCouleur1;
	private JToggleButton tglbtnCouleur2;
	private JPanel panelNoir;
	private JPanel panelRouge;
	private JPanel panelOrange;
	private JPanel panelJaune;
	private JPanel panelVert;
	private JPanel panelGris;
	private JPanel panelBlanc;
	private JPanel panelBleu;
	private JPanel panelCyan;
	private JPanel panelRose;
	private JPanel panelMagenta;
	private JPanel panel_11;

	private JLabel lblPosY;
	private JLabel lblPosX;

	private Color couleurChoisie;
	private Color couleurBackground;
	private int tailleTrait;

	private Vector<BufferedImage> images;

	private Vector<Object> ensembleObjets;
	private Vector<Crayon> crayons;
	private Vector<Efface> effaces;
	private Vector<Pipette> pipettes;
	private Vector<OutilsRectangle> rectangles;
	private Vector<OutilsCercle> cercles;
	private Vector<OutilsTriangle> triangles;
	private Vector<PotPeinture> potsPeinture;


	private EcouteurCouleur eCouleur;
	private EcouteurSurface eSurface;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePaint frame = new FramePaint();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePaint() {

		//Vecteur de crayons pour contenir tous les traits  
		crayons= new Vector<Crayon>();
		effaces = new Vector<Efface>();
		pipettes = new Vector<Pipette>();
		potsPeinture = new Vector<PotPeinture>();
		rectangles = new Vector<OutilsRectangle>();
		cercles = new Vector<OutilsCercle>();
		triangles = new Vector<OutilsTriangle>();
		images = new Vector<BufferedImage>();

		ensembleObjets = new Vector();

		couleurBackground = Color.white;

		setTitle("Paint \u00E0 Jean-Simon");
		setBounds(100, 100, 950, 644);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(6, 6, 934, 118);
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);

		surface = new Surface();
		getContentPane().add(surface);
		surface.setBounds(10, 120, 914, 415);


		//Bouttons outils de création
		tglbtnCrayon = new JToggleButton("");
		tglbtnCrayon.setBounds(80, 6, 62, 35);
		tglbtnCrayon.setIcon(new ImageIcon("icones/crayon.gif"));
		panelPrincipal.add(tglbtnCrayon);

		tglbtnEfface = new JToggleButton("");
		tglbtnEfface.setBounds(6, 6, 62, 35);
		tglbtnEfface.setIcon(new ImageIcon("icones/efface.gif"));
		panelPrincipal.add(tglbtnEfface);

		tglbtnPipette = new JToggleButton("");
		tglbtnPipette.setBounds(154, 6, 62, 35);
		tglbtnPipette.setIcon(new ImageIcon("icones/pipette.gif"));
		panelPrincipal.add(tglbtnPipette);

		tglbtnPeinture = new JToggleButton("");
		tglbtnPeinture.setBounds(228, 6, 62, 35);
		tglbtnPeinture.setIcon(new ImageIcon("icones/remplissage.gif"));
		panelPrincipal.add(tglbtnPeinture);

		btnSauvegarde = new JButton("");
		btnSauvegarde.setBounds(324, 6, 48, 35);
		btnSauvegarde.setIcon(new ImageIcon("icones/save.gif"));
		panelPrincipal.add(btnSauvegarde);

		btnRetour = new JButton("");
		btnRetour.setBounds(376, 6, 48, 35);
		btnRetour.setIcon(new ImageIcon("icones/undo.gif"));
		panelPrincipal.add(btnRetour);

		//Bouttons formes
		tglbtnRectangle = new JToggleButton("");
		tglbtnRectangle.setBounds(242, 63, 48, 35);
		tglbtnRectangle.setIcon(new ImageIcon("icones/rectangle.gif"));
		panelPrincipal.add(tglbtnRectangle);

		tglbtnTriangle = new JToggleButton("");
		tglbtnTriangle.setBounds(360, 63, 48, 35);
		tglbtnTriangle.setIcon(new ImageIcon("icones/triangle.gif"));
		panelPrincipal.add(tglbtnTriangle);

		tglbtnCercle = new JToggleButton("");
		tglbtnCercle.setBounds(302, 63, 48, 35);
		tglbtnCercle.setIcon(new ImageIcon("icones/cercle.gif"));
		panelPrincipal.add(tglbtnCercle);


		//Création d'un groupe de boutons pour les outils de création et formes
		ButtonGroup groupeOutilsEtFormes = new ButtonGroup();
		groupeOutilsEtFormes.add(tglbtnCrayon);
		groupeOutilsEtFormes.add(tglbtnEfface);
		groupeOutilsEtFormes.add(tglbtnPipette);
		groupeOutilsEtFormes.add(tglbtnPeinture);
		groupeOutilsEtFormes.add(tglbtnRectangle);
		groupeOutilsEtFormes.add(tglbtnCercle);
		groupeOutilsEtFormes.add(tglbtnTriangle);

		lblTailleTrait = new JLabel("Taille du trait:");
		lblTailleTrait.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTailleTrait.setBounds(31, 57, 118, 41);
		panelPrincipal.add(lblTailleTrait);

		champTailleTrait = new JTextField();
		champTailleTrait.setHorizontalAlignment(SwingConstants.CENTER);
		champTailleTrait.setText("3");
		champTailleTrait.setBounds(124, 63, 48, 26);
		panelPrincipal.add(champTailleTrait);
		champTailleTrait.setColumns(10);

		//Panel avec le choix de couleurs
		panelCouleurs = new JPanel();
		panelCouleurs.setOpaque(false);
		panelCouleurs.setBorder(null);
		panelCouleurs.setBackground(new Color(192, 192, 192));
		panelCouleurs.setPreferredSize(new Dimension(0, 0));
		panelCouleurs.setBounds(632, 6, 265, 89);
		panelPrincipal.add(panelCouleurs);
		panelCouleurs.setLayout(new GridLayout(2, 5, 5, 5));


		panelNoir = new JPanel();
		panelNoir.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelNoir.setBackground(Color.BLACK);
		panelCouleurs.add(panelNoir);

		panelRouge = new JPanel();
		panelRouge.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelRouge.setBackground(Color.RED);
		panelCouleurs.add(panelRouge);

		panelOrange = new JPanel();
		panelOrange.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelOrange.setBackground(Color.ORANGE);
		panelCouleurs.add(panelOrange);

		panelJaune = new JPanel();
		panelJaune.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelJaune.setBackground(Color.YELLOW);
		panelCouleurs.add(panelJaune);

		panelVert = new JPanel();
		panelVert.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelVert.setBackground(Color.GREEN);
		panelCouleurs.add(panelVert);

		panelBlanc = new JPanel();
		panelBlanc.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelBlanc.setBackground(Color.WHITE);
		panelCouleurs.add(panelBlanc);

		panelGris = new JPanel();
		panelGris.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelGris.setBackground(Color.GRAY);
		panelCouleurs.add(panelGris);

		panelBleu = new JPanel();
		panelBleu.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelBleu.setBackground(Color.BLUE);
		panelCouleurs.add(panelBleu);

		panelCyan = new JPanel();
		panelCyan.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelCyan.setBackground(Color.CYAN);
		panelCouleurs.add(panelCyan);

		panelRose = new JPanel();
		panelRose.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelRose.setBackground(Color.PINK);
		panelCouleurs.add(panelRose);

		panelMagenta = new JPanel();
		panelMagenta.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelMagenta.setBackground(Color.MAGENTA);
		panelCouleurs.add(panelMagenta);

		panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelCouleurs.add(panel_11);



		//Boutton de Couleurs 1 et 2		
		tglbtnCouleur1 = new JToggleButton("Couleur1");
		tglbtnCouleur1.setVerticalAlignment(SwingConstants.BOTTOM);
		tglbtnCouleur1.setBounds(436, 20, 89, 75);
		panelPrincipal.add(tglbtnCouleur1);

		tglbtnCouleur2 = new JToggleButton("Couleur2");
		tglbtnCouleur2.setVerticalAlignment(SwingConstants.BOTTOM);
		tglbtnCouleur2.setBounds(526, 20, 94, 75);
		panelPrincipal.add(tglbtnCouleur2);

		FlowLayout fl_c1 = new FlowLayout();
		tglbtnCouleur1.setLayout(fl_c1);
		FlowLayout fl_c2 = new FlowLayout();
		tglbtnCouleur2.setLayout(fl_c2);

		lblCouleur1 = new JLabel("");
		lblCouleur1.setOpaque(true);
		lblCouleur1.setPreferredSize(new Dimension(50, 45));
		lblCouleur1.setBackground(Color.ORANGE);
		lblCouleur1.setBounds(86, 56, 99, 74);
		tglbtnCouleur1.add(lblCouleur1);

		lblCouleur2 = new JLabel("");
		lblCouleur2.setOpaque(true);
		lblCouleur2.setPreferredSize(new Dimension(50, 45));
		lblCouleur2.setBackground(Color.black);
		lblCouleur2.setBounds(86, 56, 99, 74);
		tglbtnCouleur2.add(lblCouleur2);

		ButtonGroup groupe2Couleurs = new ButtonGroup();
		groupe2Couleurs.add(tglbtnCouleur1);
		groupe2Couleurs.add(tglbtnCouleur2); 

		lblPosY = new JLabel("");
		lblPosY.setBounds(853, 572, 69, 20);
		getContentPane().add(lblPosY);

		lblPosX = new JLabel("");
		lblPosX.setBounds(806, 572, 69, 20);
		getContentPane().add(lblPosX);


		//Gestion d'événements		
		eCouleur = new EcouteurCouleur();

		Component [] labelsCouleur = panelCouleurs.getComponents();
		for(Component label : labelsCouleur)
			label.addMouseListener(eCouleur);

		eSurface = new EcouteurSurface();
		surface.addMouseMotionListener(eSurface);
		surface.addMouseListener(eSurface);

		//btnSauvegarde.addActionListener(eSurface);
		btnRetour.addActionListener(eSurface);
		btnSauvegarde.addActionListener(eSurface);





	}

	// -------------------------------------- Ecouteurs -------------------------------------------------------------------


	private class EcouteurCouleur implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent eCouleur) {

			//On met la couleur du panel de la couleur que l'usagé veut utilisé dans une variable Color
			Component labelClic = eCouleur.getComponent();
			couleurChoisie = labelClic.getBackground();

			// JColorChooser pour le dernier panel ---------------------- 

			//On applique la couleur choisie sur le panel du toggle button déjà sélectionné
			if(tglbtnCouleur1.isSelected())
				lblCouleur1.setBackground(couleurChoisie);

			else if(tglbtnCouleur2.isSelected())
				lblCouleur2.setBackground(couleurChoisie);

		}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}


	private class EcouteurSurface implements MouseMotionListener, MouseListener, ActionListener
	{

		@Override
		public void mouseDragged(MouseEvent me) {

			if(tglbtnCrayon.isSelected())
			{
				crayons.lastElement().ajouterPointsVecteur(me.getPoint());
				repaint();
			}

			else if(tglbtnEfface.isSelected())
			{
				effaces.lastElement().ajouterPointsVecteur(me.getPoint());
				((Efface)ensembleObjets.lastElement()).ajouterPointsVecteur(me.getPoint());
				repaint();
			}

			else if(tglbtnRectangle.isSelected())
			{
				rectangles.lastElement().tracerRectangle(me.getPoint());
				repaint();
			}

			else if(tglbtnCercle.isSelected())
			{
				cercles.lastElement().tracerCercle(me.getPoint());
				repaint();
			}

			else if(tglbtnTriangle.isSelected())
			{
				triangles.lastElement().tracerTriangle(me.getPoint());
				repaint();
			}


			lblPosX.setText("x : " + me.getX());
			lblPosY.setText("y : " + me.getY());

		}

		@Override
		public void mouseMoved(MouseEvent me) 
		{
			lblPosX.setText("x : " + me.getX());
			lblPosY.setText("y : " + me.getY());
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent me) 
		{
			surface.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent arg0) 
		{
			lblPosX.setText("x : -");
			lblPosY.setText("y : -");
		}

		@Override
		public void mousePressed(MouseEvent me) 
		{
			//Associer la bonne couleur au bon boutton de souris
			if(me.getButton() == MouseEvent.BUTTON1){
				couleurChoisie = lblCouleur1.getBackground();
			}else if(me.getButton() == MouseEvent.BUTTON3){
				couleurChoisie = lblCouleur2.getBackground();
			}

			tailleTrait = Integer.parseInt(champTailleTrait.getText());

			if(tglbtnCrayon.isSelected())
			{
				Crayon crayon = new Crayon(couleurChoisie, tailleTrait);

				crayon.ajouterPointsVecteur(me.getPoint());
				
				crayons.add(crayon);
				ensembleObjets.add(crayon);
				
				repaint();
			}

			else if(tglbtnRectangle.isSelected())
			{
				OutilsRectangle rectangle = new OutilsRectangle(me.getX(), me.getY(), couleurChoisie, tailleTrait);

				rectangles.add(rectangle);
				ensembleObjets.add(rectangle);
			}

			else if(tglbtnCercle.isSelected())
			{
				OutilsCercle cercle = new OutilsCercle(me.getX(), me.getY(), couleurChoisie, tailleTrait);

				cercles.add(cercle);
				ensembleObjets.add(cercle);
			}

			else if(tglbtnTriangle.isSelected())
			{
				OutilsTriangle triangle = new OutilsTriangle(me.getX(), me.getY(), couleurChoisie, tailleTrait);

				triangles.add(triangle);
				ensembleObjets.add(triangle);
			}

			else if(tglbtnEfface.isSelected())
			{
				Efface efface = new Efface(tailleTrait, couleurBackground);
				
				efface.ajouterPointsVecteur(me.getPoint());

				effaces.add(efface);
				ensembleObjets.add(efface);
			}

			else if(tglbtnPeinture.isSelected())
			{
				PotPeinture potPeinture = new PotPeinture(couleurChoisie);

				potsPeinture.add(potPeinture);
				ensembleObjets.add(potPeinture);

				couleurBackground = potsPeinture.lastElement().getCouleur();

				repaint();
			}

			else if(tglbtnPipette.isSelected())
			{

				Pipette pipette = new Pipette(me.getX(), me.getY());
				BufferedImage image = recupererImage(surface);

				images.add(image);
				pipettes.add(pipette);

				tglbtnCrayon.setSelected(true);

				int pixel = pipette.trouverRGBPixel(image);
				couleurChoisie = new Color(pixel);

				if(me.getButton() == MouseEvent.BUTTON1)
					lblCouleur1.setBackground(couleurChoisie);

				else if(me.getButton() == MouseEvent.BUTTON3)
					lblCouleur2.setBackground(couleurChoisie);


				repaint();

			}

		}
		@Override
		public void mouseReleased(MouseEvent me) 
		{

		}

		@Override
		public void actionPerformed(ActionEvent ae) 
		{
			if(ae.getSource() == btnRetour)
			{
				if(ensembleObjets.size() > 0)
				{

					Object o = ensembleObjets.remove(ensembleObjets.size() - 1);

					if(o instanceof Crayon)
					{
						crayons.remove(crayons.size() - 1);
					}

					else if(o instanceof OutilsRectangle)
					{
						rectangles.remove(rectangles.size() - 1);
					}

					else if(o instanceof OutilsCercle)
					{
						cercles.remove(cercles.size() - 1);
					}

					else if(o instanceof OutilsTriangle)
					{
						triangles.remove(triangles.size() - 1);
					}
					else if(o instanceof PotPeinture)
					{
						if(potsPeinture.size() > 1) {
							couleurBackground = potsPeinture.get(potsPeinture.size() - 2).getCouleur();
						}else {
							couleurBackground = Color.WHITE;
						}
						
						potsPeinture.remove(potsPeinture.size() - 1);
					}
					else if(o instanceof Efface)
					{
						effaces.remove(effaces.size() - 1);
					}

					repaint();
				}
			}

			else if(ae.getSource() == btnSauvegarde)
			{
				BufferedImage imageSauvegarde = recupererImage(surface);
				sauvegarderImage(imageSauvegarde);
			}
		}
	}


	//-------------------------------- Surface ---------------------------------------------------------------------------------------

	public class Surface extends JPanel
	{
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


			if(ensembleObjets.size() != 0)
			{

				int indexCrayon = 0;
				int indexRectangle = 0;
				int indexCercle = 0;
				int indexTriangle = 0;
				int indexEfface = 0;


				for(int i = 0; i < ensembleObjets.size(); i++)
				{

					Object o = ensembleObjets.get(i);

					if(o instanceof Crayon)
					{
						Crayon crayon = crayons.get(indexCrayon);
						Vector<Point> points = crayon.getVecteurPoints();

						if(points.size() == 1)
						{
							g2.setStroke(new BasicStroke(crayon.getTailleTrait(), BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
							g.setColor(crayon.getCouleur());
							
							g.drawLine(points.firstElement().x, points.firstElement().y, points.firstElement().x, points.firstElement().y);
						}

						for(int j = 1; j < points.size(); j++)
						{
							Point p1 = points.get(j - 1);
							Point p2 = points.get(j);

							g2.setStroke(new BasicStroke(crayon.getTailleTrait(), BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
							g.setColor(crayon.getCouleur());
							g.drawLine(p1.x, p1.y, p2.x, p2.y);
						}

						indexCrayon++; 
					}

					else if(o instanceof OutilsRectangle)
					{

						OutilsRectangle rectangle = rectangles.get(indexRectangle);

						int x = rectangle.getX();
						int y = rectangle.getY();
						int largeur = rectangle.getLargeur();
						int hauteur = rectangle.getHauteur();

						g2.setStroke(new BasicStroke(rectangle.getTailleTrait()));
						g.setColor(rectangle.getCouleur());
						g.drawRect(x, y, largeur, hauteur);

						indexRectangle++;
					}

					else if(o instanceof OutilsCercle)
					{

						OutilsCercle cercle = cercles.get(indexCercle);

						int x = cercle.getX();
						int y = cercle.getY();
						int largeur = cercle.getLargeur();
						int hauteur = cercle.getHauteur();

						g2.setStroke(new BasicStroke(cercle.getTailleTrait()));
						g.setColor(cercle.getCouleur());
						g.drawOval(x, y, largeur, hauteur);

						indexCercle++;
					}

					else if(o instanceof OutilsTriangle)
					{
						OutilsTriangle triangle = triangles.get(indexTriangle);

						int[] x = triangle.getX();
						int[] y = triangle.getY();

						g2.setStroke(new BasicStroke(triangle.getTailleTrait()));
						g.setColor(triangle.getCouleur());
						g.drawPolygon(x, y, 3);

						indexTriangle++;
					}

					else if(o instanceof Efface)
					{
						Efface efface = effaces.get(indexEfface);

						for(Efface e : effaces)
						{
							e.setCouleur(couleurBackground);
						}

						for(int j = 1; j < efface.getVecteurPoints().size(); j++)
						{
							Point p1 = efface.getVecteurPoints().get(j - 1);
							Point p2 = efface.getVecteurPoints().get(j);


							g2.setStroke(new BasicStroke(efface.getTailleTrait(), BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
							g.setColor(efface.getCouleur());
							g.drawLine(p1.x, p1.y, p2.x, p2.y);
						}

						indexEfface++;
					}

				}
			}

			setBackground(couleurBackground);
		}
	}

	public BufferedImage recupererImage(JPanel surface)
	{
		Dimension size = surface.getSize();
		BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		surface.paint(g2);
		return image;
	}

	public void sauvegarderImage(BufferedImage image) {

		JFileChooser fileChooser = new JFileChooser();		
		int reponseDialogue = fileChooser.showSaveDialog(null);

		if (reponseDialogue == JFileChooser.APPROVE_OPTION) {
			try {
				ImageIO.write(image, "png", new File(".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
