package com.lumata.common.testing.model;

import java.util.Map;
import java.util.Set;

import org.testng.internal.annotations.Sets;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

public class SchemaMetadata {

	private Map<String, Integer> tableRecordNumber;

	public SchemaMetadata() {
		this.tableRecordNumber = Maps.newHashMap();
	}

	public void add(String tableName, Integer recordCount) {
		tableRecordNumber.put(tableName, recordCount);
	}

	public Set<String> tableDifference(SchemaMetadata tmp) {		
		MapDifference<String, Integer> diff = Maps.difference(this.tableRecordNumber, tmp.tableRecordNumber);
		Set<String> diffTable = Sets.newHashSet();
		diffTable.addAll(diff.entriesDiffering().keySet());
		diffTable.addAll(diff.entriesOnlyOnRight().keySet());	
		return diffTable;
	}

	public int size() {
		return tableRecordNumber.size();
	}

	public Set<String> allTables() {
		return tableRecordNumber.keySet();
	}

	@Override
	public String toString() {
		return tableRecordNumber.toString();
	}

}
