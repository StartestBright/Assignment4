package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */

 class GameBoard{
	 IO io;
	 public GameBoard(IO io){
		 io = io;
	 }
	 
	 public void printGameBoard(int[] player1HouseArray, int[] player2HouseArray){
		io.println("+----+-------+-------+-------+-------+-------+-------+----+"); 
        io.println("| P2 | 6["+String.format("%2d",player2HouseArray[6])+"] | 5["+String.format("%2d",player2HouseArray[5])+"] | 4["+String.format("%2d",player2HouseArray[4])+"] | 3["+String.format("%2d",player2HouseArray[3])+"] | 2["+String.format("%2d",player2HouseArray[2])+"] | 1["+String.format("%2d",player2HouseArray[1])+"] | " +String.format("%2d",player1HouseArray[0])+" |");
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println("| "+String.format("%2d",player2HouseArray[0])+" | 1["+String.format("%2d",player1HouseArray[1])+"] | 2["+String.format("%2d",player1HouseArray[2])+"] | 3["+String.format("%2d",player1HouseArray[3])+"] | 4["+String.format("%2d",player1HouseArray[4])+"] | 5["+String.format("%2d",player1HouseArray[5])+"] | 6["+String.format("%2d",player1HouseArray[6])+"] | P1 |");
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
	 }

 }
 
 class GameDataMAnager{
	 private int[] player1HouseArray = {0,4,4,4,4,4,4};  
  	 private int[] player2HouseArray; 

	 public GameDataMAnager(int[] p1Array,int []p2Array){
		 this.player1HouseArray = p1Array;
		 this.player2HouseArray = p2Array;
	 }

	 public int[] getPlayerStat(int playerNum){
		 if(playerNum == 1){
			 return player1HouseArray;
		 }else
		 	 return player2HouseArray;
	 }  
	 public void setPlayer1Stat(int playerNum,int[] houseArray){
		 if(playerNum ==1 ){
			 for(int i=0;i<houseArray.length;i++){
				player1HouseArray[i] = houseArray[i];
			 }
		 }else{
			 for(int i=0;i<houseArray.length;i++){
				 player2HouseArray[i] = houseArray[i];
			 }
		 }
		 
	 }

	 

 }
 
 class Controller{

	 private GameDataMAnager dataManager;

	 public Controller(){
		 int[] player1HouseArray = {0,4,4,4,4,4,4};  
		 int[] player2HouseArray = {0,4,4,4,4,4,4};  
		 dataManager = new GameDataMAnager(player1HouseArray,player2HouseArray);
	 }

	 public void spreadSeeds(){

	 }
 }

 
public class Kalah {
  private int[] player1HouseArray = {0,4,4,4,4,4,4};  
  private int[] player2HouseArray = {0,4,4,4,4,4,4};
  private int currentHouseIndex;
  private boolean onPlayer1Row = true;
  private boolean playerQuit = false;
  private boolean isPlayer1Turn = true;	
  
  

  

  private boolean checkGameOver(IO io){
    boolean gameOver = false;
    boolean player1HouseArrayClear = true;
    boolean player2HouseArrayClear = true;
	


    
    for(int i=1;i<player1HouseArray.length; i++){
      if(player1HouseArray[i] != 0)
        player1HouseArrayClear= false;
    }
    for(int i =1;i<player2HouseArray.length;i++){
      if(player2HouseArray[i] != 0){
        player2HouseArrayClear = false;
      }
    }

    
    if( (isPlayer1Turn && player1HouseArrayClear) || (!isPlayer1Turn && player2HouseArrayClear) || playerQuit){
      gameOver= true;
    }
    
	

    if(gameOver){
      setGameOver(io);
    }
    return gameOver;
    
  }




