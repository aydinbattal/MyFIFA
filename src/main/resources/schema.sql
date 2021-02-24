CREATE TABLE Teams(
    TeamID INT PRIMARY KEY AUTO_INCREMENT,
    TeamName NVARCHAR(30),
    Continent NVARCHAR(30),
    Played INT(30),
    Won INT(30),
    Drawn INT(30),
    Lost INT(30)
);