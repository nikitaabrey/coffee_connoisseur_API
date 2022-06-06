CREATE TABLE IF NOT EXISTS. Coffee (
  CoffeeID int NOT NULL,
  RecipeID int NOT NULL,
  Name varchar(50) NOT NULL,
  Description varchar(255),
  Rating int,
  PRIMARY KEY (CoffeeID),
  FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID)
);

CREATE TABLE IF NOT EXISTS. Tag (
  TagID int NOT NULL,
  Name varchar(100) NOT NULL,
  Description varchar(255),
  PRIMARY KEY(TagID)
);

CREATE TABLE IF NOT EXISTS. CoffeeTag (
  CoffeeID int NOT NULL,
  TagID int NOT NULL,
  Value varchar(50) NOT NULL,
  CONSTRAINT PK_CoffeeTag PRIMARY KEY (CoffeeID,TagID),
  FOREIGN KEY (CoffeeID) REFERENCES Coffee(CoffeeID),
  FOREIGN KEY (TagID) REFERENCES Tag(TagID)
);