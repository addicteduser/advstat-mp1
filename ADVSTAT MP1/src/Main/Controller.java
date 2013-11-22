package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller {
	private View myView;
	private Model myModel;
	
	public Controller(View view, Model model) {
		this.myView = view;
		this.myModel = model;
		
		setListeners();
		refreshAll();
	}
	
	public void setListeners() {
		// DISTIBUTION TYPE
		myView.getDistributionType().addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				int value = myView.getDistributionType().getSelectedIndex();
				myModel.setDistType(value);
				
				
				
				
				// CODE TO COMPUTE POP DIST
				refreshAll();
			}

		});
		
		// LOWERBOUND
		myView.getTxtLowerBound().addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke) {
				int value = myView.getLowerBound();
				myModel.setLowerBound(value);
				
				// CODE TO COMPUTE POP DIST
				refreshAll();
			}
		});

		// UPPERBOUND
		myView.getTxtUpperBound().addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke) {
				int value = myView.getUpperBound();
				myModel.setUpperBound(value);
				
				// CODE TO COMPUTE POP DIST
				refreshAll();
			}
		});
		
		// POPULATION SLIDER
		myView.getSliderPopulation().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				int value = myView.getSliderPopulation().getValue();
				myView.getTxtPopSize().setText(Integer.toString(value));
				myView.getSliderSample().setMaximum(myView.getSliderPopulation().getValue());
				myModel.setPopSize(value);
				
				// CODE TO COMPUTE POP DIST
				refreshAll();
			}
		});
		
		// POPULATION
		myView.getTxtPopSize().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				try {
					int value = Integer.parseInt(myView.getTxtPopSize().getText());
					myView.getSliderPopulation().setValue(value);
					myModel.setPopSize(value);
				} catch (NumberFormatException e) {
				}
				
				refreshAll();
			}
		});
		
		// SAMPLE SLIDER
		myView.getSliderSample().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {		
				int value = myView.getSliderSample().getValue();
				myView.getTxtSamSize().setText(Integer.toString(value));
				myModel.setSamSize(value);
				
				// CODE TO COMPUTE POP DIST
				refreshSam();				
			}
		});

		myView.getTxtSamSize().addKeyListener(new KeyAdapter(){			
			@Override
			public void keyReleased(KeyEvent ke) {
				try {
					int value = Integer.parseInt(myView.getTxtSamSize().getText());
					myView.getSliderSample().setValue(value);
					myModel.setSamSize(value);
				} catch (NumberFormatException e) {
				}
				
				refreshSam();
			}
		});
	}
	
	public void refreshDetails() {
		myView.getLblPopMeanVal().setText(Double.toString(myModel.getPopMean()));
		myView.getLblPopVarianceVal().setText(Double.toString(myModel.getPopVariance()));
		myView.setPopDistTable(myModel.generatePopDistTable());
		myView.setPopDistChart(myModel.generatePopDataSet());
		
		myView.getLblSamMeanVal().setText(Double.toString(myModel.getSamMean()));
		myView.getLblSamVarianceVal().setText(Double.toString(myModel.getSamVariance()));
		myView.setSamDistTable(myModel.generateSamDistTable());
		myView.setSamDistChart(myModel.generateSamDataSet());
		myView.setSamMeanTable(myModel.generateSamMeanTable());
	}
	
	public void refreshAll() {
		myModel.generateData();
		refreshDetails();
	}
	
	public void refreshPop() {
		myModel.generatePopData();
		refreshDetails();
	}

	public void refreshSam() {
		myModel.generateSamData();
		refreshDetails();
	}
}
