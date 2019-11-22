package logic;

import gui.TimerGUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.Main;

public class Timer{
	
	public static final int SLEEP_TIME = 1000;
	public static final int COUNT_UP = 1;
	public static final int COUNT_DOWN = -1; 
			
	private TimerGUI timerGUI;
	private int hour;
	private int minute;
	private int second;
	private int countMode;
	private boolean isCounting;
	private Thread thread;
	
	
	public Timer(String name, int countMode) {
		timerGUI = new TimerGUI(name);
		this.countMode = countMode;
		resetHandle();
		setEventHandling();
		threadInitialize();
	}

	private void threadInitialize() {
		thread = new Thread(new Runnable() {	
			@Override
			public void run() {
				while(isCounting) {
					try {
						if(countMode == COUNT_UP) {
							Main.timer.getThread().join();
						}
						if(isCounting) {
							updateTime();
							updateGUI();
							Thread.sleep(SLEEP_TIME);							
						}
					} catch(InterruptedException e) {
						//
					}
				}
			}
		});
		thread.start();
	}
	
	private void updateGUI() {
		Platform.runLater(() ->	timerGUI.getDisplayPart().update(getTimeString())
		);
	}
	
	private void updateTime() {
		if(countMode == 1) {
			second++;
			if(second >= 60) {
				minute++;
				second = 0;
			}
			if(minute >= 60) {
				hour++;
				minute = 0;
			}
		} else {
			second--;
			if(second <= 0) {
				isCounting = false;
				second = 0;
			}
		}
	}
	
	
	private String getTimeString() {
		String timeString = String.format("%02d:%02d:%02d", hour, minute, second);
		return timeString;
	}
	
	private void resetHandle() {
		isCounting = false;
		if(countMode == COUNT_UP) {
			second = 0; minute = 0; hour = 0;
		} else {
			second = 30; minute = 0; hour = 0;
		}
		updateGUI();
	}
	
	private void startStopHandle() {
		if(isCounting == false) {
			isCounting = true;
			threadInitialize();
		} else {
			isCounting = false;
		}
	}
	
	
	private void setEventHandling() {
		timerGUI.getControlPart().getStartStopButton().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				startStopHandle();
			}
		});
		timerGUI.getControlPart().getResetButton().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				resetHandle();
			}
		});
	}
	
	public Thread getThread() {
		return thread;
	}

	public TimerGUI getTimerGUI() {
		return timerGUI;
	}
		
}
