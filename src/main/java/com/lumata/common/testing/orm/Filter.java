package com.lumata.common.testing.orm;

import com.lumata.common.testing.orm.Statement.MysqlSelectFuncType;
import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class Filter {
	
	public static enum DATE_UNIT {
		MICROSECOND,
		SECOND,
		MINUTE,
		HOUR,
		DAY,
		WEEK,
		MONTH,
		QUARTER,
		YEAR,
		SECOND_MICROSECOND,
		MINUTE_MICROSECOND,
		MINUTE_SECOND,
		HOUR_MICROSECOND,
		HOUR_SECOND,
		HOUR_MINUTE,
		DAY_MICROSECOND,
		DAY_SECOND,
		DAY_MINUTE,
		DAY_HOUR,
		YEAR_MONTH
	} 
	
	public static IOp op( final Enum<?> field ) {
		
		return new Op(field);
		
	}
			
	public static ICond and( final IExprFV... expr_list ) {
		
		return new Cond( MysqlStatement.AND, expr_list );
		
	}
	/*
	public static ICondFV and( final ICondFV... cond_list ) {
		
		CondFV cond = new CondFV( MysqlStatement.AND, cond_list );

		return cond;
		
	}
	*/
	public static ICond and( final ICond... cond_list ) {
		
		Cond cond = new Cond( MysqlStatement.AND, cond_list );

		return cond;
		
	}
		
	public static ICond or( final IExprFV... expr_list ) {
		
		Cond cond = new Cond( MysqlStatement.OR, expr_list );
		
		return cond;
		
	}
	/*
	public static ICondFV or( final ICondFV... cond_list ) {
		
		CondFV cond = new CondFV( MysqlStatement.OR, cond_list );
		
		return cond;
		
	}
	*/
	public static ICond or( final ICond... cond_list ) {
		
		Cond cond = new Cond( MysqlStatement.OR, cond_list );
		
		return cond;
		
	}
	
	public static ICond and( final IExprFF... expr_list ) {
		
		return new Cond( MysqlStatement.AND, expr_list );
		
	}
	/*
	public static ICondFF and( final ICondFF... cond_list ) {
		
		CondFF cond = new CondFF( MysqlStatement.AND, cond_list );

		return cond;
		
	}
	*/	
	public static ICond or( final IExprFF... expr_list ) {
		
		Cond cond = new Cond( MysqlStatement.OR, expr_list );
		
		return cond;
		
	}
	/*
	public static ICondFF or( final ICondFF... cond_list ) {
		
		CondFF cond = new CondFF( MysqlStatement.OR, cond_list );
		
		return cond;
		
	}
	*/
	public static Enum<?> max( final Enum<?> field  ) {
		
		MysqlSelectFuncType.MAX.setValueType( MysqlSelectFuncType.ValueTypes.Single_Field );
		
		return MysqlSelectFuncType.MAX.setField( field );
		
	}
	
	public static Enum<?> min( final Enum<?> field  ) {
		
		MysqlSelectFuncType.MIN.setValueType( MysqlSelectFuncType.ValueTypes.Single_Field );
		
		return MysqlSelectFuncType.MIN.setField( field );
		
	}
	
	public static Enum<?> avg( final Enum<?> field  ) {
		
		MysqlSelectFuncType.AVG.setValueType( MysqlSelectFuncType.ValueTypes.Single_Field );
		
		return MysqlSelectFuncType.AVG.setField( field );
		
	}
	
	public static Enum<?> all( final Enum<?> field  ) {
		
		MysqlSelectFuncType.ALL.setValueType( MysqlSelectFuncType.ValueTypes.Single_Field );
		
		return MysqlSelectFuncType.ALL.setField( field );
		
	}

	public static Enum<?> mid( final Enum<?> field, final int start  ) {
		
		Object[] values = new Object[2];
		
		values[0] = field;
		values[1] = start;
		
		MysqlSelectFuncType.MID.setValueType( MysqlSelectFuncType.ValueTypes.Multiple_Values );
		
		return MysqlSelectFuncType.MID.setObjectArray( values );
		
	}

	public static Enum<?> mid( final Enum<?> field, final int start, final int end  ) {
		
		Object[] values = new Object[3];
		
		values[0] = field;
		values[1] = start;
		values[2] = end;
		
		MysqlSelectFuncType.MID.setValueType( MysqlSelectFuncType.ValueTypes.Multiple_Values );
		
		return MysqlSelectFuncType.MID.setObjectArray( values );
		
	}
	
	public static Enum<?> now() {
		
		MysqlSelectFuncType.NOW.setValueType( MysqlSelectFuncType.ValueTypes.Multiple_Values );
		
		return MysqlSelectFuncType.NOW.setObjectArray();
		
	}
	
	public static Enum<?> sum( final Enum<?> field ) {
		
		MysqlSelectFuncType.SUM.setValueType( MysqlSelectFuncType.ValueTypes.Single_Field );
		
		return MysqlSelectFuncType.SUM.setField( field );
		
	}
	
	public static Enum<?> sum( String value ) {
		
		MysqlSelectFuncType.SUM.setValueType( MysqlSelectFuncType.ValueTypes.Single_Value );
		
		return MysqlSelectFuncType.SUM.setValue( value );
		
	}
	
	public static Enum<?> round( final Enum<?> field, final int decimal  ) {
		
		Object[] values = new Object[2];
		
		values[0] = field;
		values[1] = decimal;
		
		MysqlSelectFuncType.ROUND.setValueType( MysqlSelectFuncType.ValueTypes.Multiple_Values );
		
		return MysqlSelectFuncType.ROUND.setObjectArray( values );
		
	}
	
	public static Enum<?> count() {
		
		MysqlSelectFuncType.COUNT.setValueType( MysqlSelectFuncType.ValueTypes.Single_Value );
		
		return MysqlSelectFuncType.COUNT.setValue( "*" );
		
	}
	
	public static Enum<?> count( final Enum<?> field  ) {
		
		MysqlSelectFuncType.COUNT.setValueType( MysqlSelectFuncType.ValueTypes.Single_Field );
		
		return MysqlSelectFuncType.COUNT.setField( field );
		
	}
	
	public static Enum<?> ucase( final Enum<?> field  ) {
		
		MysqlSelectFuncType.UCASE.setValueType( MysqlSelectFuncType.ValueTypes.Single_Field );
		
		return MysqlSelectFuncType.UCASE.setField( field );
		
	}
	
	public static Enum<?> lcase( final Enum<?> field  ) {
		
		MysqlSelectFuncType.LCASE.setValueType( MysqlSelectFuncType.ValueTypes.Single_Field );
		
		return MysqlSelectFuncType.LCASE.setField( field );
		
	}
	
	public static Enum<?> distinct( final Enum<?> field  ) {
		
		MysqlSelectFuncType.DISTINCT.setValueType( MysqlSelectFuncType.ValueTypes.Single_Field );
		
		return MysqlSelectFuncType.DISTINCT.setField( field );
		
	}
	
	public static Enum<?> concat( final Object... values  ) {
		
		MysqlSelectFuncType.CONCAT.setValueType( MysqlSelectFuncType.ValueTypes.Multiple_Values );
		
		return MysqlSelectFuncType.CONCAT.setObjectArray( values );
		
	}

	public static Enum<?> date_add( final Enum<?> field, Object expr, DATE_UNIT dateUnit  ) {
		
		Object[] values = new Object[2];
		
		values[0] = field;
		values[1] = "INTERVAL " + expr + " " + dateUnit.name();
		
		MysqlSelectFuncType.DATE_ADD.setValueType( MysqlSelectFuncType.ValueTypes.Multiple_Values );
		
		return MysqlSelectFuncType.DATE_ADD.setObjectArray( values );
		
	}

	public static Enum<?> date_add( final String field, Object expr, DATE_UNIT dateUnit  ) {
		
		Object[] values = new Object[2];
		
		values[0] = field;
		values[1] = "INTERVAL " + expr + " " + dateUnit.name();
		
		MysqlSelectFuncType.DATE_ADD.setValueType( MysqlSelectFuncType.ValueTypes.Multiple_Values );
		
		return MysqlSelectFuncType.DATE_ADD.setObjectArray( values );
		
	}
	
	public static Enum<?> date_sub( final Enum<?> field, Object expr, DATE_UNIT dateUnit  ) {
		
		Object[] values = new Object[2];
		
		values[0] = field;
		values[1] = "INTERVAL " + expr + " " + dateUnit.name();
		
		MysqlSelectFuncType.DATE_SUB.setValueType( MysqlSelectFuncType.ValueTypes.Multiple_Values );
		
		return MysqlSelectFuncType.DATE_SUB.setObjectArray( values );
		
	}
	
	public static Enum<?> date_sub( final String field, Object expr, DATE_UNIT dateUnit  ) {
		
		Object[] values = new Object[2];
		
		values[0] = field;
		values[1] = "INTERVAL " + expr + " " + dateUnit.name();
		
		MysqlSelectFuncType.DATE_ADD.setValueType( MysqlSelectFuncType.ValueTypes.Multiple_Values );
		
		return MysqlSelectFuncType.DATE_ADD.setObjectArray( values );
		
	}
	
	//TODO
	public static Enum<?> as( Object expr ) {
		
		
		return null;
		
	}
	
	public static Row row( final Object... values ) {
		
		return new Row(values);
		
	}
		

}
