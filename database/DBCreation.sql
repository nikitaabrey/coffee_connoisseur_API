CREATE TABLE  Difficulty (
	DifficultyID INT NOT NULL,
	Level VARCHAR(255) NOT NULL,
	PRIMARY KEY(DifficultyID)
);

CREATE TABLE Recipe (
	RecipeID INT NOT NULL,
  DifficultyID INT NULL,
	Name VARCHAR(150) NOT NULL,
	Description VARCHAR(255),
	Instructions VARCHAR(1200),
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
  Name VARCHAR(10) NOT NULL, 
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
  UnitID INT,
  Quantity VARCHAR(120),
  CONSTRAINT PK_RecipeIngredient PRIMARY KEY (RecipeID, IngredientID),
  FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID),
  FOREIGN KEY (IngredientID) REFERENCES Ingredient(IngredientID)
);