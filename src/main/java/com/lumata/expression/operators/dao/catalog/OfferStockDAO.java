/*
+-------------+----------------------+------+-----+-------------------+-----------------------------+
| Field       | Type                 | Null | Key | Default           | Extra                       |
+-------------+----------------------+------+-----+-------------------+-----------------------------+
| product_id  | smallint(5) unsigned | NO   | PRI | NULL              |                             |
| channel_id  | smallint(5) unsigned | NO   | PRI | 0                 |                             |
| reserved    | bigint(20) unsigned  | YES  |     | 0                 |                             |
| allocated   | bigint(20) unsigned  | YES  |     | 0                 |                             |
| accepted    | bigint(20) unsigned  | YES  |     | 0                 |                             |
| refused     | bigint(20) unsigned  | YES  |     | 0                 |                             |
| update_time | timestamp            | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
+-------------+----------------------+------+-----+-------------------+-----------------------------+
*/

package com.lumata.expression.operators.dao.catalog;

public class OfferStockDAO {
	
	public OfferStockDAO() {}

}
