-- View that returns Coffees and their recipes
CREATE VIEW CoffeeRecipeView AS
SELECT c.CoffeeID, c.Name, c.Description, c.Rating, r.RecipeID, r.Name AS RecipeName, r.Description AS RecipeDescription, r.Instructions, r.PrepTime, d.`Level` AS Difficulty
FROM Coffee c 
INNER JOIN Recipe r ON c.RecipeID = r.RecipeID
INNER JOIN Difficulty d ON r.DifficultyID = d.DifficultyID;