package view;



import controller.*;
import adapter.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class View {
	private Adapter adapter;
	private JFrame gui;
    private JButton[][] blocks;
    private JButton reset;
    private JTextArea playerturn;
    
    public View() {
    		this.gui = new JFrame("Tic Tac Toe");
    		this.blocks = new JButton[3][3];
    		this.reset = new JButton("Reset");
    		this.playerturn = new JTextArea();

    		initialize();
    }

    public void setActionListener(Controller c) {

	this.adapter = new Adapter(c,this);
		for(int row = 0; row<3 ;row++) {
	        for(int column = 0; column<3 ;column++) {
	        		blocks[row][column].addActionListener(adapter);
	        }
		}
	    reset.addActionListener(adapter);
    }

    public void initialize () {
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    gui.setSize(new Dimension(500, 350));
	    gui.setResizable(true);
	    
	    JPanel gamePanel = new JPanel(new FlowLayout());
	    JPanel game = new JPanel(new GridLayout(3,3));
	    gamePanel.add(game, BorderLayout.CENTER);
	    JPanel options = new JPanel(new FlowLayout());
	    options.add(reset);
	    JPanel messages = new JPanel(new FlowLayout());
	    messages.setBackground(Color.white);
	    gui.add(gamePanel, BorderLayout.NORTH);
	    gui.add(options, BorderLayout.CENTER);
	    gui.add(messages, BorderLayout.SOUTH);
	    messages.add(playerturn);
	    playerturn.setText("Player 1 to play 'X'");
	    
	    for(int row = 0; row<3 ;row++) {
	        for(int column = 0; column<3 ;column++) {
	            blocks[row][column] = new JButton();
	            blocks[row][column].setPreferredSize(new Dimension(75,75));
	            blocks[row][column].setText("");
	            game.add(blocks[row][column]);
	            
		    }
		}

	    gui.setVisible(true);

    }

    public boolean isReset(ActionEvent e) {
    		if(e.getSource() == reset)
    			return true;
    		return false;
    }
    
    public ArrayList<Integer> getPosition(ActionEvent e) {
    	ArrayList<Integer> position = new ArrayList<Integer>();
    	for(int row = 0; row<3 ;row++) {
	        for(int column = 0; column<3 ;column++) {
	        		if(e.getSource() == blocks[row][column]) {
	        			position.add(row);
	        			position.add(column);
	        		}
	        }
    		}
    		return position;
    }

    public void update(int row, int column, char symbol, String message) {
    		blocks[row][column].setText(Character.toString(symbol));
    		blocks[row][column].setEnabled(false);
    		playerturn.setText(message);
    	
    }
    

    public void isWinner(int row, int column, char symbol, String message) {
		blocks[row][column].setText(Character.toString(symbol));
		blocks[row][column].setEnabled(false);
		for(int i = 0; i<3 ;i++) {
	        for(int j = 0; j<3 ;j++) {
	        	blocks[i][j].setEnabled(false);
	        }
		}
		playerturn.setText(message);

    }

    public void resetGame() {
    	for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                blocks[row][column].setText("");
                blocks[row][column].setEnabled(true);
            }
        }
        playerturn.setText("Player 1 to play 'X'");
    }

    public String getButtonText(int i, int j) {
    		return blocks[i][j].getText();
    }

}