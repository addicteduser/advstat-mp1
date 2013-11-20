package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

@SuppressWarnings("serial")
public class View extends JFrame {
	private JTextField txtPopSize;
	private JTextField txtSamSize;
	private JTable tblPop;
	private JTable tblSam;
	private JTable tblSamMean;
	private JTextField txtLowerBound;
	private JTextField txtUpperBound;
	private DefaultCategoryDataset populationDistributionDataset;
	private DefaultCategoryDataset samplingDistributionDataset;
	private JScrollPane scrPopGraph;
	private JScrollPane scrSamGraph;
	private JScrollPane scrPopDist;
	private JScrollPane scrSamDist;
	private JScrollPane scrSamMean;
	private JLabel lblPopMean;
	private JLabel lblPopVariance;
	private JLabel lblMeanMean;
	private JLabel lblMeanVariance;
	private JSlider sliderPopulation;
	private JSlider sliderSample;
	private JComboBox<String> distributionType;
	private DefaultTableModel populationDistributionModel;
	private DefaultTableModel samplingDistributionModel;
	private DefaultTableModel samplesAndMeanModel;
	private JLabel lblPopMeanVal;
	private JPanel pnlPopulation;
	private JLabel lblPopVarianceVal;
	private JPanel pnlSample;
	private JLabel lblMeanMeanVal;
	private JLabel lblMeanVarrianceVal;

	public View() {
		setResizable(false);
		initComponents();
		repaint();
		validate();
	}

