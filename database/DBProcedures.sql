DELIMITER //

CREATE PROCEDURE GetCoffeeIngredients(IN coffeeID int)
BEGIN
	SELECT i.IngredientID, i.Name FROM Coffee c
	INNER JOIN Recipe r ON c.RecipeID = r.RecipeID 
	INNER JOIN RecipeIngredient ri ON r.RecipeID = ri.RecipeID
	INNER JOIN Ingredient i ON ri.IngredientID = i.IngredientID 
	WHERE c.CoffeeID = CoffeeID;
END //
DELIMITER ;
