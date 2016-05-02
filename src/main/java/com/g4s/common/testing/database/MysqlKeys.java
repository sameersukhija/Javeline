package com.g4s.common.testing.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlKeys {

	String table;
	String nonUnique;
	String keyName;
	String seqInIndex;
	String columnName;
	String collation;
	String cardinality;
	String subPart;
	String packed;
	String isNull;
	String indexType;
	String comment;
	String indexComment;

	public enum Columns {
		Table,
		Non_unique,
		Key_name,
		Seq_in_index,
		Column_name,
		Collation,
		Cardinality,
		Sub_part,
		Packed,
		Null,
		Index_type,
		Comment,
		Index_comment
	}
	
	public MysqlKeys() {
		
		table = null;
		nonUnique = null;
		keyName = null;
		seqInIndex = null;
		columnName = null;
		collation = null;
		cardinality = null;
		subPart = null;
		packed = null;
		isNull = null;
		indexType = null;
		comment = null;
		indexComment = null;
		
	}
	
	public MysqlKeys( ResultSet rs ) throws SQLException {
		
		this.setTable( rs.getString( Columns.Table.name() ) );
		this.setNonUnique( rs.getString( Columns.Non_unique.name() ) );
		this.setKeyName( rs.getString( Columns.Key_name.name() ) );
		this.setSeqInIndex( rs.getString( Columns.Seq_in_index.name() ) );
		this.setColumnName( rs.getString( Columns.Column_name.name() ) );
		this.setCollation( rs.getString( Columns.Collation.name() ) );
		this.setCardinality( rs.getString( Columns.Cardinality.name() ) );
		this.setSubPart( rs.getString( Columns.Sub_part.name() ) );
		this.setPacked( rs.getString( Columns.Packed.name() ) );
		this.setIsNull( rs.getString( Columns.Null.name() ) );
		this.setIndexType( rs.getString( Columns.Index_type.name() ) );
		this.setComment( rs.getString( Columns.Comment.name() ) );
		this.setIndexComment( rs.getString( Columns.Index_comment.name() ) );
		
	}
	
	public String getTable() {
		return this.table;
	}
	
	public String getNonUnique() {
		return this.nonUnique;
	}
	
	public String getKeyName() {
		return this.keyName;
	}
	
	public String getSeqInIndex() {
		return this.seqInIndex;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public String getCollation() {
		return this.collation;
	}

	public String getCardinality() {
		return this.cardinality;
	}

	public String getSubPart() {
		return this.subPart;
	}

	public String getPacked() {
		return this.packed;
	}

	public String getIsNull() {
		return this.isNull;
	}

	public String getIndexType() {
		return this.indexType;
	}

	public String getComment() {
		return this.comment;
	}

	public String getIndexComment() {
		return this.indexComment;
	}

	public void setTable( String table ) {
		this.table = table;
	}
	
	public void setNonUnique( String nonUnique ) {
		this.nonUnique = nonUnique;
	}
	
	public void setKeyName( String keyName ) {
		this.keyName = keyName;
	}
	
	public void setSeqInIndex( String seqInIndex ) {
		this.seqInIndex = seqInIndex;
	}

	public void setColumnName( String columnName ) {
		this.columnName = columnName;
	}

	public void setCollation( String collection ) {
		this.collation = collection;
	}

	public void setCardinality( String cardinality ) {
		this.cardinality = cardinality;
	}

	public void setSubPart( String subPart ) {
		this.subPart = subPart;
	}

	public void setPacked( String packed ) {
		this.packed = packed;
	}

	public void setIsNull( String isNull ) {
		this.isNull = isNull;
	}

	public void setIndexType( String indexType ) {
		this.indexType = indexType;
	}

	public void setComment( String comment ) {
		this.comment = comment;
	}

	public void setIndexComment( String indexComment ) {
		this.indexComment = indexComment;
	}
	
}
