package com.g4s.common.testing.model;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.g4s.common.testing.model.SchemaMetadata;

public class SchemaMetadataTest {

	@Test
	public void noDiff(){
		SchemaMetadata orig = new SchemaMetadata();
		for (int i = 0; i < 10; i++) {
			orig.add(getTableName(i), i);
		}
		
		SchemaMetadata modified = new SchemaMetadata();
		for (int i = 0; i < 10; i++) {
			modified.add(getTableName(i), i);
		}
		Set<String> tableModifed = orig.tableDifference(modified);
		Assert.assertEquals(tableModifed.size(),0);
		
		for (String t : orig.allTables()) {
			Assert.assertFalse(tableModifed.contains(t));	
		}
	}
	
	@Test
	public void diff1(){
		int origTableNumber = 10;
		SchemaMetadata orig = new SchemaMetadata();
		for (int i = 0; i < origTableNumber; i++) {
			orig.add(getTableName(i), i);
		}
		
		SchemaMetadata modified = new SchemaMetadata();
		for (int i = 0; i < origTableNumber; i++) {
			modified.add(getTableName(i), i+100);
		}
		Set<String> tableModifed = orig.tableDifference(modified);
		Assert.assertEquals(tableModifed.size(),origTableNumber);
		
		for (String t : orig.allTables()) {
			Assert.assertTrue(tableModifed.contains(t));	
		}
	}
	
	@Test
	public void diff2(){
		SchemaMetadata orig = new SchemaMetadata();
		int origTableNumber = 10;
		for (int i = 0; i < origTableNumber; i++) {
			orig.add(getTableName(i), i);
		}
		
		
		SchemaMetadata modified = new SchemaMetadata();
		int modifiedTableNumber = 5;
		for (int i = 0; i < modifiedTableNumber; i++) {
			modified.add(getTableName(i), i+100);
		}
		
		Set<String> tableModifed = orig.tableDifference(modified);
		Assert.assertEquals(modifiedTableNumber, modifiedTableNumber);
		for (int i = modifiedTableNumber; i < origTableNumber; i++) {
			String tableName= getTableName(i);
			if (tableModifed.contains(tableName)){
				Assert.fail("Table "+tableName+" must not be in modified list");
			}
		}
		
		for (int i = 0; i < origTableNumber; i++) {
			String tableName= getTableName(i);
			if (i<modifiedTableNumber){
				Assert.assertTrue(tableModifed.contains(tableName),"Table "+tableName+ " must be modified ");
			}else{
				Assert.assertFalse(tableModifed.contains(tableName),"Table "+tableName+ " must not be modified ");
			}
		}
	}	
	
	@Test
	public void diff3(){
		SchemaMetadata orig = new SchemaMetadata();
		int origTableNumber = 10;
		for (int i = 0; i < origTableNumber; i++) {
			orig.add(getTableName(i), i);
		}
		
		SchemaMetadata modified = new SchemaMetadata();
		int modifiedTableNumber = origTableNumber+5;
		for (int i = origTableNumber; i < modifiedTableNumber; i++) {
			modified.add(getTableName(i), i+100);
		}
		Set<String> tableModifed = orig.tableDifference(modified);
		System.out.println(tableModifed);
		Assert.assertEquals(modifiedTableNumber, modifiedTableNumber);
		for (int i = modifiedTableNumber; i < origTableNumber; i++) {
			String tableName= getTableName(i);
			if (tableModifed.contains(tableName)){
				Assert.fail("Table "+tableName+" must not be in modified list");
			}
		}
		
		for (int i = 0; i < origTableNumber; i++) {
			String tableName= getTableName(i);
			if (i<modifiedTableNumber){
				Assert.assertFalse(tableModifed.contains(tableName),"Table "+tableName+ " must not be modified ");
			}else{
				Assert.assertTrue(tableModifed.contains(tableName),"Table "+tableName+ " must be modified ");
			}
		}
	}	
	
	private String getTableName(int i){
		return "table_"+i;
	}
}
