This program can model groups of parking garages with the following information: 
- Group_info: GroupName PricePerParkingHour GarageName#capacity GarageName#capacity ...
- Group_reset_info: GroupName NewPrice
- Every car will inquiry the parking information from the groups before it chooses where to park.
- Car will chose the parking lot with the lowest price and the most available space.
- If all parkings are full, cars will be in-line to wait until the groups inform it a new spot is available. - - First comes first served apply for waiting cars.
- Group can reset its price.
- Only new cars that enter the group parking lots after it resets its price are charged with the new price. Cars that have already parked there before the group changes its price are charged with the old price.
- Car can only be at one place at a time.




## The input text file:
- Out of the Group_info and Group_reset_info, the rest of the input file has the following format:
  Time inquiry_arrive: CarName CarName
  Time exit: CarName CarName

  example:
  8:00 inquiry_arrive: Car1 Car2     
  8:00 is the time the car(s) inquiries_price and arrives. To simplify the model, assume that car arrives immediately after it inquires price. Car1 and Car2 are the car names. Also assume that many cars can get timed-ticket and enter a parking lot at the same time. 
  If at a certain time (8:00 for example), there are both cars arrive and exit, the exit line will be put above the arrive line.



## The output of the program: 
   The program will print to screen the actions of the customer cars, the groups, and the garages accordingly.
- CarN gets informed by all groups that GarageN has the lowest price of n dollars/h and has the most available spaces (x spots)
- CarN gets a hh:mm timed ticked and enters GarageN right away
- At hh:mm, at GarageN, CarN presents their ticket and pays x.xx dollars for x.xx hours parking. CarN exits from GarageN right away
- GroupN has changed the parking price to x.xx dollars/hour
- All parking garages are full, CarN has inquired information at hh:mm and is now waiting until spot is available (when another car successfully exits the parking).
- If there are multiple cars waiting for parking, "first comes first served" will apply. If waiting cars happen to have the same arrive time, but only one spot is available, one of those waiting cars will be admitted.



## How to run the program:
- In Command Line, cd to the target folder of the maven project (maven-demo/target)
- Use the command "java -jar maven-demo-0.0.1-SNAPSHOT.jar" to run the program
- After running the command above, the program will prompt user to enter the text file that would be test
- The program will print to screen the actions of the customer cars, the groups, and the garages accordingly. The output can be compared with the result put in the "test_output" folder.
