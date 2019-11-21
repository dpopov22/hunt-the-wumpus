# hunt-the-wumpus
To run this file you will need java installed. To compile the files and be able to run the program, please cd to the folder in which these files are saved, and then use the command javac *.java. Then type the command "java HuntTheWumpus". The program will ask you what width and length you want the map(game board) to be. In order to move the hunter to the left press a, to the right press d, down press s and up press w. If you are getting close to the wumpus, the box around the circle representing the wumpus will change to red. Then, if you stumble upon the wumpus, the circle will turn white to demonstrate the death of the hunter. If you wish to shoot the hunter, press the space bar to arm yourself. The hunter will then turn blue. Use the direction keys (awsd) to determine which direction you are shooting in. If you miss, the game is over. If you shoot the wumpus, the location of the wumpus is revealed and you win the game. 

The purpose of the Hunt The Wumpus project is to create a game through the implementation of a graph data structure. 
The graph data structure uses vertices and edges to create a connected grid of "rooms, "
which in this case, allows for the Hunter to travel from room to room in search of the Wumpus. This graph is undirected, 
which allowed for the Hunter to move back to a room he had already been in. In addition to the graph data structure, 
this project uses a BST Map,Hashmap, Arrays, Array lists, Heaps, and a Linked List to create the game successfully. 
It also utilizes inheritance, since the Hunter and Wumpus classes have to inherit methods from the Agent class. 
After completing the project, I had a playable Hunt the Wumpus game in which the Hunter could kill the rumpus by shooting, 
or die by stumbling upon the wumpus/missing his shot. 
