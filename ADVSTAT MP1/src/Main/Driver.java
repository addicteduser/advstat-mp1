package Main;

public class Driver {
	public static void main(String[] args) {
		View view = new View();
		new Controller(view, new Model(view.LOW_BOUND, view.UP_BOUND, view.POP_SIZE, view.SAM_SIZE, view.DIST_TYPE));
	}
}