
public class Widget {
	
	private int widgetID;
	private int workedUponCounter;
	
	public Widget(int id) {
		this.widgetID = id;
		this.workedUponCounter = 0;
	}
	
	/*
	 * this method is called by the worker thread when a worker works on this widget,
	 * to keep track of how many workers worked on it.
	 */
	public void workUpon() {
		this.workedUponCounter++;
	}
	
	@Override
	public String toString() {
		return "Widget " + widgetID;
	}
	
	public int getId() {
		return widgetID;
	}
	
	public int getCounter() {
		return workedUponCounter;
	}
	
	/*
	 * this method returns a string like "handled by <A,B,C>"
	 */
	public String getHandledString() {
		String workersIDs = "ABCD";
		String handledString = "";
		for (int i = 0; i < workedUponCounter; i++) {
			// don't concatenate a comma if its the last iteration
			if (i == workedUponCounter - 1)
				handledString += workersIDs.charAt(i);
			else 
				handledString += workersIDs.charAt(i) + ",";

		}
		return "handled by <" + handledString +">";
	}

}
