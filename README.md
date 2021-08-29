# Live Score Board

Track team score by displaying live score on the screen (Smart TV, Computer, Phone, Tablet). This application will track score of two teams (red, blue). Feed the score to app using Flic buttons

![Live Score Board](https://thetechstack.net/assets/images/projects/live-score-board/live-score-board.jpg)

This application is developed using Spring Boot and ReactJS.

See this video for demo. [![Live score board]()](https://thetechstack.net/assets/images/projects/live-score-board/20210829_175848_1.mp4)

See below image to understand how this works;

![Live Score Board Flow](https://thetechstack.net/assets/images/projects/live-score-board/live-score-board-flow.jpg)

### Run this Application

Prerequisites: Java 11 

* Download latest .jar from releases.
* From terminal execute 'java -jar live-score-board-<version>.jar' to run the application.

Access application at http://localhost:8080. Initially application will show score 0 for both the teams, to update the score, configure your Flic Hub and two Flic buttons for red and blue score.
  
For Red Flic button, add an HTTP action on click and provide HTTP method as PUT and provide URL http://<host>:8080/score/increment/red
For Blue Flic button, add an HTTP action on click and provide HTTP method as PUT and provide URL http://<host>:8080/score/increment/blue
  
  
