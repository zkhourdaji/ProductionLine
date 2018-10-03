/*
 * Zafer Khourdaji
 * EID: E01251928
 */
public class Factory {

	public static void main(String[] args) {
		
		// creates 3 conveyer belts
		ConveyerBelt belt1 = new ConveyerBelt();
		ConveyerBelt belt2 = new ConveyerBelt();
		ConveyerBelt belt3 = new ConveyerBelt();
		
		/*
		 * the setup is :
		 * WorkerA -> ConveyerBelt1 -> WorkerB -> ConveryBelt2 -> WorkerC -> ConveryerBelt3 -> WorkerD
		 */
		Worker workerA = new Worker('A', null, belt1);
		Worker workerB = new Worker('B', belt1, belt2);
		Worker workerC = new Worker('C', belt2, belt3);
		Worker workerD = new Worker('D', belt3, null);
		
		Thread workerAThread = new Thread(workerA);
		Thread workerBThread = new Thread(workerB);
		Thread workerCThread = new Thread(workerC);
		Thread workerDThread = new Thread(workerD);
		
		workerAThread.start();
		workerBThread.start();
		workerCThread.start();
		workerDThread.start();
		
	}
}
