import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

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
	private JLabel populationMean;
	private JLabel populationVariance;
	private JLabel meanMean;
	private JLabel meanVariance;
	private JSlider sliderPopulation;
	private JSlider sliderSample;
	private JComboBox<String> distributionType;
	private DefaultTableModel populationDistributionModel;
	private DefaultTableModel samplingDistributionModel;
	private DefaultTableModel samplesAndMeanModel;

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

		scrPopGraph = new JScrollPane();
		scrPopGraph.setBounds(30, 30, 750, 260);
		getContentPane().add(scrPopGraph);

		sliderPopulation = new JSlider(JSlider.HORIZONTAL, 1, 1000, 100);
		sliderPopulation.setBounds(60, 300, 650, 30);
		sliderPopulation.setName("sliderPopulation");

		getContentPane().add(sliderPopulation);

		JLabel lblPopulation = new JLabel("N");
		lblPopulation.setFont(new Font("Rockwell", Font.PLAIN, 24));
		lblPopulation.setBounds(30, 300, 20, 30);
		getContentPane().add(lblPopulation);

		txtPopSize = new JTextField(Integer.toString(sliderPopulation
				.getValue()));
		txtPopSize.setFont(new Font("Rockwell", Font.PLAIN, 20));
		txtPopSize.setBounds(720, 300, 60, 30);

		getContentPane().add(txtPopSize);
		txtPopSize.setColumns(10);

		JLabel lblSample = new JLabel("n");
		lblSample.setFont(new Font("Rockwell", Font.PLAIN, 24));
		lblSample.setBounds(30, 340, 20, 30);
		getContentPane().add(lblSample);

		sliderSample = new JSlider(JSlider.HORIZONTAL, 1,
				Integer.parseInt(txtPopSize.getText()), 5);
		sliderSample.setBounds(60, 340, 650, 30);
		sliderSample.setName("sliderSample");

		getContentPane().add(sliderSample);

		txtSamSize = new JTextField(Integer.toString(sliderSample.getValue()));
		txtSamSize.setFont(new Font("Rockwell", Font.PLAIN, 20));
		txtSamSize.setColumns(10);
		txtSamSize.setBounds(720, 340, 60, 30);

		getContentPane().add(txtSamSize);

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

		scrSamGraph = new JScrollPane();
		scrSamGraph.setBounds(30, 380, 750, 260);
		getContentPane().add(scrSamGraph);

		scrPopDist = new JScrollPane();
		scrPopDist.setBounds(810, 30, 210, 260);
		getContentPane().add(scrPopDist);

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

		String[] distributionTableColumn = { "x", "f(x)" };
		tblPop = new JTable();
		populationDistributionModel = new DefaultTableModel();
		tblPop.setModel(populationDistributionModel);
		populationDistributionModel
				.setColumnIdentifiers(distributionTableColumn);

		scrPopDist.setViewportView(tblPop);

		populationDistributionDataset = new DefaultCategoryDataset();
		ChartPanel popChartPanel = new ChartPanel(createChart(
				populationDistributionDataset, "Population Distribution"));
		scrPopGraph.setViewportView(popChartPanel);

		scrSamDist = new JScrollPane();
		scrSamDist.setBounds(810, 380, 210, 260);
		getContentPane().add(scrSamDist);

		samplingDistributionDataset = new DefaultCategoryDataset();
		ChartPanel samChartPanel = new ChartPanel(createChart(
				samplingDistributionDataset, "Sampling Distribution"));
		scrSamGraph.setViewportView(samChartPanel);

		tblSam = new JTable();
		samplingDistributionModel = new DefaultTableModel();

		tblSam.setModel(samplingDistributionModel);
		samplingDistributionModel.setColumnIdentifiers(distributionTableColumn);
		scrSamDist.setViewportView(tblSam);

		scrSamMean = new JScrollPane();
		scrSamMean.setBounds(1050, 380, 210, 260);
		getContentPane().add(scrSamMean);

		tblSamMean = new JTable();
		samplesAndMeanModel = new DefaultTableModel();
		tblSamMean.setModel(samplesAndMeanModel);
		String[] samplesAndMeanColumn = { "Samples", "Mean" };
		samplesAndMeanModel.setColumnIdentifiers(samplesAndMeanColumn);

		tblSamMean.getColumnModel().getColumn(1).setResizable(false);
		scrSamMean.setViewportView(tblSamMean);

		JLabel lblLowerbound = new JLabel("Lowerbound");
		lblLowerbound.setFont(new Font("Rockwell", Font.PLAIN, 24));
		lblLowerbound.setBounds(1032, 82, 143, 30);
		getContentPane().add(lblLowerbound);

		JLabel lblUpperbound = new JLabel("Upperbound");
		lblUpperbound.setFont(new Font("Rockwell", Font.PLAIN, 24));
		lblUpperbound.setBounds(1032, 122, 143, 30);
		getContentPane().add(lblUpperbound);

		populationVariance = new JLabel("Variance");
		populationVariance.setFont(new Font("Rockwell", Font.PLAIN, 16));
		populationVariance.setBounds(810, 314, 210, 16);
		getContentPane().add(populationVariance);

		populationMean = new JLabel("Mean");
		populationMean.setFont(new Font("Rockwell", Font.PLAIN, 16));
		populationMean.setBounds(810, 297, 210, 16);
		getContentPane().add(populationMean);

		meanMean = new JLabel("Mean");
		meanMean.setFont(new Font("Rockwell", Font.PLAIN, 16));
		meanMean.setBounds(810, 337, 210, 16);
		getContentPane().add(meanMean);

		meanVariance = new JLabel("Variance");
		meanVariance.setFont(new Font("Rockwell", Font.PLAIN, 16));
		meanVariance.setBounds(810, 354, 210, 16);
		getContentPane().add(meanVariance);
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
		return populationMean;
	}

	public void setPopulationMean(JLabel populationMean) {
		this.populationMean = populationMean;
	}

	public JLabel getPopulationVariance() {
		return populationVariance;
	}

	public void setPopulationVariance(JLabel populationVariance) {
		this.populationVariance = populationVariance;
	}

	public JLabel getMeanMean() {
		return meanMean;
	}

	public void setMeanMean(JLabel meanMean) {
		this.meanMean = meanMean;
	}

	public JLabel getMeanVariance() {
		return meanVariance;
	}

	public void setMeanVariance(JLabel meanVariance) {
		this.meanVariance = meanVariance;
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

}
