# StreetMapping
Grace Zheng & Dung Le
How the program works:
- the program takes in the command lines in the terminal:
	+ If there is only '--show' in the command line: create a GUI variable and plot the graph
	+ If there is only '--directions' in the command line: take out the 2 intersections out of the command lines and put them into the getShortestPath method, which calls the dijkstra method to find the shortest path between those two intersections. After that it will print out the shortest path using the print method in main.java
	+ If there are '--show' and '--directions' in the command line: do the same as if there were only a directions in the command line. After getting the shortest path, create a new GUI variable and put the path into the GUI. The GUI will call the GUI frame which will eventually show the map with the shortest distance in the cyan color.
				
- the shortest path is shown in the cyan color.
- the intersections in the shortest path will be printed in the console in beginning-to-end order.
- the distance in miles is also printed in the console.

Obstacles:
- Initially, it takes a lot of time to deal with the nys.txt file because there are cycle graph in the map
=> there are a lot of alike intersection in the queue so we had to take it out by fixing the comparable method when we compare 2 intersections. We made the visited intersection smaller than the unvisited intersection.
=> the queue will put the visited intersections on the top. Then we will make the queue pop the top element out until there is no visited intersection in the queue.

Files included in project: main.java, Node.java, Inp.java, Haversine.java, GUI.java, GUIFrame.java

Distribution of workload:
- Input procession and finding the shortest path: Dung Le
- Graph implementation, map scaling, and map drawing: Grace Zheng.

EXTRA CREDIT:
1. Graph is movable using the arrow keys. 
2. Also, you can zoom in and zoom out of the map to see the path clearer using - and + keys
3. When you pressed the 'n' key, the map goes back to normal scale.
4. You can click in the map to see what intersection point is there. The intersection and its latitude and longitude will be printed in the console.
5. user can exit the program by typing 'exit'.

- The input and output parameters are indicated above each method in the code.
- The Haversine code was taken from https://github.com/jasonwinn/haversine/blob/master/Haversine.java

Design and implementation choice made:
- The map will automatically rescale itself when the window screen is changed.

The expected runtime for plotting the map: O(E) with E is the number of roads in the map.
Expected runtime for finding the shortest distance using priority queue: O(E log v)
