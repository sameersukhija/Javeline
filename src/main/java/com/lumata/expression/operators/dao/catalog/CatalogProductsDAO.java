/*
+-------------------+--------------+------+-----+---------+----------------+
| Field             | Type         | Null | Key | Default | Extra          |
+-------------------+--------------+------+-----+---------+----------------+
| product_id        | smallint(5)  | NO   | PRI | NULL    | auto_increment |
| external_id       | varchar(10)  | YES  |     | NULL    |                |
| product_name      | varchar(45)  | YES  | UNI | NULL    |                |
| description       | varchar(512) | YES  |     | NULL    |                |
| start_date        | date         | YES  |     | NULL    |                |
| end_date          | date         | YES  |     | NULL    |                |
| recommended_price | float        | YES  |     | NULL    |                |
| supplier_id       | smallint(5)  | NO   |     | NULL    |                |
| unitary_cost      | float        | YES  |     | NULL    |                |
| url_image         | varchar(255) | YES  |     | NULL    |                |
+-------------------+--------------+------+-----+---------+----------------+
*/

package com.lumata.expression.operators.dao.catalog;

public class CatalogProductsDAO {
	
	public CatalogProductsDAO() {}

}
