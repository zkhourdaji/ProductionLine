
public class ConveyerBelt {
	
	public static final int BUFFER_SIZE = 3;
	
	
	private Widget[] buffer;	//widget buffer
	private int numberOfWidgets;// number of widgets currently on the belt
	private int in;				// points to the next free position in the buffer
	private int out;			// points to the next full position in the buffer
	
	public ConveyerBelt() {
		this.buffer = new Widget[BUFFER_SIZE];
		this.numberOfWidgets = 0;
		this.in = 0;
		this.out = 0;
	}
	
	/*
	 * puts the widget on the ConveyerBelt
	 * if the belt is full then wait for another worker to take 
	 * a widget from the belt
	 */
	public synchronized void put(Widget widget, char workerID) {
		
		// if the belt is full then wait
		while(numberOfWidgets == BUFFER_SIZE) {
			try {
				System.out.println("WARNING: worker " + workerID + " is waiting to put " + widget + " " + widget.getHandledString() + " on conveyer");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		/*
		 * belt is not full so increment the number of widgets on the belt
		 * and insert the widget
		 */
		numberOfWidgets++;
		this.buffer[in] = widget;
		// wrap around the buffer
		in = (in + 1) % BUFFER_SIZE;
		System.out.println("Worker " + workerID + " is placing " + widget + " " + widget.getHandledString() + " on the belt");

		
		/*
		 * notify threads that are waiting to take a widget from the belt if it was originally empty
		 */
		notifyAll();
	}
	
	/*
	 * take a widget from the Conveyer belt
	 */
	public synchronized Widget take(char workerID) {
		Widget widget;
		
		// if there is no widgets to take then wait
		while(this.numberOfWidgets == 0) {
			try {
				System.out.println("WARNING: worker " + workerID + " is idle!");
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		/*
		 * at this point there is a widget to take from the belt
		 */
		this.numberOfWidgets --;
		widget = buffer[out];
		out = (out + 1) % BUFFER_SIZE;
		
		System.out.println("Worker " + workerID + " is retrieving " + widget + " " + widget.getHandledString() + " from the belt" );

		
		// notify any workers that were waiting to put a widget on the belt and it was full
		notifyAll();
		return widget;
	}
}