	public void initComponents() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(1300, 700));
		this.setVisible(true);
		this.setTitle("ADVSTAT - Sampling Distribution Simulation");
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		/* POPULATION */
		pnlPopulation = new JPanel();
		pnlPopulation.setBorder(new TitledBorder(new LineBorder(null, 1, true),
				"Population Distribution", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlPopulation.setBounds(12, 0, 1010, 330);
		pnlPopulation.setLayout(null);
		getContentPane().add(pnlPopulation);

		scrPopGraph = new JScrollPane();
		scrPopGraph.setBounds(10, 18, 750, 260);
		pnlPopulation.add(scrPopGraph);

		JLabel lblPopulation = new JLabel("N");
		lblPopulation.setFont(new Font("Rockwell", Font.PLAIN, 24));
		lblPopulation.setBounds(10, 288, 20, 30);
		pnlPopulation.add(lblPopulation);

		sliderPopulation = new JSlider(JSlider.HORIZONTAL, 1, 1000, 30);
		sliderPopulation.setBounds(40, 288, 650, 30);
		sliderPopulation.setName("sliderPopulation");
		sliderPopulation.setMinorTickSpacing(1);
		sliderPopulation.setSnapToTicks(true);
		sliderPopulation.setPaintTicks(true);
		pnlPopulation.add(sliderPopulation);

		txtPopSize = new JTextField(Integer.toString(sliderPopulation
				.getValue()));
		txtPopSize.setFont(new Font("Rockwell", Font.PLAIN, 20));
		txtPopSize.setBounds(700, 288, 60, 30);
		txtPopSize.setColumns(10);
		pnlPopulation.add(txtPopSize);

		scrPopDist = new JScrollPane();
		scrPopDist.setBounds(790, 18, 210, 260);
		pnlPopulation.add(scrPopDist);

		lblPopMean = new JLabel("Mean:");
		lblPopMean.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblPopMean.setBounds(790, 285, 70, 16);
		pnlPopulation.add(lblPopMean);

		lblPopMeanVal = new JLabel("<mean>");
		lblPopMeanVal.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblPopMeanVal.setBounds(863, 285, 137, 16);
		pnlPopulation.add(lblPopMeanVal);

		lblPopVariance = new JLabel("Variance:");
		lblPopVariance.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblPopVariance.setBounds(790, 302, 210, 16);
		pnlPopulation.add(lblPopVariance);

		lblPopVarianceVal = new JLabel("<variance>");
		lblPopVarianceVal.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblPopVarianceVal.setBounds(863, 302, 137, 16);
		pnlPopulation.add(lblPopVarianceVal);

		/* SAMPLE */
		pnlSample = new JPanel();
		pnlSample.setBorder(new TitledBorder(new LineBorder(null, 1, true),
				"Sampling Distribution", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlSample.setBounds(12, 330, 1010, 330);
		pnlSample.setLayout(null);
		getContentPane().add(pnlSample);

		JLabel lblSample = new JLabel("n");
		lblSample.setFont(new Font("Rockwell", Font.PLAIN, 24));
		lblSample.setBounds(10, 14, 20, 30);
		pnlSample.add(lblSample);

		sliderSample = new JSlider(JSlider.HORIZONTAL, 1,
				Integer.parseInt(txtPopSize.getText()), 10);
		sliderSample.setBounds(40, 14, 650, 30);
		sliderSample.setName("sliderSample");
		sliderSample.setMinorTickSpacing(1);
		sliderSample.setSnapToTicks(true);
		sliderSample.setPaintTicks(true);
		pnlSample.add(sliderSample);

		txtSamSize = new JTextField(Integer.toString(sliderSample.getValue()));
		txtSamSize.setFont(new Font("Rockwell", Font.PLAIN, 20));
		txtSamSize.setColumns(10);
		txtSamSize.setBounds(700, 14, 60, 30);
		pnlSample.add(txtSamSize);

		scrSamGraph = new JScrollPane();
		scrSamGraph.setBounds(10, 54, 750, 260);
		pnlSample.add(scrSamGraph);

		lblMeanMean = new JLabel("Mean:");
		lblMeanMean.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblMeanMean.setBounds(790, 11, 210, 16);
		pnlSample.add(lblMeanMean);

		lblMeanMeanVal = new JLabel("<mean>");
		lblMeanMeanVal.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblMeanMeanVal.setBounds(863, 11, 137, 16);
		pnlSample.add(lblMeanMeanVal);

		lblMeanVariance = new JLabel("Variance:");
		lblMeanVariance.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblMeanVariance.setBounds(790, 28, 210, 16);
		pnlSample.add(lblMeanVariance);

		lblMeanVarrianceVal = new JLabel("<variance>");
		lblMeanVarrianceVal.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblMeanVarrianceVal.setBounds(863, 28, 137, 16);
		pnlSample.add(lblMeanVarrianceVal);

		scrSamDist = new JScrollPane();
		scrSamDist.setBounds(790, 54, 210, 260);
		pnlSample.add(scrSamDist);

		/* OTHERS */
		distributionType = new JComboBox<String>();
		distributionType.setFont(new Font("Rockwell", Font.PLAIN, 20));
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		comboBoxModel.addElement("Uniform");
		comboBoxModel.addElement("Positively Skewed");
		comboBoxModel.addElement("Negatively Skewed");
		comboBoxModel.addElement("Bimodal");
		comboBoxModel.addElement("Normal");
		comboBoxModel.addElement("Random");
		distributionType.setModel(comboBoxModel);
		distributionType.setBounds(1032, 30, 228, 40);
		getContentPane().add(distributionType);

		JLabel lblLowerbound = new JLabel("Lowerbound");
		lblLowerbound.setFont(new Font("Rockwell", Font.PLAIN, 24));
		lblLowerbound.setBounds(1032, 82, 143, 30);
		getContentPane().add(lblLowerbound);

		txtLowerBound = new JTextField("0");
		txtLowerBound.setFont(new Font("Rockwell", Font.PLAIN, 20));
		txtLowerBound.setColumns(10);
		txtLowerBound.setBounds(1200, 82, 60, 30);
		getContentPane().add(txtLowerBound);

		txtUpperBound = new JTextField("10");
		txtUpperBound.setFont(new Font("Rockwell", Font.PLAIN, 20));
		txtUpperBound.setColumns(10);
		txtUpperBound.setBounds(1200, 122, 60, 30);
		getContentPane().add(txtUpperBound);

		JLabel lblUpperbound = new JLabel("Upperbound");
		lblUpperbound.setFont(new Font("Rockwell", Font.PLAIN, 24));
		lblUpperbound.setBounds(1032, 122, 143, 30);
		getContentPane().add(lblUpperbound);

		/* GRAPHS and TABLES */
		String[] distributionTableColumn = { "x", "f(x)" };

		// POPULATION
		populationDistributionDataset = new DefaultCategoryDataset();
		ChartPanel popChartPanel = new ChartPanel(createChart(
				populationDistributionDataset, "Population Distribution"));
		scrPopGraph.setViewportView(popChartPanel);
		
		tblPop = new JTable();
		populationDistributionModel = new DefaultTableModel();
		populationDistributionModel
				.setColumnIdentifiers(distributionTableColumn);
		tblPop.setModel(populationDistributionModel);
		scrPopDist.setViewportView(tblPop);

		// SAMPLE
		samplingDistributionDataset = new DefaultCategoryDataset();
		ChartPanel samChartPanel = new ChartPanel(createChart(
				samplingDistributionDataset, "Sampling Distribution"));
		scrSamGraph.setViewportView(samChartPanel);
		
		tblSam = new JTable();
		samplingDistributionModel = new DefaultTableModel();
		samplingDistributionModel.setColumnIdentifiers(distributionTableColumn);
		tblSam.setModel(samplingDistributionModel);
		scrSamDist.setViewportView(tblSam);

		/* UNKNOWN :)) */
		scrSamMean = new JScrollPane();
		scrSamMean.setBounds(1042, 163, 228, 497);
		getContentPane().add(scrSamMean);

		tblSamMean = new JTable();
		samplesAndMeanModel = new DefaultTableModel();
		tblSamMean.setModel(samplesAndMeanModel);
		String[] samplesAndMeanColumn = { "Samples", "Mean" };
		samplesAndMeanModel.setColumnIdentifiers(samplesAndMeanColumn);

		tblSamMean.getColumnModel().getColumn(1).setResizable(false);
		scrSamMean.setViewportView(tblSamMean);
	}
	
	public void setPopDistTable(DefaultTableModel model) {
		tblPop = new JTable();
		tblPop.setModel(model);
		scrPopDist.setViewportView(tblPop);
		pnlPopulation.validate();
	}
	
	public void setPopDistChart(DefaultCategoryDataset dataset) {
		ChartPanel popChartPanel = new ChartPanel(createChart(
				dataset, "Population Distribution"));
		scrPopGraph.setViewportView(popChartPanel);
	}

	public JTextField getTxtPopSize() {
		return txtPopSize;
	}

	public void setTxtPopSize(JTextField txtPopSize) {
		this.txtPopSize = txtPopSize;
	}

	public JTextField getTxtSamSize() {
		return txtSamSize;
	}

	public void setTxtSamSize(JTextField txtSamSize) {
		this.txtSamSize = txtSamSize;
	}

	public JTable getTblPop() {
		return tblPop;
	}

	public void setTblPop(JTable tblPop) {
		this.tblPop = tblPop;
	}

	public JTable getTblSam() {
		return tblSam;
	}

	public void setTblSam(JTable tblSam) {
		this.tblSam = tblSam;
	}

	public JTable getTblSamMean() {
		return tblSamMean;
	}

	public void setTblSamMean(JTable tblSamMean) {
		this.tblSamMean = tblSamMean;
	}

	public JTextField getTxtLowerBound() {
		return txtLowerBound;
	}

	public void setTxtLowerBound(JTextField txtLowerBound) {
		this.txtLowerBound = txtLowerBound;
	}

	public JTextField getTxtUpperBound() {
		return txtUpperBound;
	}

	public void setTxtUpperBound(JTextField txtUpperBound) {
		this.txtUpperBound = txtUpperBound;
	}

	public DefaultCategoryDataset getPopulationDistributionDataset() {
		return populationDistributionDataset;
	}

	public void setPopulationDistributionDataset(
			DefaultCategoryDataset populationDistributionDataset) {
		this.populationDistributionDataset = populationDistributionDataset;
	}

	public DefaultCategoryDataset getSamplingDistributionDataset() {
		return samplingDistributionDataset;
	}

	public void setSamplingDistributionDataset(
			DefaultCategoryDataset samplingDistributionDataset) {
		this.samplingDistributionDataset = samplingDistributionDataset;
	}

	public JScrollPane getScrPopGraph() {
		return scrPopGraph;
	}

	public void setScrPopGraph(JScrollPane scrPopGraph) {
		this.scrPopGraph = scrPopGraph;
	}

	public JScrollPane getScrSamGraph() {
		return scrSamGraph;
	}

	public void setScrSamGraph(JScrollPane scrSamGraph) {
		this.scrSamGraph = scrSamGraph;
	}

	public JScrollPane getScrPopDist() {
		return scrPopDist;
	}

	public void setScrPopDist(JScrollPane scrPopDist) {
		this.scrPopDist = scrPopDist;
	}

	public JScrollPane getScrSamDist() {
		return scrSamDist;
	}

	public void setScrSamDist(JScrollPane scrSamDist) {
		this.scrSamDist = scrSamDist;
	}

	public JScrollPane getScrSamMean() {
		return scrSamMean;
	}

	public void setScrSamMean(JScrollPane scrSamMean) {
		this.scrSamMean = scrSamMean;
	}

	public JLabel getPopulationMean() {
		return lblPopMean;
	}

	public void setPopulationMean(JLabel populationMean) {
		this.lblPopMean = populationMean;
	}

	public JLabel getPopulationVariance() {
		return lblPopVariance;
	}

	public void setPopulationVariance(JLabel populationVariance) {
		this.lblPopVariance = populationVariance;
	}

	public JLabel getMeanMean() {
		return lblMeanMean;
	}

	public void setMeanMean(JLabel meanMean) {
		this.lblMeanMean = meanMean;
	}

	public JLabel getMeanVariance() {
		return lblMeanVariance;
	}

	public void setMeanVariance(JLabel meanVariance) {
		this.lblMeanVariance = meanVariance;
	}

	public JSlider getSliderPopulation() {
		return sliderPopulation;
	}

	public void setSliderPopulation(JSlider sliderPopulation) {
		this.sliderPopulation = sliderPopulation;
	}

	public JSlider getSliderSample() {
		return sliderSample;
	}

	public void setSliderSample(JSlider sliderSample) {
		this.sliderSample = sliderSample;
	}

	public JComboBox<String> getDistributionType() {
		return distributionType;
	}

	public void setDistributionType(JComboBox<String> distributionType) {
		this.distributionType = distributionType;
	}

	public DefaultTableModel getPopulationDistributionModel() {
		return populationDistributionModel;
	}

	public void setPopulationDistributionModel(
			DefaultTableModel populationDistributionModel) {
		this.populationDistributionModel = populationDistributionModel;
	}

	public DefaultTableModel getSamplingDistributionModel() {
		return samplingDistributionModel;
	}

	public void setSamplingDistributionModel(
			DefaultTableModel samplingDistributionModel) {
		this.samplingDistributionModel = samplingDistributionModel;
	}

	public DefaultTableModel getSamplesAndMeanModel() {
		return samplesAndMeanModel;
	}

	public void setSamplesAndMeanModel(DefaultTableModel samplesAndMeanModel) {
		this.samplesAndMeanModel = samplesAndMeanModel;
	}

	public int populationSize() {
		return Integer.parseInt(txtPopSize.getText());
	}

	public int sampleSize() {
		return Integer.parseInt(txtSamSize.getText());
	}

	public JFreeChart createChart(CategoryDataset dataset, String title) {
		final JFreeChart chart = ChartFactory.createBarChart(title, // chart
																	// title
				"x", // domain axis label
				"f(x)", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				false, // tooltips?
				false // URLs?
				);

		chart.setBackgroundPaint(Color.lightGray);

		// get a reference to the plot for further customisation...
		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		return chart;
	}

	public void populatePopulationDistributionModel(ArrayList<Integer> x,
			ArrayList<Double> y) {
		int row = populationDistributionModel.getRowCount();
		if (row > 0) {
			for (int i = row - 1; i >= 0; i--) {
				populationDistributionModel.removeRow(i);
			}
		}

		for (int i = 0; i < x.size(); i++) {
			populationDistributionModel.addRow(new Object[] { x.get(i),
					y.get(i) });
		}

	}

	public void populateSamplingDistributionModel(ArrayList<Double> x,
			ArrayList<Double> y) {
		for (int i = samplingDistributionModel.getRowCount() - 1; i >= 0; i--) {
			samplingDistributionModel.removeRow(i);
		}

		for (int i = 0; i < x.size(); i++) {
			samplingDistributionModel
					.addRow(new Object[] { x.get(i), y.get(i) });
		}

	}

	public void populateSamplesAndMeanModel(ArrayList<ArrayList<Integer>> x,
			ArrayList<Double> y) {
		for (int i = samplesAndMeanModel.getRowCount() - 1; i >= 0; i--) {
			samplesAndMeanModel.removeRow(i);
		}

		for (int i = 0; i < x.size(); i++) {
			samplesAndMeanModel.addRow(new Object[] { x.get(i), y.get(i) });
		}

	}

	public int getLowerBound() {
		int lb = 0;
		try {
			lb = Integer.parseInt(txtLowerBound.getText());
		} catch (Exception e) {
		}
		return lb;
	}

	public int getUpperBound() {
		int ub = 0;
		try {
			ub = Integer.parseInt(txtUpperBound.getText());
		} catch (Exception e) {
		}
		return ub;
	}

	public void populatePopTable() {

	}

	public JLabel getLblPopMean() {
		return lblPopMean;
	}

	public void setLblPopMean(JLabel lblPopMean) {
		this.lblPopMean = lblPopMean;
	}

	public JLabel getLblPopVariance() {
		return lblPopVariance;
	}

	public void setLblPopVariance(JLabel lblPopVariance) {
		this.lblPopVariance = lblPopVariance;
	}

	public JLabel getLblMeanMean() {
		return lblMeanMean;
	}

	public void setLblMeanMean(JLabel lblMeanMean) {
		this.lblMeanMean = lblMeanMean;
	}

	public JLabel getLblMeanVariance() {
		return lblMeanVariance;
	}

	public void setLblMeanVariance(JLabel lblMeanVariance) {
		this.lblMeanVariance = lblMeanVariance;
	}

	public JLabel getLblPopMeanVal() {
		return lblPopMeanVal;
	}

	public void setLblPopMeanVal(JLabel lblPopMeanVal) {
		this.lblPopMeanVal = lblPopMeanVal;
	}

	public JPanel getPnlPopulation() {
		return pnlPopulation;
	}

	public void setPnlPopulation(JPanel pnlPopulation) {
		this.pnlPopulation = pnlPopulation;
	}

	public JLabel getLblPopVarianceVal() {
		return lblPopVarianceVal;
	}

	public void setLblPopVarianceVal(JLabel lblPopVarianceVal) {
		this.lblPopVarianceVal = lblPopVarianceVal;
	}

	public JPanel getPnlSample() {
		return pnlSample;
	}

	public void setPnlSample(JPanel pnlSample) {
		this.pnlSample = pnlSample;
	}

	public JLabel getLblMeanMeanVal() {
		return lblMeanMeanVal;
	}

	public void setLblMeanMeanVal(JLabel lblMeanMeanVal) {
		this.lblMeanMeanVal = lblMeanMeanVal;
	}

	public JLabel getLblMeanVarrianceVal() {
		return lblMeanVarrianceVal;
	}

	public void setLblMeanVarrianceVal(JLabel lblMeanVarrianceVal) {
		this.lblMeanVarrianceVal = lblMeanVarrianceVal;
	}
}
