package eg.edu.alexu.csd.oop.game.cs.State;

public class ConText {
	private IState state;

	   public ConText(float speed){
	      state = new BeginState(speed);
	   }
	   //move to the following state
	   public void nextState(){
	      this.state = state.NextState();		
	   }

	   public IState getState(){
	      return state;
	   }
}
