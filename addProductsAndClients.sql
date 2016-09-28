
/*==============================================================*/
/* This script allow to insert some products into the DB        */
/*==============================================================*/



/*==============================================================*/
/* --Products Data Dump                                         */
/*==============================================================*/


INSERT INTO `product` (`NAME`, `PRICE`, `DESCRIPTION`) VALUES
('Xbox 360', 500.00, 'Xbox console'),
('TV Samsung 45"', 700.00, '45" Led Tv'),
('iMac all included', 2300.00, 'all in one imac for home use'),
('Wireless optical mouse', 10.00, NULL),
('ipad', 320.00, 'ipad with accesories'),
('Guitar Pro 6 Software for all musicians', 100.00, 'New great guitar pro'),
('Sword Handle Umbrellas', 30.00, 'When you need to stay safe from the rain but also want to keep your man ego intact, a sword handle umbrella is the only solution.'),
('Unbreakable Smart Phone Screen Cover', 20.00, 'Upgrade the armor on your expensive new smart phone with the unbreakable smart phone cover.'),
('TRON Motorcycle', 30000.00, 'This TRON style motorcycle is a fully functional and street legal bike that is powered by a Suzuki 996cc engine.'),
('Diesel Powered MechWarrior', 1500000.00, 'Now you can own your very own diesel powered MechWarrior that stands 13 feet tall and weighs 4.4 tons.'),
('Spider Mouse', 15.00, 'Encase a real spider in a clear acrylic mouse and watch as your computer or laptop quickly becomes the least used PC at your home or office.'),
('Light Up Shoes', 45.00, 'These aren’t just any regular light up shoes, these custom made kicks are hand made to order from the designer responsible for the lighting in the TRON movie and for Daft Punk.');



/*==============================================================*/
/* --Customer Data Dump                                         */
/*==============================================================*/

INSERT INTO `customer` (`CUSTOMER_ID`, `NAME`, `EMAIL`) VALUES
('Santiago Ruiz', 'santiago@hotmail.com'),
('Wilson Guevara', 'wilson@gmail.com'),
('Jonnatan Garcia', 'jhonnatan@yahoo.com'),
('Karen Guzman', 'karen@hotmail.com'),
('Daniel Sosa', 'dani@gmail.com');
