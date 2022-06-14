CREATE TABLE  Difficulty (
	DifficultyID INT NOT NULL,
	DifficultyLevel VARCHAR(250) NOT NULL,
	PRIMARY KEY(DifficultyID)
);

CREATE TABLE Recipe (
	RecipeID INT NOT NULL,
  DifficultyID INT NULL,
	Name VARCHAR(150) NOT NULL,
	Description VARCHAR(255),
	Instructions VARCHAR(255),
	PrepTime FLOAT,
  PRIMARY KEY(RecipeID),
	FOREIGN KEY (DifficultyID) REFERENCES Difficulty(DifficultyID)
);

CREATE TABLE Coffee (
  CoffeeID INT  NOT NULL,
  RecipeID INT NOT NULL,
  Name VARCHAR(50) NOT NULL,
  Description VARCHAR(255),
  Rating INT,
  PRIMARY KEY (CoffeeID),
  FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID)
);

CREATE TABLE  Tag (
  TagID INT NOT NULL,
  Name VARCHAR(100) NOT NULL,
  Description VARCHAR(255),
  PRIMARY KEY(TagID)
);

CREATE TABLE  CoffeeTag (
  CoffeeID INT NOT NULL,
  TagID INT NOT NULL,
  Value VARCHAR(50) NOT NULL,
  CONSTRAINT PK_CoffeeTag PRIMARY KEY (CoffeeID,TagID),
  FOREIGN KEY (CoffeeID) REFERENCES Coffee(CoffeeID),
  FOREIGN KEY (TagID) REFERENCES Tag(TagID)
);

CREATE TABLE Unit (
  UnitID INT NOT NULL,
  UnitName Varchar(50) NOT NULL, 
  PRIMARY KEY(UnitID)
);

CREATE TABLE Ingredient (
  IngredientID INT NOT NULL,
  Name VARCHAR(150) NOT NULL,
  PRIMARY KEY (IngredientID)
);

CREATE TABLE RecipeIngredient (
  RecipeID INT NOT NULL,
  IngredientID INT NOT NULL,
  UnitID INT NOT NULL,
  Quantity VARCHAR(120),
  CONSTRAINT PK_RecipeIngredient PRIMARY KEY (RecipeID, IngredientID),
  FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID),
  FOREIGN KEY (IngredientID) REFERENCES Ingredient(IngredientID)
);


CREATE TABLE CoffeeRating
(
    UserID VARCHAR(100) NOT NULL,
    CoffeeID INT NOT NULL,
    LastRating INT NOT NULL,
    CONSTRAINT  PRIMARY KEY (CoffeeID,UserID ),
    FOREIGN KEY (CoffeeID) REFERENCES Coffee (CoffeeID)
);






INSERT INTO Difficulty(DifficultyID,DifficultyLevel) VALUES
 (1,1)
,(2,2)
,(3,3)
,(4,4)
,(5,5);





INSERT INTO Unit(UnitID,Name) VALUES
 (1,'tsp')
,(2,'ml')
,(3,'tbsp')
,(4,'kg')
,(5,'mg')
,(6,'l')
,(7,'g')
,(8,'lb')
,(9,'cm')
,(10,'cup')
,(11,'oz')
,(12,'qt')
,(13,'mm'),
(14,'in');



INSERT INTO Ingredient(IngredientID,name) VALUES
 (1,'Coffee beans')
,(2,'Hot Water')
,(3,'Water')
,(4,'Milk')
,(5,'Whipped cream')
,(6,'Mint Chocolate -syrup')
,(7,'Crushed mint chocolate cookies')
,(8,'Espresso shot');


INSERT INTO Tag(TagID,Name,Description) VALUES
 (1,'classic','The good old granual coffee in a jar')
