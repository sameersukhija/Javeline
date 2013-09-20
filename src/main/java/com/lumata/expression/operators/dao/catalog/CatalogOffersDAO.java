/*
+----------------------+-----------------------------------------------------+------+-----+-------------------+-----------------------------+
| Field                | Type                                                | Null | Key | Default           | Extra                       |
+----------------------+-----------------------------------------------------+------+-----+-------------------+-----------------------------+
| offer_id             | smallint(5)                                         | NO   | PRI | NULL              | auto_increment              |
| offer_name           | varchar(45)                                         | YES  | UNI | NULL              |                             |
| description          | varchar(512)                                        | YES  |     | NULL              |                             |
| start_date           | date                                                | YES  |     | NULL              |                             |
| end_date             | date                                                | YES  |     | NULL              |                             |
| offer_status         | enum('CREATED','ACTIVATED','CANCELED','TERMINATED') | YES  |     | NULL              |                             |
| eligibility_criteria | blob                                                | YES  |     | NULL              |                             |
| notification         | blob                                                | YES  |     | NULL              |                             |
| offer_rank           | tinyint(3) unsigned                                 | NO   |     | NULL              |                             |
| update_time          | timestamp                                           | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
+----------------------+-----------------------------------------------------+------+-----+-------------------+-----------------------------+
*/

package com.lumata.expression.operators.dao.catalog;

public class CatalogOffersDAO {

	public CatalogOffersDAO() {}
	
}
