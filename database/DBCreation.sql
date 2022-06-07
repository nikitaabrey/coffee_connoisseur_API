
CREATE TABLE  Difficulty (
	DifficultyID int NOT NULL,
	DifficultyLevel varchar(250) NOT NULL,
	PRIMARY KEY(DifficultyID)
);
CREATE TABLE Recipe (
	RecipeID INT IDENTITY(1,1) PRIMARY KEY,
	Name VARCHAR(150) NOT NULL,
	Description VARCHAR(255),
	Instructions VARCHAR(255),
	PrepTime FLOAT,
	Difficulty INT,
	FOREIGN KEY (Difficulty) REFERENCES Difficulty(DifficultyID)
);

CREATE TABLE Coffee (
  CoffeeID int  IDENTITY(1,1)  NOT NULL,
  RecipeID int NOT NULL,
  Name varchar(50) NOT NULL,
  Description varchar(255),
  Rating int,
  PRIMARY KEY (CoffeeID),
  FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID)
);

CREATE TABLE  Tag (
  TagID int NOT NULL,
  Name varchar(100) NOT NULL,
  Description varchar(255),
  PRIMARY KEY(TagID)
);

CREATE TABLE  CoffeeTag (
  CoffeeID int NOT NULL,
  TagID int NOT NULL,
  Value varchar(50) NOT NULL,
  CONSTRAINT PK_CoffeeTag PRIMARY KEY (CoffeeID,TagID),
  FOREIGN KEY (CoffeeID) REFERENCES Coffee(CoffeeID),
  FOREIGN KEY (TagID) REFERENCES Tag(TagID)
);

CREATE TABLE Unit (
	UnitID int NOT NULL,
	UnitName int NOT NULL, 
	PRIMARY KEY(UnitID)
);


CREATE TABLE Ingredient (
 IngredientID INT IDENTITY(1,1) PRIMARY KEY ,
 Name VARCHAR(150) NOT NULL,
);
CREATE TABLE RecipeIngredient (
RecipeID INT  FOREIGN KEY REFERENCES Recipe(RecipeID),
IngredientID INT FOREIGN KEY REFERENCES Ingredient(IngredientID),
Quantity VARCHAR(120),
Unit INT REFERENCES Unit(UnitID),
PRIMARY KEY (IngredientID, RecipeID)
);