,(2,'cowboy','This is one of the easiest ways of brewing coffee.  Water is boiled with coffee grounds in a pot for a few minutes to make a crude brew.  Ideally this brewing method is suited to camping or backpacking.')
,(3,'Drip','The automatic drip coffee maker is likely the one you''re most familiar with. It''s probably housed in your kitchen right now.For a drip coffee maker, you need ground coffee beans, water, and paper filters.')
,(4,'Filter','ou place the filter into the coffee maker then add your coffee and water. The water slowly drips through the coffee and down into the pot.')
,(5,'Percolator','Coffee percolators give us a retro feel.  Many of these have largely been replaced by drip filters although some people still use them to brew a simple cup of coffee at home.')
,(6,'Vacuum','This coffeemaker uses the expansion and contraction of water vapor caused by alternating temperatures to draw hot water through coffee grounds.')
,(7,'EspressoMachine','Espresso machines produce small shots of coffee very quickly. The coffee produced from an espresso machine is often stronger and creamier than that produced in the drip coffee maker.')
,(8,'FrenchPress','French presses produce bolder coffee. Since the  coffee grounds are directly immersed in water the flavor is able to come out stronger.')
,(9,'Light','Toasted, light body, high acidity')
,(10,'Medium','Rounded, sweet flavour, extra body')
,(11,'MediumDark','Heavy, full body, bittersweet')
,(12,'Dark','Smokey, burnt, intensely bitter')
,(13,'Cinnamon','196°C (385°F)')
,(14,'NewEngland','205°C (401°F)')
,(15,'American','210°C (410°F)')
,(16,'City','219°C (426°F)')
,(17,'Full city','225°C (437°F)')
,(18,'Vienna','239°C (462°F)')
,(19,'French','243°C (469°F)')
,(20,'Italian','246°C (474°F)')
,(21,'Espresso','A short, strong drink (about 30 ml) served in an espresso cup.')
,(22,'cappuccino','A coffee drink consisting of espresso and a milk foam mixture (drink size about 160–240 ml). Served in a cappuccino cup.')
,(23,'ColdBrew','Cold Brew Coffee is a smooth, cold beverage prepared by brewing freshly ground coffee in cold water.')
,(24,'Mocha','Prepared like iced latte, but garnished and flavoured like caffè mocha (about 300 ml).');




INSERT INTO Recipe(RecipeID,Name,Description,Instructions,PrepTime,DifficultyID) VALUES
 (1,'AMERICANO (or ESPRESSO AMERICANO)','Often served in a cappuccino cup. (The espresso is added into the hot water rather than all the water being flowed through the coffee that would lead to over extraction.)','Espresso with added hot water (100–150 ml).',2,2)
