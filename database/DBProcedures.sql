DELIMITER //

CREATE PROCEDURE GetCoffeeIngredients(IN coffeeID int)
BEGIN
	SELECT i.IngredientID, i.Name, ri.Quantity, u.Name AS Unit FROM Coffee c
	INNER JOIN Recipe r ON c.RecipeID = r.RecipeID 
	INNER JOIN RecipeIngredient ri ON r.RecipeID = ri.RecipeID
	INNER JOIN Ingredient i ON ri.IngredientID = i.IngredientID 
	INNER JOIN Unit u ON ri.UnitID = u.UnitID 
	WHERE c.CoffeeID = CoffeeID;
END //

CREATE PROCEDURE GetCoffeeTags(IN coffeeID int)
BEGIN
	SELECT t.TagID, ct.value, t.Name, t.Description FROM Coffee c 
	INNER JOIN CoffeeTag ct ON c.CoffeeID = ct.CoffeeID 
	INNER JOIN Tag t ON ct.TagID = t.TagID 
	WHERE c.CoffeeID = coffeeID;
END //
DELIMITER ;