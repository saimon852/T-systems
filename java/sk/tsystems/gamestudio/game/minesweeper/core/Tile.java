package sk.tsystems.gamestudio.game.minesweeper.core;


public abstract class Tile {
    
  
    public enum State {
       
        OPEN, 
       
        CLOSED,
       
        MARKED
    }
    
    
    private State state = State.CLOSED;
	public char[] getValue;
        
    
    public State getState() {
        return state;
    }

    
    void setState(State state) {
        this.state = state;
    }
    
}
