/*
 * Zafer Khourdaji
 * EID: E01251928
 */
public class Worker implements Runnable {

	// the maximum sleep time for the worker in milliseconds
	private static final int SLEEP_TIME = 6000;
	private static final int NUMBER_OF_WIDGETS_TO_PRODUCE = 24;
	
	private char workerID;
	private ConveyerBelt takeFrom, produceTo;
	
	public Worker(char id, ConveyerBelt takeFrom, ConveyerBelt produceTo) {
		this.workerID =id;
		// one of those will be null for worker A and worker D
		// since worker A only puts Widgets on the Conveyer belt
		// and worker D only takes Widgets from the Conveyer belt
		this.produceTo = produceTo;
		this.takeFrom = takeFrom;
	}
	
	@Override
	public void run() {
		
		// only worker A will access i to set the ID of the widget
		int widgetID = 0;
		
		while(true) {
			/* check if this worker is worker A since takeFrom belt will be null
			 * he only produces to the produceTo belt
			 */
			if (this.workerID == 'A' && widgetID < NUMBER_OF_WIDGETS_TO_PRODUCE) {
			
				try {
					// simulate work by sleeping (I wish this was how the real world worked)
					long random = new Double(Math.random() * SLEEP_TIME).longValue();
					Widget widget = new Widget(widgetID);
					Thread.sleep(random);
					widget.workUpon();	// to keep track of the number of workers who worked on this widget
					produceTo.put(widget, this.workerID);
					widgetID++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// worker D only takes widgets from the belt and doesnt have a produceTo belt
			else if (this.workerID == 'D') {
				try {
					Widget widget = takeFrom.take(this.workerID);
					// simulate work by sleeping (I wish this was how the real world worked)
					long random = new Double(Math.random() * SLEEP_TIME).longValue();
					Thread.sleep(random);	
					widget.workUpon();	// to keep track of the number of workers who worked on this widget
					// check if we this is the last widget
					if (widget.getId() == NUMBER_OF_WIDGETS_TO_PRODUCE - 1) {
						System.out.println("Sucessfully produced all " + NUMBER_OF_WIDGETS_TO_PRODUCE + " widgets!");
						System.exit(0);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			/*
			 * for workers B,C
			 * they can take from the takeFrom belt and work on the widget before placing it
			 * on the produceTo belt
			 */
			else if (this.workerID == 'B' || this.workerID == 'C'){
				// simulate work by sleeping (I wish this was how the real world worked)
				long random = new Double(Math.random() * SLEEP_TIME).longValue();

				try {
					Widget widget = takeFrom.take(this.workerID);
					widget.workUpon();
					Thread.sleep(random);
					produceTo.put(widget, this.workerID);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}		
	}
}