,(2,'Caffè Latte','How to make Caffè Latte? Caffè Latte is a popular espresso based drink that combines espresso and warm milk.','STEP 1
 Prepare the espresso.                                                                                                                                                                                                                                       STEP 2
Pour milk to the pitcher. Make sure your stea wand cloth is moist.  
STEP 3
Purge the steam wand and pull it to far up and straight position. Place the pitcher so that the nozzle is aligned to the steam wand. Make sure the steam wand nozzle is in the middle of the pitcher and just below the milk surface. Tilt the pitcher a bit to optimize the whilrpool later on.
STEP 4
Swith on the steam wand. Start with the nozzle just below the surface but after a second or two rise the pitcher a bit so that the nozzle gets deeper into milk. Make sure the nozzle does not touch the bottom of the pitcher! 
STEP 5
Find a perfect position where the whilrpool of milk is created. Keep warming the milk until it reaches +55-62 c. 
STEP 6
Swirl the milk in the pitcher until it is smooth, silky and shiny.  
STEP 7
Pour the caffe latte and enjoy!',7,4)
,(3,'Mint Chocolate Mocha','erfect coffee recipe for dessert - tune your Café Mocha with mint and chocolate!','Measure syrup into a glass (300 ml).
Prepare the espresso on top.
Pour steamed milk into the glass.
Garnish with whipped cream and cookie crumble.',5,5)
,(4,'French Press','Rich flavors in a cup','STEP 1  
Measure and grind coffee.
STEP 2 
Warm up the french press with hot water. Use the water afterwards to water your houseplants.
STEP 3
Add the ground coffee to the decanter. 
STEP 4
Pour water on the ground coffee and swirl with spoon.
STEP 5
Place the plunger in upper position on top of the decanter and let the coffee steep for 4 min. 
STEP 6
Press the plunger down. 
Enjoy!',15,3)
,(5,'Cappuccino','How to make Cappuccino? Cappuccino is one of the most popular espresso based drinks. It combines espresso and warm, steamed milk.','STEP 1
Prepare the espresso.                                                                                                                                                                                                                                        STEP 2
Pour milk to the pitcher.
STEP 3
Make sure your steam wand cloth is moist. Purge the steam wand and pull it to far up and straight position. Place the pitcher so that the nozzle is aligned to the steam wand. Make sure the steam wand nozzle is in the middle of the pitcher and just below the milk surface. Tilt the pitcher a bit to optimize the whilrpool later on.   
STEP 4
Switch on the steam wand. Create froth for the first couple of second (up until the milk has reached 37 c). While froth is created, the volume of the milk increases so be sure to have the steam wand nozzle close to the milk surface all the way through. The longer you keep the nozzle close to milk surface the more froth you will create.   
When you have reached 37 c, rise the pitcher a bit so that the nozzle gets deeper into milk. Make sure the nozzle does not touch the bottom of the pitcher!  
Find a perrfect position where the whilrpool of milk is created. Keep warming the milk until it reaches +55-62 c. 
STEP 5
Swirl the milk in the pitcher until it is smooth, silky and shiny.  
STEP 6
Pour the cappuccino and enjoy!',10,4)
,(6,'Cold Brew Coffee','Learn how to make cold brew coffee by using Toddy. How to video & instruction how to prepare the coolest coffee drink!','Put the rubber plug in place so it can be removed from the outside. Moisten the reusable filter and place it at the bottom of the filter container. Place the filter bag inside the container.
Measure 300 g of coarsely ground coffee in the paper filter. Add 2 litres of water in a calm, circular motion.
 Tie the paper filter with a string. Cover the container and allow to brew for 14–16 hours in the fridge.
Remove the plug from the bottom of the filter container and pour the extract in a jug. Keep the extract in the fridge in a container tightly closed with a lid (it will keep for
about a week in the cold).',16,3)
,(7,'Espresso','How to make espresso?','STEP 1 
Wipe the portafilter with a clean, dry cloth. 
STEP 2
Dose the coffee grounds into the portafilter. 
STEP 3
Level the dose and tamp it evenly.
STEP 4
Flush the group head. 
STEP 5
Attach the portafilter and immediately start the brewing.
STEP 6
Place the cups below the portafilter spouts.  
Enjoy!',5,2);



INSERT INTO Coffee(CoffeeID,Name,Description,RecipeID,Rating) VALUES
 (1,'AMERICANO (or ESPRESSO AMERICANO)','Espresso with added hot water (100–150 ml). Often served in a cappuccino cup. (The espresso is added into the hot water rather than all the water being flowed through the coffee that would lead to over extraction.)',1,4)
,(2,'CAFFÈ LATTE','A tall, mild ''milk coffee'' (about 150-300 ml). An espresso with steamed milk and only a little milk foam poured over it. Serve in a latte glass or a coffee cup. Flavoured syrup can be added.',2,3)
,(3,'CAFFÈ MOCHA','A caffè latte with chocolate and whipped cream, made by pouring about 2 cl of chocolate sauce into the glass, followed by an espresso shot and steamed milk.',3,1)
,(4,'CAFÈ AU LAIT','French morning coffee. Made by mixing dark roasted filter coffee (often prepared with French Press) and warm milk. Served in a bowl or a large coffee cup.',4,2)
,(5,'CAPPUCCINO','A coffee drink consisting of espresso and a milk foam mixture (drink size about 160–240 ml). Served in a cappuccino cup.',5,0)
,(6,'COLD BREW COFFEE','Cold Brew Coffee is a smooth, cold beverage prepared by brewing freshly ground coffee in cold water. In the Cold Brew process, time makes up for heat.',6,1)
,(7,'ESPRESSO','A short, strong drink (about 30 ml) served in an espresso cup.',7,1);


INSERT INTO CoffeeTag(CoffeeID,TagID,Value) VALUES
 (1,1,0)
,(1,5,0)
,(1,15,0)
,(1,10,0)
,(2,18,0)
,(2,7,0)
,(2,8,0)
,(3,4,0)
,(3,11,0)
,(3,19,0)
,(4,19,0)
,(4,8,0)
,(4,12,0)
,(5,7,0)
,(5,12,0)
,(5,20,0)
,(6,2,0)
,(6,10,0)
,(7,12,0)
,(7,20,0);


INSERT INTO RecipeIngredient(RecipeID,IngredientID,Quantity,UnitID) VALUES
 (1,1,'100',7)
,(1,2,'250',2)
,(2,1,'100',7)
,(2,2,'250',2)
,(2,4,'50',2)
,(2,8,'30',2)
,(3,8,'30',2)
,(3,6,'300',2)
,(3,5,'30',2)
,(3,7,'2',NULL)
,(3,4,'100',2)
,(4,1,'65',7)
,(4,2,'250',2)
,(4,4,'100',2)
,(5,1,'75',7)
,(5,2,'500',2)
,(5,4,'50',2)
,(6,1,'100',7)
,(6,2,'500',2)
,(7,1,'50',7)
,(7,8,'30',2);
