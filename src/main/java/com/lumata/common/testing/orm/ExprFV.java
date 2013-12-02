package com.lumata.common.testing.orm;

import com.lumata.common.testing.orm.Statement.MysqlSelectFuncType;

public class ExprFV implements IExprFV {
	
	private Enum<?> field;
	private Op.Types op;
	private Object value;
	private Object[] values;
	private boolean use_place_holder;
	private MysqlSelectFuncType.ValueTypes value_type;
	
	public ExprFV() {}

	public ExprFV( Enum<?> field, Op.Types op ) {
		
		this.setField( field );
		this.setOp( op );
		this.setUsePlaceHolder( true );
		this.setValueType( MysqlSelectFuncType.ValueTypes.Single_Value );
		
	}
	
	public ExprFV( Enum<?> field, Op.Types op, Object value ) {
		this.setField( field );
		this.setOp( op );
		this.setValue( value );
		this.setUsePlaceHolder( false );
		this.setValueType( MysqlSelectFuncType.ValueTypes.Single_Value );
	}
	
	public ExprFV( Enum<?> field, Op.Types op, Object... values ) {
		this.setField( field );
		this.setOp( op );
		this.setValues( values );
		this.setUsePlaceHolder( false );
		this.setValueType( MysqlSelectFuncType.ValueTypes.Multiple_Values );
	}
	
	public Enum<?> getField() {
		return this.field;
	}
	
	public Op.Types getOp() {
		return this.op;
	}
	
	public Object getValue() {
		return this.value;
	}
	
	public Object[] getValues() {
		return this.values;
	}
	
	public boolean getUsePlaceHolder() {
		return this.use_place_holder;
	}
	
	public Object getValueType() {
		return this.value_type;
	}

	public void setField( Enum<?> field ) {
		this.field = field;
	}
	
	public void setOp( Op.Types op ) {
		this.op = op;
	}
	
	public void setValue( Object value ) {
		this.value = value;
	}
	
	public void setValues( Object... values ) {
		this.values = values;
	}
	
	public void setUsePlaceHolder( boolean use_place_holder ) {
		this.use_place_holder = use_place_holder;
	}
	
	public void setValueType( MysqlSelectFuncType.ValueTypes value_type ) {
		this.value_type = value_type;
	}
	
}
