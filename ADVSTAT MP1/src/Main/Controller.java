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
		refreshUI();
	}
	
	public void setListeners() {
		// DISTIBUTION TYPE
		myView.getDistributionType().addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				int value = myView.getDistributionType().getSelectedIndex();
				myModel.setDistType(value);
				
				switch(value) {
				case 0: // UNIFORM
					break;
				case 1: // POSITIVELY SKEWED
					break;
				case 2: // NEGATIVELY SKEWED
					break;
				case 3: // BIMODAL
					break;
				case 4: // NORMAL
					break;
				case 5: // RANDOM
					break;
				}
				
				// CODE TO COMPUTE POP DIST
				refreshUI();
			}

		});
		
		// LOWERBOUND
		myView.getTxtLowerBound().addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke) {
				int value = myView.getLowerBound();
				myModel.setLowerBound(value);
				
				// CODE TO COMPUTE POP DIST
				refreshUI();
			}
		});

		// UPPERBOUND
		myView.getTxtUpperBound().addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke) {
				int value = myView.getUpperBound();
				myModel.setUpperBound(value);
				
				// CODE TO COMPUTE POP DIST
				refreshUI();
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
				refreshUI();
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
			}
		});
	}
	
	public void refreshUI() {
		myView.getLblPopMeanVal().setText(Double.toString(myModel.getMean()));
		myView.getLblPopVarianceVal().setText(Double.toString(myModel.getVariance()));
		myView.setPopDistTable(myModel.generatePopDistTable());
		myView.setPopDistChart(myModel.generatePopDataSet());
	}
}
