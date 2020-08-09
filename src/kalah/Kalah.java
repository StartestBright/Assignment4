package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */

 
public class Kalah {
  private int[] p1Houses = {0,4,4,4,4,4,4};
  
  private int[] p2Houses = {0,4,4,4,4,4,4};

  
  

  public boolean checkGameOver(boolean p1Turn){
    boolean gameOver = false;
    boolean p1HousesClear = true;
    boolean p2HousesClear = true;


    
    for(int i=0;i<p1Houses.length; i++){
      if(p1Houses[i] != 0)
        p1HousesClear= false;
    }
    for(int i =0;i<p2Houses.length;i++){
      if(p2Houses[i] != 0){
        p2HousesClear = false;
      }
    }

    
    if( (p1Turn && p1HousesClear) || (!p1Turn && p2HousesClear) ){
      gameOver= true;
    }


    return gameOver;
    
  }

  public static void printGameBoard(IO io){
    
  }
  
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

  
	public void play(IO io) {

    boolean p1Turn = true;
    int choice = -1;
    boolean p1HouseChosen = true;
    boolean isGameOver = false;


    while(!checkGameOver(p1Turn) && !isGameOver){
      
      int nSeedsLeft =0;

        io.println("+----+-------+-------+-------+-------+-------+-------+----+"); 
        io.println("| P2 | 6["+String.format("%2d",p2Houses[6])+"] | 5["+String.format("%2d",p2Houses[5])+"] | 4["+String.format("%2d",p2Houses[4])+"] | 3["+String.format("%2d",p2Houses[3])+"] | 2["+String.format("%2d",p2Houses[2])+"] | 1["+String.format("%2d",p2Houses[1])+"] | " +String.format("%2d",p1Houses[0])+" |");
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println("| "+String.format("%2d",p2Houses[0])+" | 1["+String.format("%2d",p1Houses[1])+"] | 2["+String.format("%2d",p1Houses[2])+"] | 3["+String.format("%2d",p1Houses[3])+"] | 4["+String.format("%2d",p1Houses[4])+"] | 5["+String.format("%2d",p1Houses[5])+"] | 6["+String.format("%2d",p1Houses[6])+"] | P1 |");
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");









      if(p1Turn){
        p1HouseChosen= true;
        boolean validChoice = true;
        
        choice =  io.readInteger("Player P1's turn - Specify house number or 'q' to quit: ",1,6,-1,"q");
        
        if(choice !=-1)
          if(p1Houses[choice] == 0 )
            validChoice = false;

        while(!validChoice){
          io.println("House is empty. Move again.");
          io.println("+----+-------+-------+-------+-------+-------+-------+----+"); 
        io.println("| P2 | 6["+String.format("%2d",p2Houses[6])+"] | 5["+String.format("%2d",p2Houses[5])+"] | 4["+String.format("%2d",p2Houses[4])+"] | 3["+String.format("%2d",p2Houses[3])+"] | 2["+String.format("%2d",p2Houses[2])+"] | 1["+String.format("%2d",p2Houses[1])+"] | " +String.format("%2d",p1Houses[0])+" |");
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println("| "+String.format("%2d",p2Houses[0])+" | 1["+String.format("%2d",p1Houses[1])+"] | 2["+String.format("%2d",p1Houses[2])+"] | 3["+String.format("%2d",p1Houses[3])+"] | 4["+String.format("%2d",p1Houses[4])+"] | 5["+String.format("%2d",p1Houses[5])+"] | 6["+String.format("%2d",p1Houses[6])+"] | P1 |");
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        choice =  io.readInteger("Player P1's turn - Specify house number or 'q' to quit: ",1,6,-1,"q");


          if(p1Houses[choice] != 0)
            validChoice = false;
        }
        
        
        if(choice==-1){

          io.println("Game over");
          io.println("+----+-------+-------+-------+-------+-------+-------+----+"); 
          io.println("| P2 | 6["+String.format("%2d",p2Houses[6])+"] | 5["+String.format("%2d",p2Houses[5])+"] | 4["+String.format("%2d",p2Houses[4])+"] | 3["+String.format("%2d",p2Houses[3])+"] | 2["+String.format("%2d",p2Houses[2])+"] | 1["+String.format("%2d",p2Houses[1])+"] | "+ String.format("%2d",p1Houses[0])+" |");
          io.println("|    |-------+-------+-------+-------+-------+-------|    |");
          io.println("| "+String.format("%2d",p2Houses[0])+" | 1["+String.format("%2d",p1Houses[1])+"] | 2["+String.format("%2d",p1Houses[2])+"] | 3["+String.format("%2d",p1Houses[3])+"] | 4["+String.format("%2d",p1Houses[4])+"] | 5["+String.format("%2d",p1Houses[5])+"] | 6["+String.format("%2d",p1Houses[6])+"] | P1 |");
          io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		      isGameOver= true;
          break;
        }

        nSeedsLeft = p1Houses[choice]; 
        p1Houses[choice]=0;
        
      }else{
        p1HouseChosen=false;

        boolean validChoice = true;

        choice =  io.readInteger("Player P2's turn - Specify house number or 'q' to quit: ",1,6,-1,"q");
        
        if(choice !=-1)
          if(p2Houses[choice] == 0)
            validChoice = false;
        while(!validChoice){
          io.println("House is empty. Move again.");
          io.println("+----+-------+-------+-------+-------+-------+-------+----+"); 
        io.println("| P2 | 6["+String.format("%2d",p2Houses[6])+"] | 5["+String.format("%2d",p2Houses[5])+"] | 4["+String.format("%2d",p2Houses[4])+"] | 3["+String.format("%2d",p2Houses[3])+"] | 2["+String.format("%2d",p2Houses[2])+"] | 1["+String.format("%2d",p2Houses[1])+"] | " +String.format("%2d",p1Houses[0])+" |");
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println("| "+String.format("%2d",p2Houses[0])+" | 1["+String.format("%2d",p1Houses[1])+"] | 2["+String.format("%2d",p1Houses[2])+"] | 3["+String.format("%2d",p1Houses[3])+"] | 4["+String.format("%2d",p1Houses[4])+"] | 5["+String.format("%2d",p1Houses[5])+"] | 6["+String.format("%2d",p1Houses[6])+"] | P1 |");
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        choice =  io.readInteger("Player P2's turn - Specify house number or 'q' to quit: ",1,6,-1,"q");


          if(p2Houses[choice] != 0)
            validChoice = false;
        }


        if(choice==-1){

          io.println("Game over");
          io.println("+----+-------+-------+-------+-------+-------+-------+----+"); 
        io.println("| P2 | 6["+String.format("%2d",p2Houses[6])+"] | 5["+String.format("%2d",p2Houses[5])+"] | 4["+String.format("%2d",p2Houses[4])+"] | 3["+String.format("%2d",p2Houses[3])+"] | 2["+String.format("%2d",p2Houses[2])+"] | 1["+String.format("%2d",p2Houses[1])+"] | "+ String.format("%2d",p1Houses[0])+" |");
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println("| "+String.format("%2d",p2Houses[0])+" | 1["+String.format("%2d",p1Houses[1])+"] | 2["+String.format("%2d",p1Houses[2])+"] | 3["+String.format("%2d",p1Houses[3])+"] | 4["+String.format("%2d",p1Houses[4])+"] | 5["+String.format("%2d",p1Houses[5])+"] | 6["+String.format("%2d",p1Houses[6])+"] | P1 |");
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
			  isGameOver= true;
          break;
        }

        nSeedsLeft = p2Houses[choice];
        p2Houses[choice]=0;
        
      }

















      
      choice++;
      
      while(nSeedsLeft>0 && !isGameOver){
        
        if(p1HouseChosen){ //P1 side
          

          if(choice>6){
            choice=0;
          }

		   if(!p1Turn && choice == 0){  //at p2 store on p1Turn
			 p1HouseChosen = false;
        	choice++;
		 	}
		 	else if(p1Turn && choice ==0){  // At my store on my turn
			  p1Houses[choice]++;
			  
			  if(nSeedsLeft == 1){ // If it was the last seed
				  p1Turn= true;
				  p1HouseChosen = true;
			  }else{
				  p1HouseChosen = false;
			  }
			  nSeedsLeft--;
        	  choice++;
			  
		  }else{
			  if(nSeedsLeft ==1){
				  
				  if(p1Houses[choice] == 0 && p1Turn && p2Houses[7-choice]!=0){
					  int capturedSeeds = p2Houses[7-choice]+1;
            p2Houses[7-choice] = 0;
					  p1Houses[choice] = 0;
					  p1Houses[0] += capturedSeeds;
				  }else{
					  p1Houses[choice]++;
				  }
          p1Turn = !p1Turn;
          
          
			  }else{
				  p1Houses[choice]++;
			  }
			  nSeedsLeft--;
        	choice++;
		  }

          
          
          
        }else if(!p1HouseChosen){  //P2 side
			if(choice>6){
            choice=0;
          }

		 if(p1Turn && choice == 0){  //at p2 store on p1Turn
			 p1HouseChosen = true;
        	choice++;
		 }
		 else  if(!p1Turn && choice ==0){  // At my store on my turn
			  p2Houses[choice]++;
			  
			  if(nSeedsLeft == 1){ // If it was the last seed
				  p1Turn= false;
				  p1HouseChosen = false;
			  }else{
				  //p1Turn= true;
				  p1HouseChosen = true;
			  }
			  nSeedsLeft--;
        	choice++;
		  }else{
			  if(nSeedsLeft ==1){ //if the last seed 
				  if(p2Houses[choice] == 0 && !p1Turn && p1Houses[7-choice]!=0){ //capture if the house empty on your side
					  int capturedSeeds = p1Houses[7-choice]+1;
            		  p1Houses[7-choice] = 0;
					  p2Houses[choice] = 0;
					  p2Houses[0] += capturedSeeds;
				  }else{
					  p2Houses[choice]++;
            
				  }
         			p1Turn = !p1Turn;
			  }else{
				  p2Houses[choice]++;
			  }

			  nSeedsLeft--;
        		choice++;
		  }

          
          


        }
        //nSeedsLeft--;
        //choice++;
        
    }
		//p1Turn = !p1Turn;

		if(checkGameOver(p1Turn)){
      io.println("Game over");
	  isGameOver = true;
    	}
    }
    
    
    
    
	}
}