	private void printGameBoard(IO io){
		io.println("+----+-------+-------+-------+-------+-------+-------+----+"); 
        io.println("| P2 | 6["+String.format("%2d",player2HouseArray[6])+"] | 5["+String.format("%2d",player2HouseArray[5])+"] | 4["+String.format("%2d",player2HouseArray[4])+"] | 3["+String.format("%2d",player2HouseArray[3])+"] | 2["+String.format("%2d",player2HouseArray[2])+"] | 1["+String.format("%2d",player2HouseArray[1])+"] | " +String.format("%2d",player1HouseArray[0])+" |");
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println("| "+String.format("%2d",player2HouseArray[0])+" | 1["+String.format("%2d",player1HouseArray[1])+"] | 2["+String.format("%2d",player1HouseArray[2])+"] | 3["+String.format("%2d",player1HouseArray[3])+"] | 4["+String.format("%2d",player1HouseArray[4])+"] | 5["+String.format("%2d",player1HouseArray[5])+"] | 6["+String.format("%2d",player1HouseArray[6])+"] | P1 |");
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
	}
  


	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}




	private int getUsercurrentHouseIndex(IO io,int playerTurn){

		boolean validcurrentHouseIndex = true;
        
        currentHouseIndex =  io.readInteger("Player P"+playerTurn+"'s turn - Specify house number or 'q' to quit: ",1,6,-1,"q");
        
        if(currentHouseIndex !=-1){
          if(playerTurn == 1){
            if(player1HouseArray[currentHouseIndex] == 0)
                  validcurrentHouseIndex = false;
          }else{
            if(player2HouseArray[currentHouseIndex] == 0)
                  validcurrentHouseIndex = false;
          }
          
		    }
        
        while(!validcurrentHouseIndex){
          io.println("House is empty. Move again.");
          printGameBoard(io);
          currentHouseIndex =  io.readInteger("Player P"+playerTurn+"'s turn - Specify house number or 'q' to quit: ",1,6,-1,"q");

          if(currentHouseIndex == -1){
            return currentHouseIndex;
          }else{
            if(playerTurn==1){
              if(player1HouseArray[currentHouseIndex] != 0)
                      validcurrentHouseIndex = true;
            }else{
              if(player2HouseArray[currentHouseIndex] != 0)
                      validcurrentHouseIndex = true;
            }	
          }
          
        }

		return currentHouseIndex;
        

	}


	private void spreadSeeds(int nSeedsLeft){
		while(nSeedsLeft>0 && !playerQuit){
			boolean lastSeed = (nSeedsLeft-1 == 0);

			if(currentHouseIndex>6){
					currentHouseIndex=0;
			}
        
			if(onPlayer1Row){
				if(!isPlayer1Turn && currentHouseIndex == 0){ 
					onPlayer1Row = false;
				}else if(isPlayer1Turn && currentHouseIndex ==0){ 
					player1HouseArray[currentHouseIndex]++;
					if(lastSeed){ 
						isPlayer1Turn= true;
						onPlayer1Row = true;
					}else{
						onPlayer1Row = false;
					}
					nSeedsLeft--;
					
				}else{
					if(lastSeed){
						if(player1HouseArray[currentHouseIndex] == 0 && isPlayer1Turn && player2HouseArray[7-currentHouseIndex]!=0){
							int otherSideHouseIndex = 7 - currentHouseIndex;
							int capturedSeeds = player2HouseArray[otherSideHouseIndex]+1;
							player2HouseArray[otherSideHouseIndex] = 0;
							player1HouseArray[currentHouseIndex] = 0;
							player1HouseArray[0] += capturedSeeds;
						}else{
							player1HouseArray[currentHouseIndex]++;
								
						}
							isPlayer1Turn = !isPlayer1Turn;
					}else{
						player1HouseArray[currentHouseIndex]++;
					}
					nSeedsLeft--;
				}
			}else if(!onPlayer1Row){ 

				if(isPlayer1Turn && currentHouseIndex == 0){  
					onPlayer1Row = true;
				}
				else  if(!isPlayer1Turn && currentHouseIndex ==0){ 
					player2HouseArray[currentHouseIndex]++;
					
					if(lastSeed){ 
						isPlayer1Turn= false;
						onPlayer1Row = false;
					}else{
						onPlayer1Row = true;
					}
					nSeedsLeft--;
				}else{
					if(lastSeed){
						if(player2HouseArray[currentHouseIndex] == 0 && !isPlayer1Turn && player1HouseArray[7-currentHouseIndex]!=0){ 
							int otherSideHouseIndex = 7 - currentHouseIndex;
							int capturedSeeds = player1HouseArray[otherSideHouseIndex]+1;
							player1HouseArray[otherSideHouseIndex] = 0;
							player2HouseArray[currentHouseIndex] = 0;
							player2HouseArray[0] += capturedSeeds;
						}else{
							player2HouseArray[currentHouseIndex]++;
					
						}
							isPlayer1Turn = !isPlayer1Turn;
					}else{
						player2HouseArray[currentHouseIndex]++;
					}

					nSeedsLeft--;
				}
			}
			currentHouseIndex++;
        
    }
	}







	private void setGameOver(IO io){
    
    int player1Score =0;
    int player2Score =0;
    if(!playerQuit){
      
      for(int i=0;i<player1HouseArray.length;i++){
        player1Score += player1HouseArray[i];
        player2Score += player2HouseArray[i];
      }

    }

    if(playerQuit){
      io.println("Game over");
      printGameBoard(io);
    }else{
      printGameBoard(io);
      io.println("Game over");
      printGameBoard(io);
      io.println("	player 1:"+player1Score);	
      io.println("	player 2:"+player2Score); 
      if(player1Score>player2Score){
        io.println("Player 1 wins!");
      }else if(player2Score>player1Score){
        io.println("Player 2 wins!");
      }else{
        io.println("A tie!");
      }
    }
    playerQuit= true;

	}

	public void play(IO io) {
     currentHouseIndex = -1;
    


    while(!checkGameOver(io)){
      
      int nSeedsLeft =0;
      printGameBoard(io);


        if(isPlayer1Turn){
        onPlayer1Row= true;

        currentHouseIndex = getUsercurrentHouseIndex(io,1);

        if(currentHouseIndex==-1){
          playerQuit =true;
          setGameOver(io);
          break;
        }

        nSeedsLeft = player1HouseArray[currentHouseIndex]; 
        player1HouseArray[currentHouseIndex]=0;
          
          
          }else{
        
        onPlayer1Row=false;

        currentHouseIndex = getUsercurrentHouseIndex(io,2);
        
        if(currentHouseIndex==-1){
          playerQuit = true;
          setGameOver(io);
          break;
        }

        if(!playerQuit){
          nSeedsLeft = player2HouseArray[currentHouseIndex];
          player2HouseArray[currentHouseIndex]=0;
        }
        
        
      }
        
        if(!playerQuit){
          currentHouseIndex++;
          spreadSeeds(nSeedsLeft);
        }
    }
    
    
    
    
	}
}
